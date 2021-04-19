<%@taglib prefix="s" uri="/struts-tags"%>
<html>
   <head>
      <title>CreateStudent</title>
   </head>
   <body>
      <h2>CreateStudent</h2>
<form action="/StrutCRUD/staff.action?ActionType=CreateStudent" method="post">
<br/>Name:         </b><input type="text" name="StudentName" >
<br/>Email:        </b><input type="email"  name="StudentEmail" >
<br/>DOB :         </b><input type="date" name="StudentDob" >
<br/>Phone_no:     </b><input type="text" name="StudentPhone_no" >
<br/>Address:</b><input type="text" name="StudentAddress" >
<br/>Student_id</b><input type="number" name="Student_id" >
<button type="submit">Create</button>
</form>
   </body>
</html>
