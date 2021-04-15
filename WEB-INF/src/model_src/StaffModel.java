package com.models;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;

public class StaffModel implements Codes{
    private String SessionId;
    private Integer Id;
    private Byte Usertype;
    public StaffModel(){
          //get db conn here.
    }
    
    createStudent(){
         
    }

    deleteStudent(){

    }

    createStudentDetails(){
       
    }

    editStudentDetails(){

    }

    getStudentDetails(){

    }


    editSelfDetails(){

    }

    getSelfDetails(){

    }

    isStaff(){
        
    }

    isSameDept(){

    }


    public void setSessionId(String SessionId){
       this.SessionId=SessionId;
       ResultSet rs=null
       try(
       PreparedStatement ps = con.prepareStatement("select _id,type from user_session where session_key=?");
       ps.setString(1,SessionId);
       rs=ps.executeQuery();
       ){
       if(rs.next()){
           UserType=rs.getInt("type");
           Id=rs.getInt("_id");
           if(laction.Email==Email){
               if(laction.Password==Password){
                   laction.setId(Id);
                   return SUCCESS;
               }
               else{
                   return CRED_WRONG;
               }
           }
           return 
       }
       }
       catch(Exception e){
        con.close();
        return ERROR;
       }
    }


    public ResultSet listSelf(){
        if(UserType==STAFF){
            PreparedStatement ps = con.prepareStatement("select ")
        }
    }
}