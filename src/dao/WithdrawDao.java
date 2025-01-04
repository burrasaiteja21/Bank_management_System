package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import model.AccountAttributes;
import model.Status;
import model.Transaction;

public class WithdrawDao {

    /*
     * This method handles the withdrawal operation for a given account.
     * It checks if the account exists, verifies sufficient balance (or overdraft),
     * updates the account attributes, and records the transaction.
     * 
     * @param accountNumber: The account number from which the withdrawal is made.
     * @param amount: The amount to be withdrawn.
     * @return A Status object indicating whether the withdrawal was successful or not.
     */
    public static Status execute(String accountNumber, int amount) {

        /* 
         * Retrieve account attributes for the specified account.
         * The attributes include balance, overdraft, and account type.
         */
        HashMap<String, Integer> map = AccountOperationsDao.getAccountAttributes(accountNumber);

        /* 
         * If the account doesn't exist (null map), return an error status.
         */
        if (map == null) {
            return new Status("Invalid account number", true);
        } else {
            /* 
             * Retrieve the balance and overdraft details of the account.
             * 'balance' is the available funds in the account.
             * 'overdraft' is the overdraft limit for current accounts.
             */
            int balance = map.get("balance");
            int overdraft = map.get("overdraft");

            /* 
             * Check if the balance is sufficient for the withdrawal.
             * - If the balance is enough, deduct the withdrawal amount.
             * - If the balance is insufficient, check for overdraft availability.
             */
            if (balance >= amount) {
                balance -= amount;  // Deduct from balance
            } else {
                if (map.get("accountType") == 1) {  // Current account (overdraft allowed)
                    /* 
                     * If the account is a current account (accountType == 1),
                     * check if the overdraft can cover the deficit.
                     */
                    int extraRequired = amount - balance;  /*
                        *Calculate the additional amount needed
                     */ 
                    if (overdraft >= extraRequired) {
                        overdraft -= extraRequired;  /*
                            *Deduct from overdraft
                         */ 
                        balance = 0;  /*
                            *Set balance to 0 as overdraft covers the remaining amount
                         */ 
                    } else {
                        /* 
                         * If overdraft is insufficient, return an error status.
                         */
                        return new Status("Insufficient balance and overdraft", true);
                    }
                } else {
                    /* 
                     * If it's a savings account (accountType != 1), overdraft is not allowed.
                     */
                    return new Status("Insufficient balance", true);
                }
            }

            try {
                /* 
                 * Update the account attributes with the new balance and overdraft values.
                 */
                AccountAttributes updatedAttributes = new AccountAttributes(accountNumber, balance, overdraft);
                AccountOperationsDao.updateAccountAttributes(updatedAttributes);

                /* 
                 * Record the withdrawal transaction in the database.
                 * Since this is a withdrawal, the 'toAccount' is null (as no transfer occurs).
                 */
                Transaction transaction = new Transaction(null, accountNumber, amount);
                TransactionDao.execute(transaction);

                /* 
                 * Return a success status indicating the withdrawal was successful.
                 */
                return new Status("Withdrawal successful", false);
            } catch (Exception e) {
                /* 
                 * If an error occurs while updating account attributes or recording the transaction,
                 * catch the exception and return an error status.
                 */
                System.out.println(e);
                return new Status("Some error occurred while withdrawing. Please try again later.", true);
            }
        }
    }
}
