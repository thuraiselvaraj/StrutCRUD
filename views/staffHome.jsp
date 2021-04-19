<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
      <title> Staff Home Page</title>
    </head>
    <body>
      <p>Staff Home</p>
      <a href="/StrutCRUD/staff.action?ActionType=GetStaff">Edit your details</a>
      <a href="/StrutCRUD/staff.action?ActionType=ListStudents">List all students</a>
      <a href="/StrutCRUD/staff.action?ActionType=DisplayStaff">Get Self Details</a>
    </body>
</html>
   