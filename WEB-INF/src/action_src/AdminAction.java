package com.actions;
import com.models.CommonModel;
public class AdminAction extends AdminStaffActionCommon{
    public Integer Staff_id;
    public String StaffEmail;
    
    public void setStaff_id(int Staff_id){
        this.Staff_id=Staff_id;
    }

    public String setStaffEmail(String StaffEmail){
        this.StaffEmail=StaffEmail;
    }

    public String createStaff(){}

    public String editStaff(){}

    public String updateStaff(){}

    public String deleteStaff(){}

}