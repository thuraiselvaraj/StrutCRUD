package com.models;
public class UserMetaBean {
    private int Id;
    private byte Type;
    private String SessionKey="";
    private String StaffDepartment="";

    public void setSessionKey(String SessionKey){
        this.SessionKey=SessionKey;
    }

    public String getSessionKey(){
        return SessionKey;
    }

    public void setStaffDepartment(String StaffDepartment){
        this.StaffDepartment=StaffDepartment;
    }

    public String getStaffDepartment(){
        return StaffDepartment;
    }

    public int getId(){
        return Id;
    }

    public byte getType(){
        return Type;
    }

    public void setId(int Id){
        this.Id=Id;
    }

    public void setType(byte Type){
        this.Type=Type;
    }


}
