package com.actions;
import com.opensymphony.xwork2.ActionSupport;
public class DummyAction extends ActionSupport{
    private Integer Id=null;

    public void setId(Integer Id){
        this.Id=Id;
        
    }

    public Integer getId(){
        System.out.println("THis is the logger"+this.Id);
        return Id;
    }

    public Integer getIds(){
        System.out.println("THis is the logger22"+this.Id);
        return null;
    }

    public String execute() {
        return SUCCESS;
    }
    
}

