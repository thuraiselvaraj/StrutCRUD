package com.models;
import com.actions.LoginAction;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import javax.servlet.http.Cookie;
import com.security.Security;

public class LoginModel implements Codes {
    Connection con ;

    public LoginModel(){
        con = DBConnection.getConnection();
    }

    public byte checkIfUserExistsAndDoLogin(LoginAction laction){
        try{
        PreparedStatement ps = con.prepareStatement("select * from login_table where email=?");
        ps.setString(1,laction.getEmail());
        ResultSet rs=ps.executeQuery();   
        System.out.println("Inside the loginCheked");
      
            if(rs.next()){
            String Email=rs.getString("email");
            String Password=rs.getString("password");
            int Id=rs.getInt("_id");
            byte TYPE=(byte)rs.getInt("type");
            rs.close();
            ps.close();
            if(laction.getEmail().equals(Email)){
                if(laction.getPassword().equals(Password)){
                    String session_key=createSession(Id,TYPE);
                    Cookie cookie=new Cookie("SessionKey",session_key);
                    laction.getUserMeta().setSessionKey(session_key);
                    laction.getUserMeta().setType(TYPE);
                    laction.getUserMeta().setId(Id);
                    laction.getServletResponse().addCookie(cookie);
                    return SUCCESS;
                }
                else{
                    return CRED_WRONG;
                }
            } 
        }
        return NO_LOGIN;
        }
        catch(Exception e){
        e.printStackTrace();
        return ERROR;
        }   
    }

    public String createSession(int Id,byte type){
        String SessionKey=Security.get_md5(Security.get_random_string());
        try{
            PreparedStatement ps=con.prepareStatement("insert into session_table(_id,session_key,type) values(?,?,?) ;");
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

    public void finalize() throws Throwable{
        con.close();
    }


    public byte deleteSession(String SessionKey){
        try{
            PreparedStatement ps=con.prepareStatement("delete from session_table where session_key=?");
            ps.setString(1,SessionKey);
            ps.executeUpdate();
            ps.close();
            return SUCCESS;
        }
        catch(Exception e){
            e.printStackTrace();
            return ERROR;
        }
    }   
    
    public byte deleteAllSession(String SessionKey){
        try{
            PreparedStatement ps=con.prepareStatement("select _id from session_table where session_key=?");
            ps.setString(1,SessionKey);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                rs.close();
                ps=con.prepareStatement("delete from session_table where _id=?");
                ps.setInt(1, rs.getInt("_id"));
                ps.executeUpdate();
                ps.close();
                return SUCCESS;
            }
            return NO_SUCCESS;
        }
        catch(Exception e){
            e.printStackTrace();
            return ERROR;
        }
    } 
}