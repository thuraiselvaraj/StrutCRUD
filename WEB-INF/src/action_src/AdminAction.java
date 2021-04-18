package com.actions;
import com.models.AdminModel;
import com.models.Codes;
import java.sql.ResultSet;
import com.models.StaffBean;
import java.util.*;
public class AdminAction extends AdminStaffActionCommon implements Codes{
    public Integer Staff_Id;
    public String StaffEmail;
    public String UpdateMail;
    private List<StaffBean> beanList;
 


    public AdminAction(){
        staffBean=new StaffBean();
    }

    public void setStaff_id(Integer Staff_Id){
       System.out.println("Inside setterSid");
        this.Staff_Id=Staff_Id;
    }

    public List<StaffBean> getBeanList(){
        return beanList;
    }

    public void setStaffEmail(String StaffEmail){
        this.StaffEmail=StaffEmail.trim();
    }

    public void setUpdateMail(String UpdateMail){
        this.UpdateMail=UpdateMail;
    }

    public String createStaff(){
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

    public String updateStaff(){
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
            byte Result=admin.updateStaff(this);
            Message=Codes.stringify(Result);
            return Message;
        }
    }

    public String getStaff(){
     try{
        System.out.println("GETSTAFF");
        if(this.StaffEmail.contains("@")==false){
            Message="EMAIL_TAMPERED";
            return Message;
       }
       else{
         AdminModel admin=new AdminModel();
         ResultSet rs=admin.getStaff(this);
         if(rs==null){return "ERROR";}
         if(rs.next()){
            staffBean.StaffName=rs.getString("name");
            staffBean.StaffDob=rs.getString("dob");
            staffBean.StaffPhone_no=rs.getString("phone_no");
            staffBean.StaffDepartment=rs.getString("dept");
            staffBean.StaffEducational_qualification=rs.getString("educational_qualification");
            staffBean.Staff_Id=rs.getInt("staff_id");
            staffBean.StaffEmail=rs.getString("email");
            setUpdateKey(admin.createUpdateKey(rs.getInt("_id")));
            Message="SUCCESS";
            return Message;
            } 
           else{
               return "EMAIL_TAMPERED";
            }
         }
        }
        catch(Exception e){
            e.printStackTrace();
            return "ERROR";
         }
        }
    public String listStaffs(){
        beanList=new ArrayList<StaffBean>();
        AdminModel admin=new AdminModel();
     try{
        ResultSet rs=admin.listStaffs(this);
        if(rs==null){
            System.out.println("Null");
            return "ERROR";}
        while(rs.next()){
           staffBean =new StaffBean();
           staffBean.StaffName=rs.getString("name");
           staffBean.StaffDob=rs.getString("dob");
           staffBean.StaffPhone_no=rs.getString("phone_no");
           staffBean.StaffDepartment=rs.getString("dept");
           staffBean.StaffEducational_qualification=rs.getString("educational_qualification");
           staffBean.Staff_Id=rs.getInt("staff_id");
           staffBean.StaffEmail=rs.getString("email");
           beanList.add(staffBean);
           } 
        if(beanList.size()==0){
            System.out.println("This no staffs");
            return "NO_STAFFS";
        }
           Message="SUCCESS";
           return Message;
        }
    catch(Exception e){
        e.printStackTrace();
        return "ERROR";
    }
}
   public String deleteStaff(){
      AdminModel admin=new AdminModel();
      byte status=admin.deleteStaff(this.StaffEmail);  
      Message=Codes.stringify(status);
      return Message;
   }

    public String exec(){
        System.out.println("Exec "+ActionType.trim().length());
        String temp=null;
        switch(ActionType.trim()){
           case "CreateStaff":
                 System.out.println("CreateStaff");
                 temp= createStaff();
                 System.out.println(temp+"CS");
                 return temp+"CS";
            case "UpdateStaff":
                 System.out.println("UpdateStaff");
                 temp= updateStaff();
                 System.out.println(temp+"US");
                 return temp+"US";
            case "GetStaff":
                 System.out.println("GetStaff");
                 temp= getStaff();
                 System.out.println(temp+"GS");
                 return temp+"GS";
            case "ListStaffs":
                 System.out.println("ListStaff");
                 temp= listStaffs();
                 System.out.println(temp+"LS");
                 return temp+"LS";
            case "DeleteStaff":
                  temp=deleteStaff();
                  System.out.println(temp+"DS");
                  return temp+"DS";
           default :
                 return Message;
        }
    }
   

}