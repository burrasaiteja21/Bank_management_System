package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Transaction;

/**
 * This class is responsible for reading a transaction from the database
 * based on the transaction ID.
 */
public class ReadTransactionByIdDbo {

    /**
     * This method retrieves the transaction details for the given transaction ID.
     * It returns a Transaction object with the retrieved data, or an empty
     * Transaction object if no matching transaction is found.
     * 
     * @param tid The transaction ID whose details are to be fetched.
     * @return A Transaction object containing the transaction details, or an empty Transaction object if not found.
     */
    public static Transaction execute(String tid) {
        try (Connection con = DatabaseConnection.getConnection()) {

            /**
             * Prepare the SQL query to fetch transaction details from the database
             * based on the transaction ID.
             */
            String sql = "select * from Transactions where id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, tid);

            /**
             * Execute the query and get the result set.
             */
            ResultSet set = st.executeQuery();
            if (set.next()) {
                /**
                 * If a transaction is found, retrieve its details from the result set.
                 */
                String id = set.getString(1);
                String toAccount = set.getString(2);
                String fromAccount = set.getString(3);
                int amount = set.getInt(4);
                String time = set.getString(5);

                /**
                 * Create a Transaction object with the retrieved details and return it.
                 */
                Transaction transaction = new Transaction(id, fromAccount, toAccount, amount, time);
                return transaction;
            } else {
                /**
                 * If no transaction is found, return an empty Transaction object.
                 */
                return new Transaction();
            }

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
