<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <title>Login Page</title>
    </head>
    <body>
      <s:form action="login">
        <input type="email" placeholder="enter email here" name="Email" lable="Email"  required>
        <br/>
        <input type="password" placeholder="Enter Password" name="Password" label="Password" required>
        <br/>
        <button type="submit">Login</button>
      </s:form>

      
    </body>
</html>
   