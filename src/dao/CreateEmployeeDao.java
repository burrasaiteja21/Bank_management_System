package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Employee;

/**
 * The CreateEmployeeDao class is responsible for inserting a new employee 
 * record into the Employee table in the database.
 * 
 * The execute method takes an Employee object as input, extracts its attributes, 
 * and performs an SQL INSERT operation to add the employee's details into the database.
 */
public class CreateEmployeeDao {

    /**
     * This method inserts a new employee into the Employee table.
     * 
     * @param employee The Employee object containing the details of the employee.
     * @return true if the insertion was successful, false otherwise.
     */
    public static boolean execute(Employee employee) {
        try (Connection con = DatabaseConnection.getConnection()) {
            /**
             * SQL query to insert a new employee into the Employee table.
             */
            String sql = "insert into Employee (id, name, address, password, email, contactNumber,recoveryPhrase) values (?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            
            /**
             * Setting the parameters of the SQL statement from the employee object.
             */
            st.setString(1, employee.getId());
            st.setString(2, employee.getName());
            st.setString(3, employee.getAddress());
            st.setString(4, employee.getPassword());
            st.setString(5, employee.getEmail());
            st.setString(6, employee.getContactNumber());
            st.setString(7, employee.getRecoveryPhrase());
            /**
             * Execute the insert query to add the new employee.
             */
            st.executeUpdate();
            
            /**
             * Return true indicating that the employee record was successfully inserted.
             */
            return true;
        } catch (SQLException e) {
            /**
             * Print the SQL exception stack trace in case of an error and return false.
             */
            e.printStackTrace();
            return false;
        }
    }
}
