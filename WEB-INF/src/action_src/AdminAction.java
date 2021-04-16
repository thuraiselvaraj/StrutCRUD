package com.actions;
import com.models.AdminModel;
import com.models.Codes;
public class AdminAction extends AdminStaffActionCommon implements Codes{
    public Integer Staff_Id;
    public String StaffEmail;
    
    public void setStaff_id(Integer Staff_Id){
       System.out.println("Inside setterSid");
        this.Staff_Id=Staff_Id;
    }

    public void setStaffEmail(String StaffEmail){
        this.StaffEmail=StaffEmail.trim();
    }

    public String createStaff(){
        System.out.println("CreateStaff");
        if(this.StaffEmail.contains("@")==false){
             Message="EMAIL_TAMPERED";
             return Message;
        }
        else if(this.Staff_Id==null){
            Message="EMPTY_STAFF_ID";
            return Message;
        }
        else{
            AdminModel admin=new AdminModel();
            byte Result=admin.createStaff(this);
            Message=Codes.stringify(Result);
            return Message;

        }
    }

    public String exec(){
        System.out.println("Exec "+ActionType.trim().length());
        switch(ActionType.trim()){
           case "CreateStaff":
                 System.out.println("CreateStaff");
                 String temp= createStaff();
                 System.out.println(temp);
                 return temp;
           default :
                 return Message;
        }
    }

    // public String editStaff(){}

    // public String updateStaff(){}

    // public String deleteStaff(){}

}