package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customer;

/**
 * This class is responsible for reading customer information from the database.
 * It retrieves the details of a customer based on their account number.
 */
public class ReadCustomerDao {

    /**
     * This method retrieves the customer information for the given account number.
     * It returns a Customer object with the retrieved data, or an empty Customer
     * object if no matching account is found.
     * 
     * @param accountNumber The account number of the customer whose details are to be fetched.
     * @return A Customer object containing the customer's details, or an empty Customer object if not found.
     */
    public static Customer execute(String accountNumber) {
        try (Connection con = DatabaseConnection.getConnection()) {

            /**
             * Prepare the SQL query to fetch customer details from the database
             * based on the account number.
             */
            String sql = "select address,ssn,aadhar,pan,name,balance,contactNumber,email,overdraft,accountType,dob,maritalStatus,gender,status from Customer where accountNumber = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, accountNumber);

            /**
             * Execute the query and get the result set.
             */
            ResultSet set = st.executeQuery();
            if (set.next()) {
                /**
                 * If a customer is found, retrieve their details from the result set.
                 */
                String address = set.getString(1);
                String ssn = set.getString(2);
                String aadhar = set.getString(3);
                String pan = set.getString(4);
                String name = set.getString(5);
                int balance = set.getInt(6);
                String contactNumber = set.getString(7);
                String email = set.getString(8);
                int overdraft = set.getInt(9);
                int accountType = set.getInt(10);
                String dob = set.getString(11);
                String maritalStatus = set.getString(12);
                String gender = set.getString(13);
                int status = set.getInt(14);
                System.out.print(status);
                /**
                 * Create a Customer object with the retrieved details and return it.
                 */
                Customer customer = new Customer(name, address, email, contactNumber, aadhar, pan, ssn, balance, accountNumber, overdraft, accountType, gender, dob, maritalStatus,status);
                return customer;
            } else {
                /**
                 * If no customer is found, return an empty Customer object.
                 */
                return new Customer();
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
