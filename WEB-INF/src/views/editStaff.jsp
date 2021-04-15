<%@taglib prefix="s" uri="/struts-tags"%>
<html>
   <head>
      <title>Update</title>
   </head>
   <body>
      <h2>Update Staff <s:property value="Staff_Id"/></h2>
<form action=updatedetails method="post">
<b>Name:         </b><input type="text" name="StaffName" value='<s:property value=""/>'>
<b>Email:        </b><input type="email"  name="StaffEmail" value='<s:property value="uemail"/>'>  //set this to read only in staff user.
<b>Department:  </b><input type="text" name="StaffDepartment" value='<s:property value="udeg"/>'>
<b>DOB :         </b><input type="date" name="StaffDob" value='<s:property value="udeg"/>'>
<b>Phone_no:     </b><input type="text" name="StaffPhone_no" value='<s:property value="udeg"/>'>
<b>Educational_qualification:</b><input type="text" name="StaffEducational_qualification" value='<s:property value="udeg"/>'>
<b>Staff_Id</b><input type="text" name="Staff_Id" value='<s:property value="udeg"/>'>
<button name="submitType" value="update" type="submit">Update</button>
</form>
   </body>
</html>
