<%@taglib prefix="s" uri="/struts-tags"%>
<html>
   <head>
      <title>Update</title>
   </head>
   <body>
       
   <s:if test="studentBeanList.size()>0">
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
				<s:iterator value="studentBeanList">
					<tr>
						<td><s:property value="StudentName" /></td>
						<td><s:property value="Student_id" /></td>
						<td><s:property value="StudentEmail" /></td>
						<td><s:property value="StudentDepartment" /></td>
						<td><s:property value="StudentDob" /></td>
						<td><s:property value="StudentPhone_no" /></td>
						<td><s:property value="StudentAddress" /></td>
						<td>
							<a href='/StrutCRUD/staff.action?ActionType=GetStudent&StudentEmail=<s:property value="StudentEmail"/>'>
								<button class="button-update">Edit</button>
							</a>
							<a href='/StrutCRUD/staff.action?ActionType=DeleteStudent&StudentEmail=<s:property value="StudentEmail"/>'>
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

		<a href='/StrutCRUD/logout.action'>logout</a></br>



</form>
   </body>
</html>