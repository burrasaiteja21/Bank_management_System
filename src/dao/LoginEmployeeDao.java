package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class handles the login functionality for employees.
 * It checks if the provided employee ID and password match a record in the database.
 */
public class LoginEmployeeDao {

    /**
     * This method checks if the provided employee ID and hashed password
     * match an existing record in the Employee table of the database.
     * 
     * @param id The employee ID to authenticate.
     * @param hashedInputPassword The hashed password input by the employee.
     * @return true if the employee ID and password match, false otherwise.
     */
    public static boolean execute(String id, String hashedInputPassword) {
        try (Connection con = DatabaseConnection.getConnection()) {

            /**
             * Prepare the SQL query to count the number of records that match the provided
             * employee ID and password in the Employee table.
             */
            String sql = "select count(*) from Employee where id = ? and password = ?";
            
            /**
             * Create a prepared statement and set the parameters for the query.
             */
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, id);
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
