package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Status;

/**
 * This class handles granting overdraft to a customer account.
 * The `execute` method allows an overdraft to be set for an account 
 * based on specific conditions.
 */
public class GrantOverdraftsDao {

    /**
     * This method updates the overdraft limit for a customer's account.
     * The overdraft is granted only if the account is a current account (accountType = 1).
     * 
     * @param accountNumber The account number to which the overdraft is granted.
     * @param overdraft The overdraft amount to be set for the account.
     * @return A Status object indicating whether the overdraft was granted or an error occurred.
     */
    public static Status execute(String accountNumber, int overdraft) {
        try (Connection con = DatabaseConnection.getConnection()) {
            
            /**
             * Prepare the SQL query to update the overdraft amount for the account.
             * The update will only be applied to current accounts (accountType=1).
             */
            String sql = "update Customer set overdraft=? where accountNumber=? and accountType=1";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, overdraft);
            st.setString(2, accountNumber);

            /**
             * Execute the update and check if the overdraft was successfully granted
             * (i.e., exactly one row was affected).
             */
            boolean overdraftGranted = st.executeUpdate() == 1;
            
            if (overdraftGranted) {
                /**
                 * If the overdraft was successfully granted, return a success status.
                 */
                return new Status("Overdraft granted", false);
            } else {
                /**
                 * If the account was not found or it is not a current account,
                 * return an error status indicating the invalid account number.
                 */
                return new Status("Invalid current account number", true);
            }
        } catch (SQLException e) {
            /**
             * If an exception occurs during the operation, print the error and
             * return an error status with a message.
             */
            e.printStackTrace();
            return new Status("Error occurred, please try again later", true);
        }
    }
}
