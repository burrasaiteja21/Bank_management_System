package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Status;

public class CustomerDeleteDao {

    public static Status deletecustomer(String accountNumber) {
        try (Connection con = DatabaseConnection.getConnection()) {
            
            String sql1 = "update Customer set status=0 where accountNumber=?";
            PreparedStatement st = con.prepareStatement(sql1);
            st.setString(1, accountNumber);
            int deactivated = st.executeUpdate();
            if (deactivated!=1) {
                return new Status("Account number not found", false);
            }
            else{
                return new Status("Account deactivated", true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return new Status("Some error occurred, please try again", true);
        }
    }
}
