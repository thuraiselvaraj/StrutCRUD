<%@taglib prefix="s" uri="/struts-tags"%>
<html>
   <head>
      <title>CreateStaff</title>
   </head>
   <body>
      <h2>CreateStaff</h2>
<form action='/StrutCRUD/admin?ActionType=CreateStaff' method="post">
<br/>Name:         </b><input type="text" name="StaffName" >
<br/>Email:        </b><input type="email"  name="StaffEmail" >  //set this to read only in staff user.
<br/>Department:  </b><input type="text" name="StaffDepartment" >
<br/>DOB :         </b><input type="date" name="StaffDob" >
<br/>Phone_no:     </b><input type="text" name="StaffPhone_no" >
<br/>Educational_qualification:</b><input type="text" name="StaffEducational_qualification" >
<br/>Staff_Id</b><input type="number" name="Staff_Id" >
<button type="submit">Create</button>
</form>
   </body>
</html>
