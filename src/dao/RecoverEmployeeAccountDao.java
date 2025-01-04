package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Status;

public class RecoverEmployeeAccountDao {
    public static Status execute(String employeeId,String newPassword,String recoveryPhrase){
        try (Connection con = DatabaseConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement("update Employee set password=? where recoveryPhrase=? and id=?");
            ps.setString(1, newPassword);
            ps.setString(2,recoveryPhrase);
            ps.setString(3, employeeId);
            System.out.println(employeeId);
            System.out.println(newPassword);
            System.out.println(recoveryPhrase);
            int passwordUpdateStatus = ps.executeUpdate();
            if(passwordUpdateStatus==1){
                return new Status("Password updated successfully", false);
            }
            else{
                return new Status("incorrect employee id or recovery phrase", true);
            }
        }
        catch(Exception e){
            return new Status("Some error occured while updating password please try again later", true);
        }
    }
}
