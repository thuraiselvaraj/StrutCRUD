package com.models;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import com.models.Codes;
import com.actions.StudentAction;

public class StudentModel implements Codes{
    Connection con;
    public StudentModel(){
            con=DBConnection.getConnection();
    }

    public ResultSet getStudent(StudentAction student){
        try{

            PreparedStatement ps=con.prepareStatement("select student_details.*,login_table.email, d.dept from login_table join student_details  using(_id) join dept_student_map s using (student_id) join department d on d._id=s.d_id where login_table._id=?");
            ps.setInt(1,student.getUserMeta().getId());
            System.out.println(ps);
            return ps.executeQuery();
         }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        }
    
    @Override
    public void finalize() throws Throwable{
        con.close();
    }
}
