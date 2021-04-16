package com.models;
import com.actions.AdminAction;
import com.security.Security;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
public class AdminModel extends Codes{
    Connection con;
    public AdminModel(){
          con=getConnection();
    }
    
    public byte createStaff(AdminAction staff){
        try{
            if(checkSidExists(staff.Staff_id)){
                return STAFF_ID_EXISTS;
            
            }
            else if(checkEmailExists(staff.StaffEmail)){
                return EMAIL_EXISTS;
            }
            else{
                con.setAutoCommit(false);
                String RandomPassword=Security.get_random_string();
                //mailto(StaffEmail,RandomPassword)
                String HashedRandomPassword=Security.get_md5(RandomPassword);
                PreparedStatement ps=con.prepareStatement("insert into login_table(email,password,type) values(?,?,?);",Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,staff.StaffEmail);
                ps.setString(2, HashedRandomPassword);
                ps.setString(3, STAFF);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                int Id;
                if(rs.next()){
                    Id= rs.getInt(i);
                }
                ps.close();
                rs.close();
                ps=con.prepareStatement("insert into staff_details(_id,name,staff_id,dob,phone_no,educational_qualification)"+ 
                "values(?,?,?,?,?,?);");
                 ps.setInt(1,Id);
                 ps.setString(2,staff.StaffName);
                 ps.setInt(3,staff.Staff_id);
                 ps.setString(4,staff.StaffDob);
                 ps.setString(5,staff.StaffPhone_no);
                 ps.setString(6,staff.StaffEducational_qualification);
                 ps.executeUpdate();
                 ps.close();
                 checkForDeptAndAdd(staff);
                 }
            con.commit();
            return SUCCESS;
        }
        catch(Exception e){
            con.rollback();
            e.printStackTrace();
            return ERROR;
        }
         
    }
    public void checkForDeptAndAdd(AdminAction staff){
        ps=con.prepareStatement("select _id from department where dept=?");
                ps.setString(1,staff.StaffDepartment);
                int dept_id;
                if((rs=ps.execute()).next()){
                    dept_id=rs.getInt("_id");
                    ps.close();
                    ps=con.prepareStatement("insert into dept_staff_map(d_id,staff_id) values(?,?);");
                    // ps.setString(1,staff.StaffDepartment);
                    ps.setInt(1,dept_id);
                    ps.setInt(2,staff.Staff_id);
                    ps.executeUpdate();
                    rs.close();
                    ps.close();
                }
                else{
                    ps=con.prepareStatement("insert into department(dept) values(?);",Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1,staff.StaffDepartment);
                    ps.executeUpdate();
                    dept_id=ps.getGeneratedKeys().getInt("_id");
                    ps.close();
                    ps=con.prepareStatement("insert into dept_staff_map(d_id,staff_id) values(?,?);");
                    ps.setInt(1,dept_id);
                    ps.setInt(2,staff.Staff_id);
                    ps.executeUpdate();
                    ps.close();
                }
    }

    public boolean checkStaffidExists(int Staff_id){
        PreparedStatement ps=con.prepareStatement("select _id from staff_details where staff_id=?");
        ps.setInt(1,Staff_id);
        ResultSet rs=ps.executeQuery();
        return rs.next();
        
    }

    public boolean checkStaffEmailExists(String StaffEmail){
        PreparedStatement ps=con.prepareStatement("select _id from staff_details where email=?");
        ps.setString(1,StaffEmail);
        ResultSet rs=ps.executeQuery();
        return rs.next() ;
    }

    @Override
    public finalize() throws Throwable{
        con.close();
    }

    public byte deleteStaff(String StaffEmail){
        try{
        PreparedStatement ps = con.prepareStatement("delete from login_table where email=?");
        ps.setString(1,StaffEmail);
        if(ps.executeUpdate()>0){
            return SUCCESS;
        }
        return NO_SUCCESS;
        }
        catch(Exception e){
            e.printStackTrace();
            return ERROR;
        }

    }

    public byte updateStaff(AdminAction staff){
        try{
            if(checkSidExists(staff.Staff_id)){
                return STAFF_ID_EXISTS;
            
            }
            else if(checkEmailExists(staff.StaffEmail)){
                return EMAIL_EXISTS;
            }
            else{
                con.setAutoCommit(false);
                PreparedStatement ps=con.prepareStatement("update table employee details set name=?,staff_id=?,dob=?,phone_no=?,educational_qualification=? where _id=?");
                 ps.setString(1,staff.StaffName);
                 ps.setInt(2,staff.Staff_id);
                 ps.setString(3,staff.StaffDob);
                 ps.setString(4,staff.StaffPhone_no);
                 ps.setString(5,staff.StaffEducational_qualification);
                 ps.setInt(6,Id);
                 ps.executeUpdate();
                 checkForDeptAndAdd(staff);
                 ps=con.prepareStatement("update table login_table(email) values(?);");
                 ps.setString(1,staff.StaffEmail);
                 ps.executeUpdate();
                 con.commit();
                 return SUCCESS;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            con.rollback();
            return ERROR;
        }

    }

    public ResultSet listStaffs(AdminAction staff){
            PreparedStatement ps=con.prepareStatement("select * from ")
        }

    


}