package com.actions;
import com.security.Security;
public class LoginAction extends ActionCommon{

    private String Email="";
    private String Password="";
    private String SessionId="";
    
    public void setSessionId(String SessionId){
        this.SessionId=SessionId;
    }

    public String getSessionId(){
        return SessionId;
    }

    public void setEmail(String Email){
        this.Email=Email;
    }

    public void setPassword(String Password){
        this.Password=Security.get_md5(Password);
    }

    public String getEmail(){
       return Email;
    }

    public String getPassword(){
        return Password;
    }

    public String redirect(){
           
    }

}

