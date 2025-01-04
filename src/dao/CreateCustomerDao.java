package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Customer;

/**
 * The CreateCustomerDao class is responsible for inserting a new customer 
 * record into the Customer table in the database.
 * 
 * The execute method takes a Customer object as an input, extracts its 
 * attributes, and performs an SQL INSERT operation to add the customer's 
 * details into the database.
 */
public class CreateCustomerDao {

    /**
     * This method inserts a new customer into the Customer table.
     * 
     * @param customer The Customer object containing the details of the customer.
     * @return true if the insertion was successful, false otherwise.
     */
    public static boolean execute(Customer customer) {
        try (Connection con = DatabaseConnection.getConnection()) {
            /**
             * SQL query to insert a new customer into the Customer table.
             */
            String sql = "insert into Customer (accountNumber, address, ssn, aadhar, pan, name, password, balance, contactNumber, email, overdraft, accountType, dob, maritalStatus, gender) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            
            /**
             * Setting the parameters of the SQL statement from the customer object.
             */
            st.setString(1, customer.getAccountNumber());
            st.setString(2, customer.getAddress());
            st.setString(3, customer.getSsn());
            st.setString(4, customer.getAadhar());
            st.setString(5, customer.getPan());
            st.setString(6, customer.getName());
            st.setString(7, customer.getPassword());
            st.setInt(8, customer.getBalance());
            st.setString(9, customer.getContactNumber());
            st.setString(10, customer.getEmail());
            st.setInt(11, customer.getOverdraft());
            st.setInt(12, customer.getAccountType());
            st.setString(13, customer.getDob());
            st.setString(14, customer.getMaritalStatus());
            st.setString(15, customer.getGender());

            
            /**
             * Execute the insert query.
             */
            st.executeUpdate();
            
            /**
             * Return true indicating that the customer record was successfully inserted.
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
