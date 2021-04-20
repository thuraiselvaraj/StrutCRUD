package com.actions;
import com.models.LoginModel;
import com.models.UserMetaBean;
import com.opensymphony.xwork2.ActionSupport;
import com.models.Codes;

public class LogOutAction extends ActionSupport  implements GetMeta {
    private UserMetaBean userMeta;
    public String Message="ERROR";
    public String getMessage(){
            return Message;
        }
    public LogOutAction(){
        userMeta=new UserMetaBean();
    }

    public UserMetaBean getUserMeta(){
        return userMeta;
    } 
    public String logout(){
        LoginModel lmodel=new LoginModel();
        byte Response=lmodel.deleteSession(userMeta.getSessionKey());
        Message=Codes.stringify(Response);
        System.out.println(userMeta);
        return Message;
    }
}
