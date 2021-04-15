package com.actions;
import com.security.Security;
public class LoginAction extends ActionCommon{

    private String Email;
    private String Password;
    private Byte UserType;

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

    public Byte getUserType(){
        return UserType;
    }

    public Byte setUserType(byte Id){
       this.UserType=UserType;
        }

    public String redirect(){
           
    }

}

