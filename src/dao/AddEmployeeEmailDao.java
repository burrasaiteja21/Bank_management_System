package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddEmployeeEmailDao {

    public static boolean execute(String email) {
        try (Connection con = DatabaseConnection.getConnection()) {
            
            String sql = "insert into EmployeeEmails (email) values (?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, email);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
