package com.actions;
import javax.security.auth.spi.LoginModule;

import com.models.LoginModel;
import com.models.UserMetaBean;
import com.security.Security;
import com.models.Codes;
public class LoginAction extends ActionCommon{

    private String Email="";
    private String Password="";
    private String SessionKey="";
    public UserMetaBean UserMeta; 

    public void setSessionKey(String SessionKey){
        this.SessionKey=SessionKey;
    }

    public String getSessionKey(){
        return SessionKey;
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

    public String login(){
        LoginModel lmodel=new LoginModel();
        return Codes.stringify(lmodel.checkIfUserExistsAndDoLogin(this));

    }



}

