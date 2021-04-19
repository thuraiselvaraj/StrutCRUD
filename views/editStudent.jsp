<%@taglib prefix="s" uri="/struts-tags"%>
<html>
   <head>
      <title>Update</title>
   </head>
   <body>
      <h2>Update Student <s:property value="studentBean.Student_id"/></h2>
<form action=/StrutCRUD/staff.action?ActionType=UpdateStudent method="post">
<br/>Name:         </b><input type="text" name="StudentName" value='<s:property value="studentBean.StudentName"/>'>
<br/>Email:        </b><input type="email"  name="StudentEmail" value='<s:property value="studentBean.StudentEmail"/>'>  //set this to read only in staff user.
<br/>Department:  </b><input type="text" name="StudentDepartment" value='<s:property value="studentBean.StudentDepartment"/>'>
<br/>DOB :         </b><input type="date" name="StudentDob" value='<s:property value="studentBean.StudentDob"/>'>
<br/>Phone_no:     </b><input type="text" name="StudentPhone_no" value='<s:property value="studentBean.StudentPhone_no"/>'>
<br/>Address:</b><input type="text" name="StudentAddress" value='<s:property value="studentBean.StudentAddress"/>'>
<br/>Student_Id</b><input type="text" name="Student_id" value='<s:property value="studentBean.Student_id"/>'>
<input type="hidden" name="UpdateKey" value='<s:property value="UpdateKey"/>'/>
<button type="submit">Update</button>
</form>
   </body>
</html>
