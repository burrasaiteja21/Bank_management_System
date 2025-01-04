package dao;

import model.Transaction;
import java.sql.*;

/**
 * This class handles operations related to transactions in the database.
 * Specifically, it inserts a new transaction record into the "Transactions" table.
 */
public class TransactionDao {

    /**
     * This method inserts a new transaction record into the database.
     * It takes a Transaction object, extracts the relevant data, and executes
     * an SQL query to store the transaction in the "Transactions" table.
     * 
     * @param transaction The transaction object containing details to be inserted.
     * @throws ClassNotFoundException if the JDBC driver class is not found.
     * @throws SQLException if any database operation fails.
     */
    public static void execute(Transaction transaction) throws ClassNotFoundException, SQLException {
        /**
         * Extract the details of the transaction object.
         */
        String transactionId = transaction.getTransactionId();
        String fromAccount = transaction.getFromAccount();
        String toAccount = transaction.getToAccount();
        int amount = transaction.getAmount();
        String dateTime = transaction.getDateTime();

        /**
         * Establish a connection to the database.
         */
        try (Connection con = DatabaseConnection.getConnection()) {

            /**
             * Prepare the SQL query for inserting a new transaction record.
             */
            String insertTransactionQuery =
                    "INSERT INTO Transactions(id, toAccount, fromAccount, amount, datetime) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement psInsertTransaction = con.prepareStatement(insertTransactionQuery);
            
            /**
             * Set the values of the prepared statement parameters from the transaction object.
             */
            psInsertTransaction.setString(1, transactionId);
            psInsertTransaction.setString(2, fromAccount);
            psInsertTransaction.setString(3, toAccount);
            psInsertTransaction.setInt(4, amount);
            psInsertTransaction.setString(5, dateTime);
            
            /**
             * Execute the query to insert the transaction.
             */
            psInsertTransaction.executeUpdate();
            
        } catch (SQLException e) {
            /**
             * In case of any SQL exception, throw a new SQLException with a custom message.
             */
            throw new SQLException("Error executing transaction");
        } 
    }
}
