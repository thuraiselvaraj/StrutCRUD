<%@taglib prefix="s" uri="/struts-tags"%>
<html>
   <head>
      <title>CreateStaff</title>
   </head>
   <body>
      <h2>CreateStaff</h2>
<form action="admin?ActionType=CreateStaff" method="post">
<b>Name:         </b><input type="text" name="StaffName" >
<b>Email:        </b><input type="email"  name="StaffEmail" >  //set this to read only in staff user.
<b>Department:  </b><input type="text" name="StaffDepartment" >
<b>DOB :         </b><input type="date" name="StaffDob" >
<b>Phone_no:     </b><input type="text" name="StaffPhone_no" >
<b>Educational_qualification:</b><input type="text" name="StaffEducational_qualification" >
<b>Staff_Id</b><input type="number" name="Staff_Id" >
<button type="submit">Create</button>
</form>
   </body>
</html>
