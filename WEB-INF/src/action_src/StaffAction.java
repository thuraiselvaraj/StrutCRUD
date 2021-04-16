package com.actions;
import com.models.StudentBean;
import java.util.*;
public class StaffAction extends AdminStaffActionCommon{
    public String StudentName;
    public String StudentDob;
    public String StudentPhone_no;
    public String StudentDepartment;
    public String StudentEducational_qualification;
    public Integer Student_id;
    public List<StudentList> Student_List;
    public StaffBean staffBean;   

    public void setStudent_Id(String Student_id){
          this.Student_id=Student_id;
    }

    public void setStudentName(String StudentName){
          this.StudentName=StudentName;
    }

    public void setStudentDob(String StudentDob){
        this.StudentDob=StudentDob;
    }

    public void setStudentPhone_no(String StudentPhone_no){
        this.StudentPhone_no=StudentPhone_no;
    }

    public void setStafDepartment(String StafDepartment){
        this.StafDepartment=StafDepartment;
    }

    public void setStudentEducational_qualification(String StudentEducational_qualification){
        this.StudentEducational_qualification=StudentEducational_qualification;
    }

    public void setStudentEmail(String StudentEmail){
        this.StudentEmail=StudentEmail;
    }


    public listStudents(){
          //call list students model here based on offset 
    }
    

}