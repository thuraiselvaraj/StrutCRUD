package com.models;
public class UserMetaBean {
    private int Id=0;
    private byte Type;
    private String SessionKey="";
    private String StaffDepartment="NULL";

    public UserMetaBean(){
        System.out.println("Instantiating the UserMeta");
    }

    public void setSessionKey(String SessionKey){
        this.SessionKey=SessionKey;
    }

    public String getSessionKey(){
        return SessionKey;
    }

    public void setDepartment(String StaffDepartment){
        this.StaffDepartment=StaffDepartment;
    }

    public String getDepartment(){
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

    public String toString(){
        return Id+" "+Type+" "+SessionKey;
    }
}
