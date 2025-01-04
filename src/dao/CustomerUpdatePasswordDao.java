package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Status;

/**
 * This class handles updating the password for a customer in the database.
 * It checks if the account exists, verifies the current password, and 
 * updates it to the new password if valid.
 */
public class CustomerUpdatePasswordDao {

    /**
     * This method updates the password for the customer with the given account number.
     * 
     * It first checks if the account number exists in the database. If found, it compares
     * the provided current password with the stored password. If they match, it updates
     * the password to the new one. The method returns a Status object with a message indicating 
     * success or failure.
     * 
     * @param accountNumber The account number of the customer.
     * @param currPassword The current password provided by the customer.
     * @param newPassword The new password to set.
     * @return A Status object with a success or failure message.
     */
    public static Status updatePassword(String accountNumber, String currPassword, String newPassword) {
        Status res;
        String CheckAccount = "SELECT password FROM Customer WHERE accountNumber = ?";
        String UpdatePassword = "UPDATE Customer SET password = ? WHERE accountNumber = ? AND password = ?";

        try (Connection con = DatabaseConnection.getConnection()) {
            /**
             * Step 1: Check if the account exists and fetch the current password.
             * The SQL query checks if the account with the given account number exists.
             */
            PreparedStatement ps = con.prepareStatement(CheckAccount);
            ps.setString(1, accountNumber);
            ResultSet rs = ps.executeQuery();

            /**
             * Step 2: If the account exists, retrieve the stored password.
             * If the account does not exist, return a status indicating the account was not found.
             */
            if (rs.next()) {
                String existingPassword = rs.getString("password");

                /**
                 * Step 3: Compare the provided current password with the stored password.
                 * If they match, proceed to update the password.
                 */
                if (existingPassword.equals(currPassword)) {
                    PreparedStatement ps1 = con.prepareStatement(UpdatePassword);
                    ps1.setString(1, newPassword);
                    ps1.setString(2, accountNumber);
                    ps1.setString(3, currPassword);

                    /**
                     * Step 4: Execute the update statement.
                     * If the update is successful, return a success status.
                     * If unsuccessful, return a failure status.
                     */
                    int upd = ps1.executeUpdate();

                    if (upd > 0) {
                        res = new Status("Password updated successfully", false);
                    } else {
                        res = new Status("Unable to update the password. Please try again later.", true);
                    }
                } else {
                    /**
                     * If the current password does not match the stored password,
                     * return a status indicating the current password is incorrect.
                     */
                    res = new Status("Current password is incorrect.", true);
                }
            } else {
                /**
                 * If the account number does not exist, return a status indicating the account was not found.
                 */
                res = new Status("Account number not found.", true);
            }
        } catch (SQLException e) {
            /**
             * If an exception occurs during the database operations,
             * print the exception stack trace and return a generic error status.
             */
            e.printStackTrace();
            return new Status("Some error occurred, please try again later.", true);
        }

        return res;
    }
}
