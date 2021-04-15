package com.models;
public class StaffBean{
    public String StaffName;
    public String StaffDob;
    public String StaffPhone_no;
    public String StaffDepartment;
    public String StaffEducational_qualification;
    public Integer Staff_id;
    public String StaffEmail;
    public String Id;
    
    public int getId(){
            return Id;
        }

    public String getStaffName(){
          return StaffName;
    }

    public String getStaffDob(){
        return StaffDob;
    }

    public String getStaffPhone_no(){
        return StaffPhone_no;
    }

    public String getStaffDepartment(){
        return StaffDepartment;
    }

    public void getStaffEducational_qualification(){
        return StaffEducational_qualification;
    }

    public int getStaff_Id(){
        return Staff_id;
    }
    public String getStaffEmail(){
        return StaffEmail;
    }
}