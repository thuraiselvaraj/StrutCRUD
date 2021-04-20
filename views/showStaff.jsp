<%@taglib prefix="s" uri="/struts-tags"%>
<html>
   <head>
      <title>Display</title>
   </head>
   <body>
<h2>Staff Details<s:property value="staffBean.Staff_Id"/></h2>
<br/>Name:         </b><s:property value="staffBean.StaffName"/>
<br/>Email:        </b><s:property value="staffBean.StaffEmail"/> 
<br/>Department:  </b><s:property value="staffBean.StaffDepartment"/>
<br/>DOB :         </b><s:property value="staffBean.StaffDob"/>
<br/>Phone_no:     </b><s:property value="staffBean.StaffPhone_no"/>
<br/>Educational_qualification:</b><s:property value="staffBean.StaffEducational_qualification"/>
<br/>Staff_Id</b><s:property value="staffBean.Staff_Id"/>
<a href='/StrutCRUD/logout.action'>logout</a></br>
</form>
   </body>
</html>
