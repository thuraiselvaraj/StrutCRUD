package com.actions;
import com.opensymphony.xwork2.ActionSupport;
import com.models.CommonModel;
public class HelloWorldAction extends ActionSupport {
    private MessageStore messageStore;
    public String execute() {
        messageStore = new MessageStore() ;
        return SUCCESS;
        // Modifier and Type	Constant Field	Value
        // public static final String	ERROR	"error"
        // public static final String	INPUT	"input"
        // public static final String	LOGIN	"login"
        // public static final String	NONE	"none"
        // public static final String	SUCCESS	"success"
    }
    public MessageStore getMessageStore() {
        return messageStore;
    }
}   