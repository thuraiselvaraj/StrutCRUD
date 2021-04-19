package com.actions;
import com.models.StudentModel;
import com.models.StudentBean;
import com.models.UserMetaBean;
import com.opensymphony.xwork2.ActionSupport;
import com.models.Codes;
import java.sql.ResultSet;
import com.opensymphony.xwork2.ActionContext;
import java.util.*;

public class StudentAction  extends ActionSupport implements GetMeta{
    public String Message="ERROR";
    public String ActionType="";
    private UserMetaBean UserMeta;
    public StudentBean studentBean;
    private String SessionKey="";

    public StudentAction(){
        UserMeta=new UserMetaBean(); 
        System.out.println("Instantiated the Student");
    }

    public UserMetaBean getUserMeta(){
        return UserMeta;
    } 

    public void setSessionKey(String SessionKey){
        this.SessionKey=SessionKey;
    }

    public String getSessionKey(){
        return SessionKey;
    }

    public void setActionType(String ActionType){
        System.out.println("Calling action type");
        this.ActionType=ActionType;
    }


    public String getStudent(){
        try{
           System.out.println("GETSTUDENT");
            StudentModel student=new StudentModel();
            ResultSet rs=student.getStudent(this);
            if(rs==null){
                Message="null";
                return Message;}
            if(rs.next()){
                studentBean=new StudentBean();
                studentBean.StudentName=rs.getString("name");
                studentBean.StudentDob=rs.getString("dob");
                studentBean.StudentPhone_no=rs.getString("phone_no");
                studentBean.StudentDepartment=rs.getString("dept");
                studentBean.Student_id=rs.getInt("student_id");
                studentBean.StudentEmail=rs.getString("email");
                studentBean.StudentAddress=rs.getString("address");
                Message="SUCCESS";
               return Message;
               }
            else{
                  Message="EMAIL_TAMPERED";
                  return Message;
               }
            }
           catch(Exception e){
               e.printStackTrace();
               return "ERROR";
            }
    }
  
    public String exec(){
        
      ActionContext context = ActionContext.getContext();
      Map<String,Object> parameters = (Map<String,Object>)context.get(ActionContext.PARAMETERS);
      System.out.println(parameters);

        System.out.println("Exec "+ActionType.trim().length());
        System.out.println(UserMeta);

        String temp=null;
        switch(ActionType.trim()){
          case "GetStudent":
             System.out.println(ActionType.trim());
             temp= getStudent();
             return temp+"GSS";
          default :
             Message="Student Action Type not found";
             return Message;
    }
  }
   
}
          