<%@taglib prefix="s" uri="/struts-tags"%>
<html>
   <head>
      <title>Update</title>
   </head>
   <body>
      <h2>Update Student <s:property value="Student_Id"/></h2>
<form action=updatedetails method="post">
<b>Name:         </b><input type="text" name="StudentName" value='<s:property value=""/>'>
<b>Email:        </b><input type="email"  name="StudentEmail" value='<s:property value="uemail"/>'>  //set this to read only in staff user.
<b>Department:  </b><input type="text" name="StudentDepartment" value='<s:property value="udeg"/>'>
<b>DOB :         </b><input type="date" name="StudentDob" value='<s:property value="udeg"/>'>
<b>Phone_no:     </b><input type="text" name="StudentPhone_no" value='<s:property value="udeg"/>'>
<b>Educational_qualification:</b><input type="text" name="StudentEducational_qualification" value='<s:property value="udeg"/>'>
<b>Student_Id</b><input type="text" name="Student_Id" value='<s:property value="udeg"/>'>
<button name="submitType" value="update" type="submit">Update</button>
</form>
   </body>
</html>