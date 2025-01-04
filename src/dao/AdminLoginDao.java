package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminLoginDao {
	public static boolean execute(String adminId,String hashedPassword){
		try (Connection con = DatabaseConnection.getConnection()) {

            /**
             * Prepare the SQL query to count the number of records that match the provided
             * account number and password in the Customer table.
             */
            String sql = "select count(*) from Admin where id = ? and password = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, adminId);
            st.setString(2, hashedPassword);

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
