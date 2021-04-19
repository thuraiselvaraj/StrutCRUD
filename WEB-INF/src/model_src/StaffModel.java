package com.models;
import com.actions.StaffAction;
import com.security.Security;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import com.models.UserMetaBean;
public class StaffModel implements Codes{
    ResultSet rs;
    Connection con;
    public StaffModel(){
        con=DBConnection.getConnection();
    }
public byte createStudent(StaffAction student){
  try{
      if(checkStudentIdExists(student.Student_id)){
          return STUDENT_ID_EXISTS;
      
      }
      else if(checkStudentEmailExists(student.StudentEmail)){
          return EMAIL_EXISTS;
      }
      else{
          con.setAutoCommit(false);
          String RandomPassword=Security.get_random_string();
          //mailto(StudentEmail,RandomPassword)
          String HashedRandomPassword=Security.get_md5(RandomPassword);
          PreparedStatement ps=con.prepareStatement("insert into login_table(email,password,type) values(?,?,?);",Statement.RETURN_GENERATED_KEYS);
          ps.setString(1,student.StudentEmail);
          ps.setString(2, HashedRandomPassword);
          ps.setByte(3,STUDENT);
          ps.executeUpdate();
          ResultSet rs = ps.getGeneratedKeys();
          int Id=0;
          if(rs.next()){
              Id= rs.getInt("GENERATED_KEY");
          }
          ps.close();
          rs.close();
          ps=con.prepareStatement("insert into student_details(_id,name,student_id,dob,phone_no,address)"+ 
          "values(?,?,?,?,?,?);");
           ps.setInt(1,Id);
           ps.setString(2,student.StudentName);
           ps.setInt(3,student.Student_id);
           ps.setString(4,student.StudentDob);
           ps.setString(5,student.StudentPhone_no);
           ps.setString(6,student.StudentAddress);
           ps.executeUpdate();
           ps.close();
           checkForDeptAndAdd(student,student.Student_id);
           }
      con.commit();
      return SUCCESS;
      }
  catch(Exception e){
    e.printStackTrace();
    try {con.rollback();}
    catch(Exception ex){ex.printStackTrace();}
    finally{ return ERROR;}
}
 
   
}

public boolean checkStudentIdExists(int Student_id) throws Exception{
  PreparedStatement ps=con.prepareStatement("select _id from student_details where student_id=?");
  ps.setInt(1,Student_id);
  ResultSet rs=ps.executeQuery();
  return rs.next();
  
}

public boolean checkStudentEmailExists(String StudentEmail) throws Exception{
  PreparedStatement ps=con.prepareStatement("select _id from login_table where email=?");
  ps.setString(1,StudentEmail);
  ResultSet rs=ps.executeQuery();
  return rs.next() ;
}

@Override
public void finalize() throws Throwable{
  con.close();
}

public boolean checkStudentIdExistsForUpdate(int Student_id,int Id) throws Exception{
    PreparedStatement ps=con.prepareStatement("select _id from student_details where student_id=? and _id != ?");
    ps.setInt(1,Student_id);
    ps.setInt(2,Id);
    ResultSet rs=ps.executeQuery();
    System.out.println(ps);
    return rs.next();
    
}

public boolean checkStudentEmailExistsForUpdate(String StudentEmail,int Id) throws Exception{
    PreparedStatement ps=con.prepareStatement("select _id from login_table where email = ? and _id != ?");
    ps.setString(1,StudentEmail);
    ps.setInt(2,Id);
    ResultSet rs=ps.executeQuery();
    System.out.println("cheeck email on update"); 
    System.out.println(ps);
    return rs.next() ;
}

public byte updateStudent(StaffAction staff){
    try{
        getIdByUpdateKey(staff);
        if(checkStudentIdExistsForUpdate(staff.Student_id,staff.Student_Uid)){
            return STUDENT_ID_EXISTS;
        
        }
        else if(checkStudentEmailExistsForUpdate(staff.StudentEmail,staff.Student_Uid)){
            return EMAIL_EXISTS;
        }
        else{
            con.setAutoCommit(false);
            PreparedStatement ps=con.prepareStatement("select student_id from student_details where _id=?");
            ps.setInt(1,staff.Student_Uid);
            ResultSet rs=ps.executeQuery();
            Integer prev_student_id=null;
            System.out.println(ps);
            if(rs.next()){
               prev_student_id=rs.getInt("student_id");
            }
            ps=con.prepareStatement("update student_details set name=?,student_id=?,dob=?,phone_no=?,address=? where _id=?");
            ps.setString(1,staff.StudentName);
            ps.setInt(2,staff.Student_id);
            ps.setString(3,staff.StudentDob);
            ps.setString(4,staff.StudentPhone_no);
            ps.setString(5,staff.StudentAddress);
            ps.setInt(6,staff.Student_Uid);
            ps.executeUpdate();
            System.out.println(ps);
            ps.close();
            ps=con.prepareStatement("update login_table set email=? where _id=?;");
            ps.setString(1,staff.StudentEmail);
            ps.setInt(2,staff.Student_Uid);
            System.out.println(ps);
            ps.executeUpdate();
            checkForDeptAndAdd(staff,prev_student_id);
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


public ResultSet listStudents(StaffAction student){
    try{
      setStaffDepartment(student.getUserMeta());
      PreparedStatement ps=con.prepareStatement("select student_details.*,login_table.email, d.dept from login_table join student_details  using(_id) join dept_student_map s using (student_id) join department d on d._id=s.d_id where d.dept=? order by login_table._id limit 10 offset ?");
      ps.setString(1,student.getUserMeta().getDepartment());
       //set offset to pagination for listing.
       if(student.CurrentPage<=1){
        ps.setInt(2,0);
        }
        else{
            ps.setInt(2,(student.CurrentPage-1)*10);
        }
      return ps.executeQuery();
    }
    catch(Exception e){
        e.printStackTrace();
        return null;
    }
}


public byte updateStaff(StaffAction staff){
    try{                
            PreparedStatement ps=con.prepareStatement("update staff_details set name=?,dob=?,phone_no=?,educational_qualification=? where _id=?");
             ps.setString(1,staff.StaffName);
             ps.setString(2,staff.StaffDob);
             ps.setString(3,staff.StaffPhone_no);
             ps.setString(4,staff.StaffEducational_qualification);
             ps.setInt(5,staff.getUserMeta().getId());
             ps.executeUpdate();
             return SUCCESS;    
    }
    catch(Exception e){
        e.printStackTrace();
        return ERROR;
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
    while (rs.next()) {
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                columnValue = rs.getString(i);
                System.out.print(columnValue + " " + rsmd.getColumnName(i));
            }
            System.out.println("");
             }
}

public ResultSet getStaff(StaffAction staff){
    try{
        PreparedStatement ps=con.prepareStatement("select staff_details.*,login_table.email, d.dept from login_table join staff_details  using(_id) join dept_staff_map s using (staff_id) join department d on d._id=s.d_id where login_table._id=?");
        ps.setInt(1,staff.getUserMeta().getId());
        return ps.executeQuery();
    }
    catch(Exception e){
        e.printStackTrace();
        return null;
    }
    }
public ResultSet getStudent(StaffAction staff){
        try{
        if(CheckIfEmailAndStaffAreOfSameDept(staff)){
            System.out.println("STaff and stu are same department");
            PreparedStatement ps=con.prepareStatement("select student_details.*,login_table.email, d.dept from login_table join student_details  using(_id) join dept_student_map s using (student_id) join department d on d._id=s.d_id where login_table.email=?");
            ps.setString(1,staff.StudentEmail);
            return ps.executeQuery();
           }
        else{
            System.out.println("STaff and stu are diff department");
            return null;
          }
         }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        }

public byte deleteStudent(StaffAction staff){
            try{
            if(CheckIfEmailAndStaffAreOfSameDept(staff)){
                PreparedStatement ps=con.prepareStatement("delete from login_table where email=?");
                ps.setString(1,staff.StudentEmail);
                if(ps.executeUpdate()>0){
                    ps.close();
                    return SUCCESS;
                }
                return NO_SUCCESS;
            }
            else{
                System.out.println("STaff and stu are diff department");
                return EMAIL_TAMPERED;
            }
          }
            catch(Exception e){
                e.printStackTrace();
                return ERROR;
            }
        
        }
        
public boolean CheckIfEmailAndStaffAreOfSameDept(StaffAction staff) throws Exception{
            PreparedStatement ps =con.prepareStatement("select 1 from dept_staff_map sta join dept_student_map stu on sta.staff_id=(select staff_id from staff_details where _id=?) and sta.d_id=stu.d_id join student_details using(student_id) join login_table using (_id) where login_table.email=?;");
            ps.setInt(1,staff.UserMeta.getId());   
            ps.setString(2,staff.StudentEmail);
            rs=ps.executeQuery();
            return rs.next();
        }


public String createUpdateKey(int Id) throws Exception{
        PreparedStatement ps=con.prepareStatement("insert into update_key_map(_id,update_key) values(?,?);");
        ps.setInt(1, Id);
        String RandomPassword=Security.get_random_string();
        String HashedRandomPassword=Security.get_md5(RandomPassword);
        ps.setString(2, HashedRandomPassword);
        ps.executeUpdate();
        ps.close();
        return HashedRandomPassword;
    }

    public void getIdByUpdateKey(StaffAction Staff) throws Exception{
        PreparedStatement ps=con.prepareStatement("select _id from update_key_map where update_key=? ;");
        ps.setString(1,Staff.getUpdateKey());
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            Staff.Student_Uid=rs.getInt("_id");
            rs.close();
            ps.close();
            ps=con.prepareStatement("delete from  update_key_map where update_key=?;");
            ps.setString(1,Staff.getUpdateKey());
            ps.executeUpdate();
            ps.close();
        }
        else{
            throw new Exception();
        }

    }

    public void checkForDeptAndAdd(StaffAction staff,Integer prev_student_id)  throws Exception{
        PreparedStatement ps=con.prepareStatement("select _id from department where dept=?");
        setStaffDepartment(staff.getUserMeta());
        ps.setString(1,staff.getUserMeta().getDepartment());
        int dept_id=0;
        boolean dept_exists=false;
        System.out.println("The prev_staff id is "+prev_student_id+" current "+staff.Student_id);
        System.out.println(ps);
        if((rs=ps.executeQuery()).next()){
            dept_id=rs.getInt("_id");
            dept_exists=true;
            rs.close();
            ps.close();
        }
        if(prev_student_id != null){
            printTable("dept_student_map");
            ps=con.prepareStatement("delete from dept_student_map where student_id=?");
            ps.setInt(1,prev_student_id);
            System.out.println("Deleting dept=" +ps);
            ps.executeUpdate();
            ps.close();
    
    
            //The table value cascades because we use fk constaint so the value alters
            //and duplicates so delete the current staff id in the map
            //and create a new entry
            ps=con.prepareStatement("delete from dept_student_map where student_id=?");
            ps.setInt(1,staff.Student_id);
            System.out.println("Deleting dept" +ps);
            ps.executeUpdate();
            ps.close();
        }
    
        if(dept_exists==false){
            // ps=con.prepareStatement("select * from dept_student_map where d_id=? and student_id=?");
            // ps.setInt(1,dept_id);
            // ps.setInt(2,prev_student_id);
            // printTable("dept_student_map");
            // System.out.println(ps);
            // rs=ps.executeQuery();
            // boolean rs_result=rs.next();
            // System.out.println("rs_result "+rs_result);
            // rs.close();
            ps=con.prepareStatement("insert into department(dept) values(?);",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,staff.getUserMeta().getDepartment());
            ps.executeUpdate();
            rs=ps.getGeneratedKeys();
            if(rs.next()){
            dept_id=rs.getInt("GENERATED_KEY");
            ps.close();
             }
           }
    
            ps=con.prepareStatement("insert into dept_student_map(d_id,student_id) values(?,?);");
            ps.setInt(1,dept_id);
            ps.setInt(2,staff.Student_id);
            System.out.println(ps);
            int status=ps.executeUpdate();
            System.out.println(status);        
            ps.close();
       
         
    }
    public void setStaffDepartment(UserMetaBean ubea) throws Exception{
        PreparedStatement ps=con.prepareStatement("select d.dept from login_table join staff_details staff  using(_id) join dept_staff_map s using (staff_id) join department d on d._id=s.d_id where login_table._id=?;");
        ps.setInt(1,ubea.getId());
        System.out.println(ubea.getId());
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
            ubea.setDepartment(rs.getString("dept"));
        }
        else{
            throw new Exception("No dept found");
        }
    }

   
}

