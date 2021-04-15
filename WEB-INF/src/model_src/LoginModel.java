package com.models;
import com.actions.LoginAction;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;

public class LoginModel implements Codes{


    public static Statement stmt;

    LoginModel(){
        Connection con = DBConnection.getConnection();
    }

    public int checkIfUserExistsAndDoLogin(LoginAction laction){
        ResultSet rs=null
        try(
        PreparedStatement ps = con.prepareStatement("select * from login_table where email=?");
        ps.setString(1,laction.Email);
        rs=ps.executeQuery();
        ){
        if(rs.next()){
            String Email=rs.getString("Email");
            String Password=rs.getString("Password");
            int Id=rs.getInt("Id");
            if(laction.Email==Email){
                if(laction.Password==Password){
                    laction.setId(Id);
                    return SUCCESS;
                }
                else{
                    return CRED_WRONG;
                }
            }
            return 
        }
        }
        catch(Exception e){
        return 
        }   

    }

    public int doLogin(LoginAction laction){
            PreparedStatement ps = con.prepareStatement("");
            try{
                if(checkIfUserExists!=true){

                int no_of_rows=stmt.executeUpdate("insert into dummy values('chella',"+s+");");
                System.out.println("The no of rows inserted are  :"+no_of_rows);
    
            } catch (SQLException e) {
                e.printStackTrace();
            }
            finally{
                con.close();            }
        }

}