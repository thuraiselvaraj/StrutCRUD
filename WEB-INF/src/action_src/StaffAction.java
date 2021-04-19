package com.actions;
import com.models.StudentBean;
import com.models.Codes;
import com.models.StaffModel;
import java.sql.ResultSet;
import java.util.*;
public class StaffAction extends AdminStaffActionCommon{
    public String StudentName;
    public String StudentDob;
    public String StudentPhone_no;
    public String StudentDepartment;
    public String StudentAddress;
    public Integer Student_id;
    public List<StudentBean> studentBeanList;
    public StudentBean studentBean;
    public int Student_Uid;
    public String StudentEmail;

    public void setStudent_Id(int Student_id){
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

    public void setStaffDepartment(String StaffDepartment){
        this.StaffDepartment=StaffDepartment;
    }

    public void setStudentEmail(String StudentEmail){
        this.StudentEmail=StudentEmail;
    }

    public String createStudent(){
        if(this.StudentEmail.contains("@")==false){
             Message="EMAIL_TAMPERED";
             return Message;
        }
        else if(this.Student_id==null){
            Message="EMPTY_STUDENT_ID";
            return Message;
        }
        else{
            StaffModel staff=new StaffModel();
            byte Result=staff.createStudent(this);
            Message=Codes.stringify(Result);
            return Message;

        }
    }
    public String updateStudent(){
        if(this.StudentEmail.contains("@")==false){
             Message="EMAIL_TAMPERED";
             return Message;
        }
        else if(this.Student_id==null){
            Message="EMPTY_STUDENT_ID";
            return Message;
        }
        else{
            StaffModel staff=new StaffModel();
            byte Result=staff.updateStudent(this);
            Message=Codes.stringify(Result);
            return Message;
        }
    }
    public String updateStaff(){
            StaffModel staff=new StaffModel();
            byte Result=staff.updateStaff(this);
            Message=Codes.stringify(Result);
            return Message;
    }

public String getStaff(){
        try{
            System.out.println("GETSTAFF");
            StaffModel staff=new StaffModel();
            ResultSet rs=staff.getStaff(this);
            if(rs==null){return "NULL";}
            if(rs.next()){
               staffBean.StaffName=rs.getString("name");
               staffBean.StaffDob=rs.getString("dob");
               staffBean.StaffPhone_no=rs.getString("phone_no");
               staffBean.StaffDepartment=rs.getString("dept");
               staffBean.StaffEducational_qualification=rs.getString("educational_qualification");
               staffBean.Staff_Id=rs.getInt("staff_id");
               staffBean.StaffEmail=rs.getString("email");
               Message="SUCCESS";
               return Message;
               } 
              else{
                  return "EMAIL_TAMPERED";
               }
           }
           catch(Exception e){
               e.printStackTrace();
               return "ERROR";
            }
           }


public String listStudents(){
            studentBeanList=new ArrayList<StudentBean>();
            StaffModel staff=new StaffModel();
         try{
            ResultSet rs=staff.listStudents(this);
            if(rs==null){
                System.out.println("Null");
                return "ERROR";}
            while(rs.next()){
                studentBean =new StudentBean();
                studentBean.StudentName=rs.getString("name");
                studentBean.StudentDob=rs.getString("dob");
                studentBean.StudentPhone_no=rs.getString("phone_no");
                studentBean.StudentDepartment=rs.getString("dept");
                studentBean.Student_id=rs.getInt("student_id");
                studentBean.StudentEmail=rs.getString("email");
                studentBean.StudentAddress=rs.getString("address");
                studentBeanList.add(studentBean);
               } 
            if(studentBeanList.size()==0){
                System.out.println("This no students");
                return "NO_STUDENTS";
            }
               Message="SUCCESS";
               return Message;
            }
        catch(Exception e){
            e.printStackTrace();
            return "ERROR";
        }
    }



public String getStudent(){
    try{
       System.out.println("GETSTUDENT");
       if(this.StudentEmail.contains("@")==false){
           Message="EMAIL_TAMPERED";
           return Message;
      }
      else{
        StaffModel staff=new StaffModel();
        ResultSet rs=staff.getStudent(this);
        if(rs==null){return "ERROR";}
        if(rs.next()){
            studentBean=new StudentBean();
            studentBean.StudentName=rs.getString("name");
            studentBean.StudentDob=rs.getString("dob");
            studentBean.StudentPhone_no=rs.getString("phone_no");
            studentBean.StudentDepartment=rs.getString("dept");
            studentBean.Student_id=rs.getInt("student_id");
            studentBean.StudentEmail=rs.getString("email");
            studentBean.StudentAddress=rs.getString("address");
            setUpdateKey(staff.createUpdateKey(rs.getInt("_id")));
            Message="SUCCESS";
           return Message;
           } 
          else{
              return "EMAIL_TAMPERED";
           }
        }
       }
       catch(Exception e){
           e.printStackTrace();
           return "ERROR";
        }
}
public String deleteStudent(){
       try {
        StaffModel staff=new StaffModel();
        return Codes.stringify(staff.deleteStudent(this));
       }
       catch(Exception e){
           return "ERROR";
       }
}



    
    public String exec(){
        System.out.println("Exec "+ActionType.trim().length());
        String temp=null;
        switch(ActionType.trim()){
            case "CreateStudent":
                System.out.println(ActionType.trim());
                 temp= createStudent();
                 return temp+"CSS";

            case "GetStudent":
                System.out.println(ActionType.trim());
                 temp= getStudent();
                 return temp+"GSS";

            case "UpdateStaff":
                System.out.println(ActionType.trim());
                 temp= updateStaff();
                 return temp+"US";

            case "DeleteStudent":
                System.out.println(ActionType.trim());
                 temp=deleteStudent();
                 return temp+"DSS";

            case "ListStudents":
                System.out.println(ActionType.trim());
                 temp= listStudents();
                 return temp+"LSS";

            case "UpdateStudent":
                 System.out.println(ActionType.trim());
                  temp=updateStudent();
                  return temp+"DSS";

            case "GetStaff" :
                 System.out.println(ActionType.trim());
                  temp= getStaff();
                  return temp+"GS";

            case "DisplayStaff" :
                System.out.println(ActionType.trim());
                temp= getStaff();
                return temp+"DS";
            
           default :
                 Message="Action Type not found";
                 return Message;
        }
    }
    
    

}