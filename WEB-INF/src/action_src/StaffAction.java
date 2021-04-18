package com.actions;
import com.models.StudentBean;
import com.models.Codes;
import java.sql.ResultSet;
import java.util.*;
public class StaffAction extends AdminStaffActionCommon{
    public String StudentName;
    public String StudentDob;
    public String StudentPhone_no;
    public String StudentDepartment;
    public String StudentAddress;
    public Integer Student_id;
    public List<StudentList> studentBeanList;
    public StaffBean staffBean;  
    public StudentBean studentBean;


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

    public String createStudent(){
        if(this.StaffEmail.contains("@")==false){
             Message="EMAIL_TAMPERED";
             return Message;
        }
        else if(this.Staff_Id==null){
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
        if(this.StaffEmail.contains("@")==false){
             Message="EMAIL_TAMPERED";
             return Message;
        }
        else if(this.Staff_Id==null){
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
        if(this.StaffEmail.contains("@")==false){
             Message="EMAIL_TAMPERED";
             return Message;
        }
        else if(this.Staff_Id==null){
            Message="EMPTY_STAFF_ID";
            return Message;
        }
        else{
            StaffModel staff=new StaffModel();
            byte Result=staff.updateStaff(this);
            Message=Codes.stringify(Result);
            return Message;
        }
    }

public String getStaff(){
        try{
           System.out.println("GETSTAFF");
           if(this.StaffEmail.contains("@")==false){
               Message="EMAIL_TAMPERED";
               return Message;
          }
          else{
            StaffModel staff=new StaffModel();
            ResultSet rs=staff.getStaff(this);
            if(rs==null){return "ERROR";}
            if(rs.next()){
               staffBean.StaffName=rs.getString("name");
               staffBean.StaffDob=rs.getString("dob");
               staffBean.StaffPhone_no=rs.getString("phone_no");
               staffBean.StaffDepartment=rs.getString("dept");
               staffBean.StaffEducational_qualification=rs.getString("educational_qualification");
               staffBean.Staff_Id=rs.getInt("staff_id");
               staffBean.StaffEmail=rs.getString("email");
            //    setUpdateKey(staff.createUpdateKey(rs.getInt("_id")));
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
                studentBean.Student_Id=rs.getInt("student_id");
                studentBean.StudentEmail=rs.getString("email");
                studentBean.StudentAddress=rs.getString("address");
                studentBeanList.add(studentBean);
               } 
            if(studentBeanList.size()==0){
                System.out.println("This no students");
                return "NO_STAFFS";
            }
               Message="SUCCESS";
               return Message;
            }
        catch(Exception e){
            e.printStackTrace();
            return "ERROR";
        }
    }

public String listStaffs(){
        beanList=new ArrayList<StaffBean>();
        AdminModel staff=new AdminModel();
     try{
        ResultSet rs=staff.listStaffs(this);
        if(rs==null){
            System.out.println("Null");
            return "ERROR";}
        while(rs.next()){
           staffBean =new StaffBean();
           staffBean.StaffName=rs.getString("name");
           staffBean.StaffDob=rs.getString("dob");
           staffBean.StaffPhone_no=rs.getString("phone_no");
           staffBean.StaffDepartment=rs.getString("dept");
           staffBean.StaffEducational_qualification=rs.getString("educational_qualification");
           staffBean.Staff_Id=rs.getInt("staff_id");
           staffBean.StaffEmail=rs.getString("email");
           beanList.add(staffBean);
           } 
        if(beanList.size()==0){
            System.out.println("This no staffs");
            return "NO_STAFFS";
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
       System.out.println("GETSTAFF");
       if(this.StudentEmail.contains("@")==false){
           Message="EMAIL_TAMPERED";
           return Message;
      }
      else{
        StudentModel staff=new StudentModel();
        ResultSet rs=staff.getStudent(this);
        if(rs==null){return "ERROR";}
        if(rs.next()){
            studentBean=new StudentBean();
            studentBean.StudentName=rs.getString("name");
            studentBean.StudentDob=rs.getString("dob");
            studentBean.StudentPhone_no=rs.getString("phone_no");
            studentBean.StudentDepartment=rs.getString("dept");
            studentBean.Student_Id=rs.getInt("student_id");
            studentBean.StudentEmail=rs.getString("email");
            studentBean.StudentAddress=rs.getString("address");
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



    
    public String exec(){
        System.out.println("Exec "+ActionType.trim().length());
        String temp=null;
        switch(ActionType.trim()){
            case "CreateStudent":
                System.out.println(ActionType.trim());
                 temp= createStaff();
                 return temp+"CSS";
            case "GetStudent":
                System.out.println(ActionType.trim());
                 temp= getStaff();
                 return temp+"GSS";

            case "UpdateStaff":
                System.out.println(ActionType.trim());
                 temp= updateStaff();
                 return temp+"USS";

            case "DeleteStudent":
                System.out.println(ActionType.trim());
                 temp=deleteStaff();
                 return temp+"DSS";

            case "ListStudents":
                System.out.println(ActionType.trim());
                 temp= listStaffs();
                 return temp+"LSS";

            case "UpdateStaff":
                 System.out.println(ActionType.trim());
                  temp=deleteStaff();
                  return temp+"DS";

            case "GetStaff":
                 System.out.println(ActionType.trim());
                  temp= getStaff();
                  return temp+"GS";
            
           default :
                 return Message;
        }
    }
    
    

}