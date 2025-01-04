package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Status;

public class CustomerActivateDao {
    public static Status execute(String accountNumber){
        try (Connection con = DatabaseConnection.getConnection()) {
            /**
             * SQL query to fetch account type, balance, and overdraft for the given account number.
             */
            String sql = "update Customer set status=1 where accountNumber=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, accountNumber); 
            int activationStatus = st.executeUpdate(); 
            if(activationStatus==1){
                return new Status("account activated", false);
            }
            else{
                return new Status("incorrect account number", true);
            }
        }
        catch(Exception exception){
            return new Status("Some error occured please try again", true);
        }
    }
}
