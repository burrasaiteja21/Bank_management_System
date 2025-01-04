package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Status;

public class EmployeeChangePasswordDao {
    public static Status execute(String employeeId,String oldPassword,String newPassword){
        try (Connection con = DatabaseConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("update Employee set password=? where password=? and id=?");
            ps.setString(1, newPassword);
            ps.setString(2,oldPassword);
            ps.setString(3, employeeId);
            int passwordUpdateStatus = ps.executeUpdate();
            if(passwordUpdateStatus==1){
                return new Status("Password updated successfully", false);
            }
            else{
                return new Status("incorrect old password", true);
            }
        }
        catch(Exception e){
            return new Status("Some error occured while updating password please try again later", true);
        }
    }
}
