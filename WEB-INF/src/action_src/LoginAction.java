package com.actions;
import com.models.LoginModel;
import com.models.UserMetaBean;
import com.security.Security;
import com.models.Codes;
import com.opensymphony.xwork2.ActionSupport;
public class LoginAction extends ActionSupport{

    private String Email="";
    private String Password="";
    public UserMetaBean userMeta;
    private String Message="";
   
    public LoginAction(){
        userMeta=new UserMetaBean();  
    }

    public UserMetaBean getUserMeta(){
        return userMeta;
    } 

    public String getMessage(){
        return Message;
    }

    public void setEmail(String Email){
        this.Email=Email.trim();
    }

    public void setPassword(String Password){
        // this.Password=Security.get_md5(Password.trim());
        this.Password=Password.trim();
    }

    public String getEmail(){
       return Email;
    }

    public String getPassword(){
        return Password;
    }

    public String login(){
        LoginModel lmodel=new LoginModel();
        byte Response=lmodel.checkIfUserExistsAndDoLogin(this);
        Message=Codes.stringify(Response);
        System.out.println(userMeta);
        if(Message.equals("SUCCESS")){
            return Codes.stringify(userMeta.getType());
        }
        return Message;
    }
}

