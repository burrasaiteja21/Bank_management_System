package dao;

import java.util.HashMap;
import model.AccountAttributes;
import model.Transaction;
import model.Status;

/**
 * This class handles the deposit operation for an account.
 * The `execute` method processes a deposit for the specified account number
 * by retrieving the account attributes, updating the balance, and recording the transaction.
 */
public class DepositDao {

    /**
     * This method performs a deposit operation on the specified account.
     * It retrieves the current account balance and overdraft, updates the balance,
     * and records the deposit transaction.
     * 
     * @param accountNumber The account number to deposit the amount to.
     * @param amount The amount to be deposited into the account.
     * @return A Status object indicating the result of the operation.
     */
    public static Status execute(String accountNumber, int amount) {
        /**
         * Retrieve the current account attributes (balance, overdraft) for the given account number.
         * If the account does not exist, return an error status indicating "Invalid account number".
         */
        HashMap<String, Integer> map = AccountOperationsDao.getAccountAttributes(accountNumber);
        if (map == null) {
            return new Status("Invalid account number", true);
        } else {
            /**
             * Retrieve the current balance and overdraft.
             * Add the deposit amount to the balance.
             */
            int balance = map.get("balance") + amount;
            int overdraft = map.get("overdraft");
            
            try {
                /**
                 * Create an updated `AccountAttributes` object with the new balance and overdraft.
                 * Update the account attributes in the database using `AccountOperationsDao`.
                 */
                AccountAttributes updatedAttributes = new AccountAttributes(accountNumber, balance, overdraft);
                AccountOperationsDao.updateAccountAttributes(updatedAttributes);
                
                /**
                 * Create a `Transaction` object for the deposit.
                 * The transaction records the deposit amount and the account number.
                 */
                Transaction transaction = new Transaction(accountNumber, null, amount);
                TransactionDao.execute(transaction);
                
                /**
                 * Return a success status indicating the deposit was successful.
                 */
                return new Status("Deposit successful", false);
            } catch (Exception e) {
                /**
                 * If an exception occurs during the deposit process,
                 * return an error status with a message indicating an issue.
                 */
                return new Status("Some error occurred while depositing. Please try again later.", true);
            }
        }
    }
}
