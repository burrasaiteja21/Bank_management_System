package dao;

import java.sql.SQLException;
import java.util.HashMap;
import model.AccountAttributes;
import model.Transaction;
import model.Status;

public class TransferDao {

    /**
     * This method executes the transfer of a specified amount from one account to another.
     * It verifies if both the "from" and "to" account exist, checks the balance and overdraft,
     * updates the account balances, and stores the transaction record in the database.
     *
     * @param fromAccount The account from which the amount will be debited.
     * @param toAccount The account to which the amount will be credited.
     * @param amount The amount to be transferred.
     * @return A Status object indicating whether the transaction was successful or not.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     * @throws SQLException if there are issues interacting with the database.
     */
    public static Status execute(String fromAccount, String toAccount, int amount) throws ClassNotFoundException, SQLException {
        /**
         * Fetch account attributes for both the sender and receiver accounts.
         * The 'from' account details are necessary to check balance and overdraft eligibility,
         * while the 'to' account is required for crediting the transferred amount.
         */
        HashMap<String, Integer> fromMap = AccountOperationsDao.getAccountAttributes(fromAccount);
        HashMap<String, Integer> toMap = AccountOperationsDao.getAccountAttributes(toAccount);

        /**
         * Check if the sender account exists.
         * If the 'from' account doesn't exist, the transaction cannot proceed.
         */
        if (fromMap == null) {
            return new Status("invalid from account number", true);
        }

        /**
         * Retrieve balance, account type, and overdraft details for the sender account.
         * 'balance' indicates the available funds in the account.
         * 'accountType' distinguishes between a current account (1) and a savings account (anything else).
         * 'overdraft' indicates the overdraft limit available for the current account.
         */
        int fromAccountBalance = fromMap.get("balance");
        int fromAccountType = fromMap.get("accountType");
        int fromAccountOverDraft = fromMap.get("overdraft");

        /**
         * Check if the sender account has sufficient funds for the transfer.
         * - If the balance is sufficient, deduct the amount.
         * - If the balance is insufficient and the account is a current account (accountType == 1),
         *   check if the overdraft can cover the deficit.
         * - Savings accounts (accountType != 1) do not have overdraft facilities.
         */
        if (fromAccountBalance >= amount) {
            fromAccountBalance -= amount; // Deduct the amount from the balance
        } else {
            if (fromAccountType == 1) {  // Current account (overdraft is available)
                int extraRequired = amount - fromAccountBalance;  // Calculate the additional amount needed
                if (fromAccountOverDraft >= extraRequired) {
                    fromAccountOverDraft -= extraRequired;  // Deduct from overdraft
                    fromAccountBalance = 0;  // Set balance to 0 since overdraft covered the difference
                } else {
                    return new Status("Insufficient balance and overdraft, Transaction failed", true);
                }
            } else {
                return new Status("Insufficient balance, Transaction failed", true);  // Savings account, no overdraft
            }
        }

        /**
         * Update the 'from' account attributes after deduction of the transfer amount.
         */
        AccountAttributes updatedFromAccountAttributes = new AccountAttributes(fromAccount, fromAccountBalance, fromAccountOverDraft);
        AccountOperationsDao.updateAccountAttributes(updatedFromAccountAttributes);

        /**
         * Check if the 'to' account exists.
         * If the account exists, credit the transfer amount to it.
         */
        if (toMap != null) {
            int updatedToBalance = toMap.get("balance") + amount;  // Credit the amount to the 'to' account
            int toOverdraft = toMap.get("overdraft");  // No need to change overdraft for the 'to' account
            AccountAttributes updatedToAccountAttributes = new AccountAttributes(toAccount, updatedToBalance, toOverdraft);
            AccountOperationsDao.updateAccountAttributes(updatedToAccountAttributes);
        }

        /**
         * Create a transaction record and store it in the database for both accounts.
         */
        Transaction transaction = new Transaction(fromAccount, toAccount, amount);
        TransactionDao.execute(transaction);

        /**
         * Return success message indicating the transaction was completed successfully.
         */
        return new Status("Transfer successful", false);
    }
}
