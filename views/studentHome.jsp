<%@taglib prefix="s" uri="/struts-tags"%>
<html>
   <head>
      <title>Show Student</title>
   </head>
   <body>
      <h2>Show Student <s:property value="studentBean.Student_id"/></h2>
<br/>Name:         </b><s:property value="studentBean.StudentName"/>
<br/>Email:        </b><s:property value="studentBean.StudentEmail"/>  //set this to read only in staff user.
<br/>Department:  </b><s:property value="studentBean.StudentDepartment"/>
<br/>DOB :         </b><s:property value="studentBean.StudentDob"/>
<br/>Phone_no:     </b><s:property value="studentBean.StudentPhone_no"/>
<br/>Address:</b><s:property value="studentBean.StudentAddress"/>
<br/>Student_Id</b><s:property value="studentBean.Student_id"/>
<br/><a href='/StrutCRUD/logout.action'>logout</a></br>
</form>
   </body>
</html>
