<%@taglib prefix="s" uri="/struts-tags"%>
<html>
   <head>
      <title>Update</title>
   </head>
   <body>
      <h2>Update Student <s:property value="Student_Id"/></h2>
<b>Name:         </b><input type="text" name="StudentName" value='<s:property value=""/>'>
<b>Email:        </b><input type="email"  name="StudentEmail" value='<s:property value="uemail"/>'>  //set this to read only in staff user.
<b>Student_Id</b><input type="text" name="Student_Id" value='<s:property value="udeg"/>'>
<b>Department:  </b><input type="text" name="StudentDepartment" value='<s:property value="udeg"/>'>
<b>DOB :         </b><input type="date" name="StudentDob" value='<s:property value="udeg"/>'>
<b>Phone_no:     </b><input type="text" name="StudentPhone_no" value='<s:property value="udeg"/>'>
<b>Educational_qualification:</b><input type="text" name="StudentEducational_qualification" value='<s:property value="udeg"/>'>


<s:if test="noData==true">
			<table>
				<thead>
					<tr style="background-color: #E0E0E1;">
						<th>Name</th>
                        <th>Student_Id</th>
						<th>Email</th>
						<th>Department</th>
						<th>DOB </th>
						<th>Phone_no</th>
						<th>Educational_qualification</th>
                        
					</tr>
				</thead>
				<s:iterator value="">
					<tr>
						<td><s:property value="srNo" /></td>
						<td><s:property value="uname" /></td>
						<td><s:property value="uemail" /></td>
						<td><s:property value="upass" /></td>
						<td><s:property value="udeg" /></td>
						<td>
							<a href="updatedetails.action?submitType=updatedata&uemail=<s:property value="uemail"/>">
								<button class="button-update">Update</button>
							</a>
							<a href="deleterecord.action?uemail=<s:property value="uemail"/>">
								<button class="button-delete">Delete</button>
							</a>
						</td>
					</tr>
				</s:iterator>
			</table>
		</s:if>
		<s:else>
			There is no details.
		</s:else>





</form>
   </body>
</html>