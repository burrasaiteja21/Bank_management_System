package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Employee;

/**
 * This class handles the login functionality for customers.
 * It checks if the provided account number and password match a record in the database.
 */
public class LoginCustomerDao {

    /**
     * This method checks if the provided account number and hashed password
     * match an existing record in the Customer table of the database.
     * 
     * @param accountNumber The account number to authenticate.
     * @param hashedInputPassword The hashed password input by the customer.
     * @return true if the account number and password match, false otherwise.
     */
    public static boolean execute(String accountNumber, String hashedInputPassword) {
        try (Connection con = DatabaseConnection.getConnection()) {

            /**
             * Prepare the SQL query to count the number of records that match the provided
             * account number and password in the Customer table.
             */
            String sql = "select count(*) from Customer where accountNumber = ? and password = ? and status=1";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, accountNumber);
            st.setString(2, hashedInputPassword);

            /**
             * Execute the query and retrieve the result.
             */
            ResultSet set = st.executeQuery();
            
            if (set.next()) {
                /**
                 * If a matching record is found, retrieve the count and print it for debugging.
                 * Return true if the count is 1 (indicating a valid login).
                 */
                int count = set.getInt(1);
                System.out.println("Login: " + count);
                return count == 1;
            } else {
                /**
                 * If no matching record is found, return false to indicate invalid login credentials.
                 */
                return false;
            }
        } catch (SQLException e) {
            /**
             * If an exception occurs during the database operation, print the error and return false.
             */
            e.printStackTrace();
            return false;
        }
    }
}
