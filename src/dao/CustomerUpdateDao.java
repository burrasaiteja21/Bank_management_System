package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Status;

/**
 * The CustomerUpdateDao class is responsible for updating customer details 
 * in the database.
 * 
 * The execute method updates the customer's information based on the given 
 * parameters such as name, address, contact number, email, and marital status.
 * 
 * If the customer account number is valid and the update is successful, it 
 * returns a success message. Otherwise, it returns an error message indicating 
 * either an invalid account number or an issue with the database update process.
 */
public class CustomerUpdateDao {

    /**
     * Updates customer details in the database.
     * 
     * @param accountNumber The account number of the customer.
     * @param name The name of the customer.
     * @param address The address of the customer.
     * @param contact The contact number of the customer.
     * @param email The email address of the customer.
     * @param maritalStatus The marital status of the customer.
     * @return Status object indicating success or failure of the operation.
     */
    public static Status execute(String accountNumber, String name, String address, String contact, String email, String maritalStatus) {
        /**
         * SQL query to update customer details based on account number.
         */
        String Upd = "UPDATE Customer SET name = ?, address = ?, contactNumber = ?, email = ?, maritalStatus = ? WHERE accountNumber = ?";

        try (Connection con = DatabaseConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(Upd);
            ps.setString(1, name);
            ps.setString(2, address);
            ps.setString(3, contact);
            ps.setString(4, email);
            ps.setString(5, maritalStatus);
            ps.setString(6, accountNumber);

            /**
             * Execute the update query and check if the update was successful.
             */
            int rs = ps.executeUpdate();

            /**
             * If one row was updated, return a success status.
             */
            if (rs == 1) {
                return new Status("Customer Details Updated Successfully", false);
            } else {
                /**
                 * If the account number is invalid, return an error status.
                 */
                return new Status("Invalid account number", true);
            }
        } catch (SQLException e) {
            /**
             * Print the exception stack trace in case of an error 
             * and return a generic error status.
             */
            e.printStackTrace();
            return new Status("Error occurred while updating customer details, please try again later.", true);
        }
    }
}
