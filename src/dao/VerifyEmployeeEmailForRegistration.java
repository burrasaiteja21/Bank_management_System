package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VerifyEmployeeEmailForRegistration {
	public static boolean execute(String email) {
        try (Connection con = DatabaseConnection.getConnection()) {
            
            String sql = "update EmployeeEmails set registered=? where email=? and registered=0";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, 1);
            st.setString(2, email);
            int emailAvailabilityStatus = st.executeUpdate();
            return emailAvailabilityStatus==1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
