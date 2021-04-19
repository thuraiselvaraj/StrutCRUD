<%@taglib prefix="s" uri="/struts-tags"%>
<html>
   <head>
      <title>Update</title>
   </head>
   <body>
      <h2>Update Staff <s:property value="Staff_Id"/></h2>
<form action=/StrutCRUD/staff.action?ActionType=UpdateStaff method="post">
<br/>Name:         </b><input type="text" name="StaffName" value='<s:property value="staffBean.StaffName"/>'>
<br/>Email:        </b><input type="email"  value='<s:property value="staffBean.StaffEmail"/>' readonly>  //set this to read only in staff user.
<br/>Department:  </b><input type="text" value='<s:property value="staffBean.StaffDepartment"/>' readonly>
<br/>DOB :         </b><input type="date" name="StaffDob" value='<s:property value="staffBean.StaffDob"/>'>
<br/>Phone_no:     </b><input type="text" name="StaffPhone_no" value='<s:property value="staffBean.StaffPhone_no"/>'>
<br/>Educational_qualification:</b><input type="text" name="StaffEducational_qualification" value='<s:property value="staffBean.StaffEducational_qualification"/>'>
<br/>Staff_Id</b><input type="text" value='<s:property value="staffBean.Staff_Id"/>' readonly>
<button type="submit">Update</button>
</form>
   </body>
</html>
