package com.actions;
import com.models.StudentModel;
import com.models.StudentBean;
import com.models.UserMetaBean;
import com.models.CommonModel;
import com.models.Codes;
import java.sql.ResultSet;
public class StudentAction extends SessionInterceptor implements Codes {
    private StudentBean student=new StudentBean();
    public UserMetaBean userMeta;
    public String showStudentDetails(){
            Student student_model=new StudentModel();
            ResultSet rs=student_model.getStudent();
            if(rs.next()){
                student.StudentName=rs.getString("name");
                student.StudentDob=rs.getString("dob");
                student.StudentPhone_no=rs.getString("phone_no");
                student.StudentDepartment=rs.getString("department");
                student.StudentAddress=rs.getString("address");
                student.Student_id=rs.getInt("student_id");
                student.StudentEmail=rs.getString("email");
                return Codes.stringify(SUCCESS);
            } 
            return Codes.stringify(NO_STUDENTS);
         }
}

/*  Bean props
    public String StudentName;
    public String StudentDob;
    public String StudentPhone_no;
    public String StudentDepartment;
    public String StudentEducational_qualification;
    public Integer Student_id;
    public String StudentEmail;
    */
/*
create table if not exists student_details(_id int,
                                         name varchar(30) not null,
                                         student_id int unique,
                                         dob varchar(15),
                                         phone_no varchar(15),
                                         department varchar(20),
                                         address varchar(100),
                                         foreign key(_id) references login_table(_id) on delete cascade,
                                         primary key(_id)
                                        );
*/


