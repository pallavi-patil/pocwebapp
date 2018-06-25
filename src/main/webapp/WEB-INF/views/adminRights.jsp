<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Gionee</title>
<script>
	document.getElementById('id01').style.display = 'block';
</script>
</head>
<body class="w3-light-grey w3-content" style="max-width: 1600px"
	ng-app="myApp" ng-Controller="myCtrl">

	<!-- Sidenav/menu -->
	<!-- Sidenav/menu -->
	<%@include file="nav.jsp"%>

	<!-- Overlay effect when opening sidenav on small screens -->
	<div class="w3-overlay w3-hide-large w3-animate-opacity"
		onclick="w3_close()" style="cursor: pointer" title="close side menu"
		id="myOverlay"></div>

	<!-- !PAGE CONTENT! -->
<div class="w3-main w3-border" style="margin-left: 340px; margin-right: 40px">
		<!-- Header -->
		<%@include file="Header.jsp"%>


		<div class="w3-row-padding w3-border w3-border-deep-orange" id="myMain">
			<br /> <b>User List</b> <br /> <br />

			 <table class="w3-table-all w3-hoverable w3-striped w3-bordered w3-border" id="table">
   <tr class="w3-light-grey"><th>Username</th><th>Password</th><th>Email</th><th>User Level</th><th>User Group</th><th>Role</th><th>Edit</th><th>Delete</th></tr>
   <c:forEach var="list" items="${userList}">
   <tr class="w3-hover-blue"  id=${list.id}><td>${list.usename}</td><td>${list.password}</td><td>${list.email}</td><td>${list.expiryDate}</td><td>${list.actor}</td><td>${list.role}</td>
   
    <td><a href="/Gionee/POC/editUser/${list.id}"><i class="fa fa-edit fa-fw fa-2x w3-hover-text-orange"></i></a></td>
       <td><a href="/Gionee/POC/deleteUser/${list.id}"><i class="fa fa-minus fa-fw fa-2x w3-hover-text-red"></i></a></td>
   </tr>
   </c:forEach>
   
   </table>
			<br> <a
				onclick="document.getElementById('id01').style.display='block'"
				class="w3-btn w3-right"> Add New User</a>

			<div id="id02" class="w3-modal">
				<div class="w3-modal-content">
					<div
						class="w3-container w3-padding w3-border w3-border-red w3-hover-border-blue">
						<span
							onclick="document.getElementById('id02').style.display='none'"
							class="w3-closebtn">&times;</span>
						<h4>Edit User</h4>
						<form:form action="/Gionee/addUser" method="Post"
							id="form" commandName="regisBean">
							<br />
							<div class="w3-row">



								<lable class="w3-col" style="width:150px">username</lable>
								<form:input class="w3-rest w3-input w3-border" id="username"
									path="usename" />
								<br />



								<lable class="w3-col" style="width:150px">Password</lable>
								<form:input type="text" class="w3-rest w3-input w3-border"
									path="password" id="passwordfield" />

								<lable class="w3-col" style="width:150px">Email</lable>
								<form:input type="text" class="w3-rest w3-input w3-border"
									path="email" id="passwordfield" />
								<br /> <input type="submit" value="Submit"
									class="w3-btn w3-grey" style="width: 150px"><br />

							</div>
						</form:form>
					</div>
				</div>

			</div>
			<div id="id01" class="w3-modal">
				<div class="w3-modal-content">
					<div
						class="w3-container w3-padding w3-border w3-border-red w3-hover-border-blue">
						<span
							onclick="document.getElementById('id01').style.display='none'"
							class="w3-closebtn">&times;</span>
						<h4>Add new User</h4>
						<form:form action="/Gionee/POC/addUser" method="POST"
							commandName="regisBean">
							<br />
							<div class="w3-row">



							
								<form:input class="w3-rest w3-input w3-border" placeholder="UserName" path="usename" />
								<br />



								
								<form:input type="text" placeholder="Password" class="w3-rest w3-input w3-border"
									path="password" />

								<br />
								<form:input type="text" placeholder="E-mail" class="w3-rest w3-input w3-border"
									path="email" />		<br />
									
									
									<select name="expiryDate" style="width:100%;height:7%"
								class="w3-select w3-border w3-hover-grayscale">

								<option selected="selected" disabled>Select User Level</option>
								
									<option value="L1">L1</option>
									<option value="L2">L2</option>
									<option value="L3">L3</option>
									<option value="L4">L4</option>
									<option value="L5">L5</option>
									<option value="L6">L6</option>
									<option value="L7">L7</option>
								
							</select>	<br />	<br />
	<form:select path="actor" style="width:100%;height:7%"
								class="w3-select w3-border w3-hover-grayscale" required="required">

								<option selected="selected" disabled>Select UserGroup</option>
								<c:forEach items="${orderList}" var="role">
									<option value="${role.amount}">${role.amount}</option>
								</c:forEach>
							</form:select>
						
								
								<br />	<br />
						 <form:select path="role" style="width:100%;height:7%"
								class="w3-select w3-border w3-hover-grayscale">

								<option selected="selected" disabled>Select Role</option>
								<c:forEach items="${userRole}" var="role">
									<option value="${role.roleName}">${role.roleName}</option>
								</c:forEach>
							</form:select><br /><br /> 
								 <input type="submit" value="Submit"
									class="w3-btn w3-grey" style="width: 150px"><br />

							</div>
						</form:form>
					</div>
				</div>

			</div>
		</div>
<!-- Footer -->
	<%@include file="Footer.jsp"%>
	</div>
	


</body>

</html>