package com.models;
public class StudentBean{
    public String StudentName;
    public String StudentDob;
    public String StudentPhone_no;
    public String StudentDepartment;
    public String StudentAddress;
    public Integer Student_id;
    public String StudentEmail;

    public int getStudent_Id(){
          return Student_id;
    }

    public String getStudentName(){
          return StudentName;
    }

    public String getStudentDob(){
        return StudentDob;
    }

    public String getStudentPhone_no(){
        return StudentPhone_no;
    }

    public String getStudentDepartment(){
        return StudentDepartment;
    }

    public String getStudentAddress(){
        return StudentAddress;
    }

    public String getStudentEmail(){
        return StudentEmail;
    }
}