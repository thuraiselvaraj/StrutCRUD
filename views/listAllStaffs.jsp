<%@taglib prefix="s" uri="/struts-tags"%>
<html>
   <head>
      <title>Update</title>
   </head>
   <body>
       
   <s:if test="beanList.size()>0">
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
				<s:iterator value="beanList">
					<tr>
						<td><s:property value="StaffName" /></td>
						<td><s:property value="Staff_Id" /></td>
						<td><s:property value="StaffEmail" /></td>
						<td><s:property value="StaffDepartment" /></td>
						<td><s:property value="StaffDob" /></td>
						<td><s:property value="StaffPhone_no" /></td>
						<td><s:property value="StaffEducational_qualification" /></td>
						<td>
							<a href='admin.action?ActionType=GetStaff&StaffEmail=<s:property value="StaffEmail"/>'>
								<button class="button-update">Edit</button>
							</a>
							<!-- <a href='deleterecord.action?ActionType=GetStaff&StaffEmail=<s:property value="StaffEmail"/>'>
								<button class="button-delete">Delete</button>
							</a> -->
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