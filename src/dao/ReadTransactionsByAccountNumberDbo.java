package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Transaction;

/**
 * This class is responsible for fetching all transactions associated with a given
 * account number, either as the sender (fromAccount) or the receiver (toAccount).
 */
public class ReadTransactionsByAccountNumberDbo {

    /**
     * This method retrieves all transactions related to a given account number, 
     * both as a sender and a receiver, ordered by the transaction date in descending order.
     * 
     * @param accountNumber The account number whose transactions are to be fetched.
     * @return An ArrayList of Transaction objects containing the transaction details.
     *         If no transactions are found, returns an empty list.
     */
    public static ArrayList<Transaction> execute(String accountNumber) {
        try (Connection con = DatabaseConnection.getConnection()) {

            /**
             * Prepare the SQL query to select all transactions where the account number
             * is either in the "fromAccount" or "toAccount" fields, ordered by the date.
             */
            String sql = "select * from Transactions where fromAccount = ? or toAccount=? order by datetime desc";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, accountNumber);
            st.setString(2, accountNumber);

            /**
             * Execute the query and get the result set.
             */
            ResultSet set = st.executeQuery();

            /**
             * Create an ArrayList to hold the transactions.
             */
            ArrayList<Transaction> transactions = new ArrayList<Transaction>();

            /**
             * Loop through the result set and create Transaction objects for each row
             * in the result set. Add each transaction to the list.
             */
            while (set.next()) {
                String id = set.getString(1);
                String toAccount = set.getString(2);
                String fromAccount = set.getString(3);
                int amount = set.getInt(4);
                String time = set.getString(5);

                /**
                 * Create a new Transaction object and add it to the list.
                 */
                Transaction transaction = new Transaction(id, fromAccount, toAccount, amount, time);
                transactions.add(transaction);
            }

            /**
             * Return the list of transactions.
             */
            return transactions;

        } catch (SQLException e) {
            /**
             * In case of an exception during the database operation, print the error
             * and return null.
             */
            e.printStackTrace();
            return null;
        }
    }
}
