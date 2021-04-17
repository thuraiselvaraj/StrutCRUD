package com.models;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;

public class StaffModel implements Codes{
public StaffModel(){
    con=DBConnection.getConnection();
}
public byte createStudent(StaffAction student){
  try{
      if(checkSidExists(student.Student_id)){
          return STUDENT_ID_EXISTS;
      
      }
      else if(checkEmailExists(student.StudentEmail)){
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
          ps.setString(3, STUDENT);
          ps.executeUpdate();
          ResultSet rs = ps.getGeneratedKeys();
          int Id;
          if(rs.next()){
              Id= rs.getInt(i);
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
           checkForDeptAndAdd(student);
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
public void checkForDeptAndAddStudent(StaffAction student){
  ps=con.prepareStatement("select _id from department where dept=?");
          ps.setString(1,student.StudentDepartment);
          int dept_id;
          if((rs=ps.execute()).next()){
              dept_id=rs.getInt("_id");
              ps.close();
              ps=con.prepareStatement("insert into dept_student_map(d_id,student_id) values(?,?);");
              // ps.setString(1,student.StudentDepartment);
              ps.setInt(1,dept_id);
              ps.setInt(2,student.Student_id);
              ps.executeUpdate();
              rs.close();
              ps.close();
          }
          else{
              ps=con.prepareStatement("insert into department(dept) values(?);",Statement.RETURN_GENERATED_KEYS);
              ps.setString(1,student.StudentDepartment);
              ps.executeUpdate();
              dept_id=ps.getGeneratedKeys().getInt("_id");
              ps.close();
              ps=con.prepareStatement("insert into dept_student_map(d_id,student_id) values(?,?);");
              ps.setInt(1,dept_id);
              ps.setInt(2,student.Student_id);
              ps.executeUpdate();
              ps.close();
          }
}

public void checkForDeptAndAddStudent(StaffAction student)  throws Exception{
    ps=con.prepareStatement("select _id from department where dept=?");
            ps.setString(1,student.StudentDepartment);
            int dept_id;
            if((rs=ps.execute()).next()){
                dept_id=rs.getInt("_id");
                ps.close();
                ps=con.prepareStatement("insert into dept_student_map(d_id,student_id) values(?,?);");
                // ps.setString(1,student.StudentDepartment);
                ps.setInt(1,dept_id);
                ps.setInt(2,student.Student_id);
                ps.executeUpdate();
                rs.close();
                ps.close();
            }
            else{
                ps=con.prepareStatement("insert into department(dept) values(?);",Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,student.StudentDepartment);
                ps.executeUpdate();
                dept_id=ps.getGeneratedKeys().getInt("_id");
                ps.close();
                ps=con.prepareStatement("insert into dept_student_map(d_id,student_id) values(?,?);");
                ps.setInt(1,dept_id);
                ps.setInt(2,student.Student_id);
                ps.executeUpdate();
                ps.close();
            }
  }

  public void checkForDeptAndAddStaff(AdminAction staff) throws Exception{
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


public boolean checkStudentIdExists(int Student_id){
  PreparedStatement ps=con.prepareStatement("select _id from student_details where student_id=?");
  ps.setInt(1,Student_id);
  ResultSet rs=ps.executeQuery();
  return rs.next();
  
}

public boolean checkStudentEmailExists(String StudentEmail){
  PreparedStatement ps=con.prepareStatement("select _id from login_table where email=?");
  ps.setString(1,StudentEmail);
  ResultSet rs=ps.executeQuery();
  return rs.next() ;
}

public boolean checkStaffIdExists(int Staff_id){
    PreparedStatement ps=con.prepareStatement("select _id from staff_details where staff_id=?");
    ps.setInt(1,Staff_id);
    ResultSet rs=ps.executeQuery();
    return rs.next();
    
}

public boolean checkStaffEmailExists(String StaffEmail){
    PreparedStatement ps=con.prepareStatement("select _id from login_table where email=?");
    ps.setString(1,StaffEmail);
    ResultSet rs=ps.executeQuery();
    return rs.next() ;
}



@Override
public void finalize() throws Throwable{
  con.close();
}

public byte deleteStudent(String StudentEmail){
  try{
  PreparedStatement ps = con.prepareStatement("delete from login_table where email=?");
  ps.setString(1,StudentEmail);
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

public byte updateStudent(StaffAction student){
  try{
      if(checkStudentIdExists(student.Student_id)){
          return STUDENT_ID_EXISTS;
      
      }
      else if(checkStudentEmailExists(student.StudentEmail)){
          return EMAIL_EXISTS;
      }
      else{
          con.setAutoCommit(false);
          PreparedStatement ps=con.prepareStatement("update employee details set name=?,student_id=?,dob=?,phone_no=?,educational_qualification=? where _id=?");
           ps.setString(1,student.StudentName);
           ps.setInt(2,student.Student_id);
           ps.setString(3,student.StudentDob);
           ps.setString(4,student.StudentPhone_no);
           ps.setString(5,student.StudentEducational_qualification);
           ps.setInt(6,Id);
           ps.executeUpdate();
           checkForDeptAndAdd(student);
           ps=con.prepareStatement("update login_table set email=? where _id=?;");
           ps.setString(1,staff.StaffEmail);
           ps.setInt(2,staff.Id);
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

public ResultSet listStudents(StaffAction student){
    try{
      PreparedStatement ps=con.prepareStatement("select student_details.*,login_table.email, d.dept from login_table join student_details  using(_id) join dept_student_map s using (student_id) join department d on d._id=s.d_id where d.dept=? order by login_table._id limit 10 offset ?");
      ps.setString(1,student.StaffDepartment);
      ps.setInt(2,student.CurrentPage*10-1); //set offset to pagination for listing.
      return ps.executeQuery();
    }
    catch(Exception e){
        e.printStackTrace();
        return new ResultSet(){
        };
    }
}

public byte updateStaff(StaffAction staff){
    try{
            con.setAutoCommit(false);
            PreparedStatement ps=con.prepareStatement("update staff_details set name=?,dob=?,phone_no=?,educational_qualification=? where _id=?");
             ps.setString(1,staff.StaffName);
             ps.setString(2,staff.StaffDob);
             ps.setString(3,staff.StaffPhone_no);
             ps.setString(4,staff.StaffEducational_qualification);
             ps.setInt(5,staff.Id);
             ps.executeUpdate();
             checkForDeptAndAdd(staff);
             ps.executeUpdate();
             con.commit();
             return SUCCESS;    
    }
    catch(Exception e){
        e.printStackTrace();
        con.rollback();
        return ERROR;
    }
}

}