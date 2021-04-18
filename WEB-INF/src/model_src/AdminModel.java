package com.models;
import com.actions.AdminAction;
import com.security.Security;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
public class AdminModel implements Codes{
    Connection con;
    ResultSet rs;
    public AdminModel(){
          con=DBConnection.getConnection();
    }
    
    public byte createStaff(AdminAction staff){
        try{
            if(checkStaffIdExists(staff.Staff_Id)){
                return STAFF_ID_EXISTS;
            }
            else if(checkStaffEmailExists(staff.StaffEmail)){
                return EMAIL_EXISTS;
            }
            else{
                con.setAutoCommit(false);
                String RandomPassword=Security.get_random_string();
                //mailto(StaffEmail,RandomPassword)
                String HashedRandomPassword=Security.get_md5(RandomPassword);
                System.out.println(HashedRandomPassword);
                PreparedStatement ps=con.prepareStatement("insert into login_table(email,password,type) values(?,?,?);",Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,staff.StaffEmail);
                ps.setString(2, HashedRandomPassword);
                ps.setInt(3, STAFF);
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                int columnValue;
                // while (rs.next()) {
				// for (int i = 1; i <= columnsNumber; i++) {
				// 	if (i > 1) System.out.print(",  ");
				// 	columnValue = rs.getInt(i);
				// 	System.out.print(columnValue + " " + rsmd.getColumnName(i));
				// }
				// System.out.println("");
		     	// }
                if(rs.next()){
                int Id=rs.getInt("GENERATED_KEY");
                rs.close();
                 ps.close();
                 ps=con.prepareStatement("insert into staff_details(_id,name,staff_id,dob,phone_no,educational_qualification)"+ 
                "values(?,?,?,?,?,?);");
                 ps.setInt(1,Id);
                 ps.setString(2,staff.StaffName);
                 ps.setInt(3,staff.Staff_Id);
                 ps.setString(4,staff.StaffDob);
                 ps.setString(5,staff.StaffPhone_no);
                 ps.setString(6,staff.StaffEducational_qualification);
                 ps.executeUpdate();
                 ps.close();
                 checkForDeptAndAdd(staff,staff.Staff_Id);
                 con.commit();
                 }
                 else return NO_SUCCESS;
            return SUCCESS;
                }
        }
        catch(Exception e){
            e.printStackTrace();
            try {con.rollback();}
            catch(Exception ex){ex.printStackTrace();}
            finally{ return ERROR;}
        }
         
    }
    public void checkForDeptAndAdd(AdminAction staff,Integer prev_staff_id)  throws Exception{
        PreparedStatement ps=con.prepareStatement("select _id from department where dept=?");
        ps.setString(1,staff.StaffDepartment);
        int dept_id=0;
        boolean dept_exists=false;
        System.out.println("The prev_staff id is "+prev_staff_id+" current "+staff.Staff_Id);
        if((rs=ps.executeQuery()).next()){
            dept_id=rs.getInt("_id");
            dept_exists=true;
            rs.close();
            ps.close();
        }
        if(prev_staff_id != null){
            ps=con.prepareStatement("delete from dept_staff_map where staff_id=?");
            ps.setInt(1,prev_staff_id);
            System.out.println("Deleting dept" +ps);
            ps.executeUpdate();
            ps.close();

            ps=con.prepareStatement("delete from dept_staff_map where staff_id=?");
            ps.setInt(1,staff.Staff_Id);
            System.out.println("Deleting dept" +ps);
            ps.executeUpdate();
            ps.close();
        }

        if(dept_exists==false){
            // ps=con.prepareStatement("select * from dept_staff_map where d_id=? and staff_id=?");
            // ps.setInt(1,dept_id);
            // ps.setInt(2,prev_staff_id);
            // printTable("dept_staff_map");
            // System.out.println(ps);
            // rs=ps.executeQuery();
            // boolean rs_result=rs.next();
            // System.out.println("rs_result "+rs_result);
            // rs.close();
            ps=con.prepareStatement("insert into department(dept) values(?);",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,staff.StaffDepartment);
            ps.executeUpdate();
            rs=ps.getGeneratedKeys();
            if(rs.next()){
            dept_id=rs.getInt("GENERATED_KEY");
            ps.close();
             }
           }
            ps=con.prepareStatement("insert into dept_staff_map(d_id,staff_id) values(?,?);");
            ps.setInt(1,dept_id);
            ps.setInt(2,staff.Staff_Id);
            int status=ps.executeUpdate();
            System.out.println(status);        
            ps.close();
       
         
    }      
    public boolean checkStaffIdExists(int Staff_Id) throws Exception{
        PreparedStatement ps=con.prepareStatement("select _id from staff_details where staff_id=?");
        ps.setInt(1,Staff_Id);
        ResultSet rs=ps.executeQuery();
        return rs.next();
        
    }

    public boolean checkStaffEmailExists(String StaffEmail) throws Exception{
        PreparedStatement ps=con.prepareStatement("select _id from user_login where email=?");
        ps.setString(1,StaffEmail);
        ResultSet rs=ps.executeQuery(); 
        return rs.next() ;
    }
    public void finalize() throws Exception{
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

    public boolean checkStaffIdExistsForUpdate(int Staff_Id,int Id) throws Exception{
        PreparedStatement ps=con.prepareStatement("select _id from staff_details where staff_id=? and _id != ?");
        ps.setInt(1,Staff_Id);
        ps.setInt(2,Id);
        ResultSet rs=ps.executeQuery();
        return rs.next();
        
    }

    public boolean checkStaffEmailExistsForUpdate(String StaffEmail,int Id) throws Exception{
        PreparedStatement ps=con.prepareStatement("select _id from user_login where email = ? and _id != ?");
        ps.setString(1,StaffEmail);
        ps.setInt(2,Id);
        ResultSet rs=ps.executeQuery(); 
        return rs.next() ;
    }

    public byte updateStaff(AdminAction staff){
        try{
            if(checkStaffIdExistsForUpdate(staff.Staff_Id,staff.getUserMeta().getId())){
                return STAFF_ID_EXISTS;
            
            }
            else if(checkStaffEmailExistsForUpdate(staff.StaffEmail,staff.getUserMeta().getId())){
                return EMAIL_EXISTS;
            }
            else{
                 con.setAutoCommit(false);
                 PreparedStatement ps=con.prepareStatement("select staff_id from staff_details where _id=?");
                 ps.setInt(1,staff.getUserMeta().getId());
                 ResultSet rs=ps.executeQuery();
                 Integer prev_staff_id=null;
                 if(rs.next()){
                    prev_staff_id=rs.getInt("staff_id");
                 }
                 ps=con.prepareStatement("update staff_details set name=?,staff_id=?,dob=?,phone_no=?,educational_qualification=? where _id=?");
                 ps.setString(1,staff.StaffName);
                 ps.setInt(2,staff.Staff_Id);
                 ps.setString(3,staff.StaffDob);
                 ps.setString(4,staff.StaffPhone_no);
                 ps.setString(5,staff.StaffEducational_qualification);
                 ps.setInt(6,staff.getUserMeta().getId());
                 ps.executeUpdate();
                 ps.close();
                 ps=con.prepareStatement("update login_table set email=? where _id=?;");
                 ps.setString(1,staff.StaffEmail);
                 ps.setInt(2,staff.getUserMeta().getId());
                 ///////boooooooooommm
                 ps.executeUpdate();
                 checkForDeptAndAdd(staff,prev_staff_id);
                 con.commit();
                 return SUCCESS;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            try {con.rollback();}
            catch(Exception ex){ex.printStackTrace();}
            finally{ return ERROR;}
        }

    }

    public ResultSet listStaffs(AdminAction staff){
          try{
            PreparedStatement ps=con.prepareStatement("select staff_details.*,login_table.email, d.dept from login_table join staff_details  using(_id) join dept_staff_map s using (staff_id) join department d on d._id=s.d_id order by login_table._id limit 10 offset ?");
            ps.setInt(1,staff.CurrentPage*10-1);
            return ps.executeQuery();
          }
          catch(Exception e){
              e.printStackTrace();
              return null;
          }
        }

    public ResultSet getStaff(AdminAction staff){
        try{
            PreparedStatement ps=con.prepareStatement("select staff_details.*,login_table.email, d.dept from login_table join staff_details  using(_id) join dept_staff_map s using (staff_id) join department d on d._id=s.d_id where login_table.email=?");
            ps.setString(1,staff.StaffEmail);
            return ps.executeQuery();
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        }

    public void printTable(String tableName) throws Exception{
        PreparedStatement ps = con.prepareStatement("select * from "+tableName+";");
        // ps.setString(1,tableName);
        System.out.println(ps);
        rs=ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnsNumber = rsmd.getColumnCount();
        String columnValue;
        if(rs.next()) {
          for (int i = 1; i <= columnsNumber; i++) {
        	if (i > 1) System.out.print(",  ");
        	columnValue = rs.getString(i);
            System.out.println(rsmd.getColumnName(i));
        	System.out.print(columnValue + "=" + rsmd.getColumnName(i));
        }
        System.out.println("");
         }
    }

    
}