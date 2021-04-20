<%@taglib prefix="s" uri="/struts-tags"%>
<html>
   <head>
      <title>Update</title>
   </head>
   <body>
      <h2>Update Staff <s:property value="Staff_Id"/></h2>
<form action='/StrutCRUD/admin.action?ActionType=UpdateStaff' method="post">
<br/>Name:         </b><input type="text" name="StaffName" value='<s:property value="staffBean.StaffName"/>'>
<br/>Email:        </b><input type="email"  name="StaffEmail" value='<s:property value="staffBean.StaffEmail"/>'>  //set this to read only in staff user.
<br/>Department:  </b><input type="text" name="StaffDepartment" value='<s:property value="staffBean.StaffDepartment"/>'>
<br/>DOB :         </b><input type="date" name="StaffDob" value='<s:property value="staffBean.StaffDob"/>'>
<br/>Phone_no:     </b><input type="text" name="StaffPhone_no" value='<s:property value="staffBean.StaffPhone_no"/>'>
<br/>Educational_qualification:</b><input type="text" name="StaffEducational_qualification" value='<s:property value="staffBean.StaffEducational_qualification"/>'>
<br/>Staff_Id</b><input type="text" name="Staff_Id" value='<s:property value="staffBean.Staff_Id"/>'>
<input type="hidden" name="UpdateKey" value='<s:property value="UpdateKey"/>'/>
<button type="submit">Update</button>
</form>
   </body>
</html>
