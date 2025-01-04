package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerVerificationDao {

    public static boolean execute(String ssn, String aadhar,String pan) {
        try (Connection con = DatabaseConnection.getConnection()) {
            String sql = "select count(*) from Citizen where ssn = ? and aadhar = ? and pan=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, ssn);
            st.setString(2, aadhar);
            st.setString(3, pan);

            ResultSet set = st.executeQuery();
            
            if (set.next()) {
                int count = set.getInt(1);
                return count == 1;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
