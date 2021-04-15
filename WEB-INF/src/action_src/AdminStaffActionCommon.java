package com.actions;
import com.opensymphony.xwork2.ActionSupport;
//Common action class that has the common fields.
public class AdminStaffActionCommon extends ActionSupport {
    public String StaffName;
    public String StaffDob;
    public String StaffPhone_no;
    public String StaffDepartment;
    public String StaffEducational_qualification;

    public  int CurrentPage;
    public  String SessionId;

    public void setCurrentPage(int CurrentPage){
           this.CurrentPage=CurrentPage;
    }

    public void setSessionId(String SessionId){
        this.SessionId=SessionId;
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

    public void doLogout(){
        //destroy session Here.
      }
}
    