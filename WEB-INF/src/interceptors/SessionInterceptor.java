package com.interceptors;
import java.util.*;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor; 
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;
import com.actions.GetMeta;
import com.models.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import com.models.UserMetaBean;
import com.opensymphony.xwork2.util.ValueStack;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class SessionInterceptor extends AbstractInterceptor{
    Connection con;
    public SessionInterceptor(){
        con=DBConnection.getConnection();
    }

    public String intercept(ActionInvocation invocation)throws Exception {
    //   final ActionContext context = invocation.getInvocationContext();
    //   Map<String,Object> parameters = (Map<String,Object>)context.get(ActionContext.PARAMETERS);
    //   String session_key=parameters.get("SessionKey").toString();
    //   

        ActionContext context = invocation.getInvocationContext();
        String session_key="";
        HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);
        Cookie[] cookies = null;
        cookies = request.getCookies();
        if(cookies==null){cookies=new Cookie[]{};}
        for(Cookie ck:cookies){
            System.out.println(ck.getName()+"  "+ck.getValue());
            if(ck.getName().equals("SessionKey")){
                System.out.println("Found");
                session_key=ck.getValue();
                break;
            }
        }
        PreparedStatement ps=con.prepareStatement("select _id,type from session_table where session_key=?");
        ps.setString(1,session_key);
        System.out.println(ps);
        ResultSet rs=ps.executeQuery();
        if(rs.next()){
           System.out.println("Found session");
           GetMeta meta=(GetMeta)invocation.getAction();
           UserMetaBean um_bean=meta.getUserMeta();
           um_bean.setId(rs.getInt("_id"));
           um_bean.setType((byte)rs.getInt("type"));
           um_bean.setSessionKey(session_key);
           rs.close();
           ps.close();
       }
      else{
          //redirect user to login page here
          
          System.out.println("Cannot find the session");
          response.sendRedirect("login.jsp");
      }
      return invocation.invoke();  

    }
}
