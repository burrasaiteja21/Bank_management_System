package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import model.AccountAttributes;

/**
 * AccountOperationsDao provides methods to interact with the Customer table 
 * in the database, specifically to retrieve and update account attributes.
 * The two key methods are:
 * - getAccountAttributes: Retrieves account attributes for a given account number.
 * - updateAccountAttributes: Updates the account balance and overdraft for a given account.
 */
public class AccountOperationsDao {

    /**
     * Retrieves account attributes (account type, balance, and overdraft) for a specific account.
     * 
     * @param accountNumber The account number of the customer whose attributes are to be retrieved.
     * @return A HashMap containing the account attributes (accountType, balance, overdraft).
     *         Returns null if no data is found or if an error occurs.
     */
    public static HashMap<String, Integer> getAccountAttributes(String accountNumber) {
        /**
         * Initialize a HashMap to store account attributes.
         */
        HashMap<String, Integer> map = new HashMap<>();
        
        try (Connection con = DatabaseConnection.getConnection()) {
            /**
             * SQL query to fetch account type, balance, and overdraft for the given account number.
             */
            String sql = "select accountType, balance, overdraft from Customer where accountNumber=? and status=1";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, accountNumber);  /**
                                              * Set the account number in the query.
                                              */
            ResultSet set = st.executeQuery();  /**
                                                 * Execute the query.
                                                 */

            if (set.next()) {
                /**
                 * Retrieve values from the result set.
                 */
                int accountType = set.getInt(1);
                int balance = set.getInt(2);
                int overdraft = set.getInt(3);

                /**
                 * Put the retrieved values into the map.
                 */
                map.put("accountType", accountType);
                map.put("balance", balance);
                map.put("overdraft", overdraft);

                /**
                 * Return the map with account attributes.
                 */
                return map;  
            } else {
                /**
                 * If no record is found for the given account number, return null.
                 */
                return null;
            }
        } catch (SQLException e) {
            /**
             * Print any SQL exceptions that occur and return null in case of error.
             */
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Updates the account balance and overdraft attributes for a given account.
     * 
     * @param attributes The AccountAttributes object containing the new balance and overdraft values.
     * @return true if the update was successful, false otherwise.
     */
    public static boolean updateAccountAttributes(AccountAttributes attributes) {
        try (Connection con = DatabaseConnection.getConnection()) {
            /**
             * SQL query to update the balance and overdraft for the specified account number.
             */
            String sql = "update Customer set balance=?, overdraft=? where accountNumber=?";
            PreparedStatement st = con.prepareStatement(sql);

            /**
             * Set the new values from the AccountAttributes object.
             */
            st.setInt(1, attributes.getBalance());
            st.setInt(2, attributes.getOverdraft());
            st.setString(3, attributes.getAccountNumber());

            /**
             * Execute the update query.
             */
            st.executeUpdate();
            return true;  /**
                           * Return true indicating the update was successful.
                           */
        } catch (SQLException e) {
            /**
             * Print any SQL exceptions that occur and return false if the update fails.
             */
            e.printStackTrace();
            return false;
        }
    }
}
