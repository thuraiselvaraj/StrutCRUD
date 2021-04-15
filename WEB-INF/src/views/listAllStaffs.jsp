<%@taglib prefix="s" uri="/struts-tags"%>
<html>
   <head>
      <title>Update</title>
   </head>
   <body>
       
<s:if test="">
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