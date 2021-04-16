package com.models;
import com.actions.LoginAction;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import com.security.Security;

public class LoginModel implements Codes{
    Connection con ;

    LoginModel(){
        con = DBConnection.getConnection();
    }

    public byte checkIfUserExistsAndDoLogin(LoginAction laction){
        try{
        PreparedStatement ps = con.prepareStatement("select * from login_table where email=?");
        ps.setString(1,laction.getEmail());
        ResultSet rs=ps.executeQuery();   
        ps.close();
            if(rs.next()){
            String Email=rs.getString("email");
            String Password=rs.getString("password");
            int Id=rs.getInt("_id");
            byte TYPE=(byte)rs.getInt("type");
            if(laction.Email().equals(Email)){
                if(laction.getPassword().equals(Password)){
                    laction.setSessionKey(createSession(Id,TYPE));
                    return TYPE;
                }
                else{
                    return CRED_WRONG;
                }
            }
            return NO_LOGIN;
        }
        }
        catch(Exception e){
        return ERROR;
        }   

    }

    public String createSession(int Id,byte type){
        String SessionKey=Security.get_md5(Security.get_random_string());
        try{
            PreparedStatement ps=con.prepareStatement("insert into session_table(_id,session_key,type) values(?,?,?);");
            ps.setString(2,SessionKey);
            ps.setInt(1, Id);
            ps.setInt(3, type);
            ps.executeUpdate();
            ps.close();
            return SessionKey;
        }
        catch(Exception e){
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public finalize() throws Throwable{
        con.close();
    }


    public void deleteSession(String session_key){
        try{
            PreparedStatement ps=con.prepareStatement("delete from session_table where session_key=?");
            ps.setString(1,SessionKey);
            ps.executeUpdate();
            ps.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public  getUser(String session_key){
           
    }

    


    
}