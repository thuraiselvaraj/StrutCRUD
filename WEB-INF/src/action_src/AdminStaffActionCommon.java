package com.actions;
import com.models.UserMetaBean;
import com.opensymphony.xwork2.ActionSupport;
//Common action class that has the common fields.
public class AdminStaffActionCommon extends ActionSupport {
    public String StaffName;
    public String StaffDob;
    public String StaffPhone_no;
    public String StaffDepartment;
    public String StaffEducational_qualification;
    public String Message="ERROR";
    public String ActionType="";
    public UserMetaBean UserMeta;
    public  int CurrentPage=1;

    public AdminStaffActionCommon(){
        UserMeta=new UserMetaBean(); 
   }

    public UserMetaBean getUserMeta(){
        return UserMeta;
    } 

    public void setCurrentPage(int CurrentPage){
        if(CurrentPage>0){
           this.CurrentPage=CurrentPage;
        }
    }

    public void setActionType(String ActionType){
        this.ActionType=ActionType;
    }

    public String getMessage(){
        return Message;
    }

    public  void setStaffName(String StaffName){
          this.StaffName=StaffName;
    }

    public  void setStaffDob(String StaffDob){
        this.StaffDob=StaffDob;
    }

    public  void setStaffPhone_no(String StaffPhone_no){
        this.StaffPhone_no=StaffPhone_no;
    }

    public void setStaffDepartment(String StaffDepartment){
        this.StaffDepartment=StaffDepartment;
    }

    public void setStaffEducational_qualification(String StaffEducational_qualification){
        this.StaffEducational_qualification=StaffEducational_qualification;
    }

    // public void doLogout(this){
        
    // }
}
    