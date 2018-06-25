<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="include.jsp"%>

<%@ page isELIgnored="false"%>
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
			<span class="w3-text-green">${message}</span><br />

			<div class="w3-container">
				User Role:
				<table
					class="w3-table-all w3-hoverable w3-striped w3-bordered w3-border"
					id="table">
					<tr class="w3-light-grey">
						<th>User Name</th>
						<th>Role Name</th>
						<th>Action</th>
					</tr>
					<c:forEach items="${users}" var="user">
						<tr class="">

							<td>${user[0]}</td>
							<td>${user[1]}</td>
							<td onclick="document.getElementById('id02').style.display='block'"><a href="" class="w3-margin-right"><i
									class="fa fa-edit fa-fw fa-2x w3-hover-text-orange"></i></a> <a
								href="" class=" w3-margin-right"><i
									class="fa fa-minus fa-fw fa-2x w3-hover-text-red"></i></a></td>
						</tr>
					</c:forEach>

				</table>
				<br />
				<%-- <button
					onclick="document.getElementById('id01').style.display='block'"
					class="w3-btn w3-right">Add</button>
				<br />
				<br />

			</div>


			<div id="id01" class="w3-modal">
				<div class="w3-modal-content">
					<div
						class="w3-container w3-padding w3-border w3-border-red w3-hover-border-blue">
						<span
							onclick="document.getElementById('id01').style.display='none'"
							class="w3-closebtn">&times;</span> User Role Mapping <br />
						<br />
						<form:form class="w3-container"
							action="/Gionee/POC/screenUserAssign" method="post"
							commandName="regisBean">

							<form:select path="usename" style="width:100%;height:7%"
								class="w3-select w3-border w3-hover-grayscale">

								<option selected="selected" disabled>Select UserName</option>
								<c:forEach items="${users}" var="role">
									<option value="${role}">${role}</option>
								</c:forEach>
							</form:select>
							<br />
							<br />
							<form:select path="role" style="width:100%;height:7%"
								class="w3-select w3-border w3-hover-grayscale">

								<option selected="selected" disabled>Select Role</option>
								<c:forEach items="${userRole}" var="role">
									<option value="${role.roleName}">${role.roleName}</option>
								</c:forEach>
							</form:select>
							<br />


							<p>
								<label class="w3-validate">is Active?</label><input
									class="w3-check" type="checkbox">
							</p>

							<form:input type="hidden" value="{{screenid}}" path="screenId"
								class="w3-btn w3-hover-blue" style="width:100%" />
							<input type="submit" value="Add" ng-Click="check()"
								class="w3-btn w3-hover-blue w3-right" style="width: 20%">
							<br />
						</form:form>
					</div>
				</div>
			</div>
<div id="id02" class="w3-modal">
				<div class="w3-modal-content">
					<div
						class="w3-container w3-padding w3-border w3-border-red w3-hover-border-blue">
						<span
							onclick="document.getElementById('id02').style.display='none'"
							class="w3-closebtn">&times;</span> User Role Mapping <br />
						<br />
						<form:form class="w3-container"
							action="/Gionee/POC/screenUserAssign" method="post"
							commandName="regisBean">

							<form:select path="usename" style="width:100%;height:7%"
								class="w3-select w3-border w3-hover-grayscale">

								<option selected="selected" disabled>Select UserName</option>
								<c:forEach items="${userList}" var="role">
									<option value="${role}">${role}</option>
								</c:forEach>
							</form:select>
							<br />
							<br />
							<form:select path="role" style="width:100%;height:7%"
								class="w3-select w3-border w3-hover-grayscale">

								<option selected="selected" disabled>Select Role</option>
								<c:forEach items="${userRole}" var="role">
									<option value="${role.roleName}">${role.roleName}</option>
								</c:forEach>
							</form:select>
							<br />


							<p>
								<label class="w3-validate">is Active?</label><input
									class="w3-check" type="checkbox">
							</p>

							<form:input type="hidden" value="{{screenid}}" path="screenId"
								class="w3-btn w3-hover-blue" style="width:100%" />
							<input type="submit" value="Update" ng-Click="check()"
								class="w3-btn w3-hover-blue w3-right" style="width: 20%">
							<br />
						</form:form>
					</div>
				</div>
			</div>
 --%>			
 
 <br />

	<%@include file="Footer.jsp"%>

		</div>
		<!-- Footer -->
	

	</div>
</body>

</html>