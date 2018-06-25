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
	$(document).ready(function() {

						
						var showChar = 30;
						var ellipsestext = "...";
						var moretext = "more";
						var lesstext = "less";
						$('.more').each(function() {
											var content = $(this).html();

											if (content.length > showChar) {

												var c = content.substr(0,
														showChar);
												var h = content.substr(
														showChar - 1,
														content.length
																- showChar + 1);

												var html = c
														+ '<span class="moreellipses">'
														+ ellipsestext
														+ '&nbsp;</span><span class="morecontent"><span>'
														+ h
														+ '</span>&nbsp;&nbsp;<a href="" class="morelink">'
														+ moretext
														+ '</a></span>';

												$(this).html(html);
											}

										});

						$(".morelink").click(function() {
							if ($(this).hasClass("less")) {
								$(this).removeClass("less");
								$(this).html(moretext);
							} else {
								$(this).addClass("less");
								$(this).html(lesstext);
							}
							$(this).parent().prev().toggle();
							$(this).prev().toggle();
							return false;
						});
						
						

					});

	function fun(x) {

		var temp = window.getComputedStyle(document.querySelector('.' + x),
				':before');

	}
</script>

<style>
ul {
	list-style: none;
}

ul li:before {
	content: "\27A4 \0020";
}
/* ul li:after {
		content: "\25bc \0020";
	     }	 	     
	      */
a.morelink {
	text-decoration: none;
	outline: none;
	color: green;
}

.morecontent span {
	display: none;
}

.comment {
	width: 400px;
	margin: 10px;
}
</style>
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

			<br /> Existing Roles:
			<table
				class="w3-table-all w3-hoverable w3-striped w3-bordered w3-border"
				id="table">

				<tr class="w3-light-grey">
					<th>Role Name</th>
					<th>Role Description</th>
					<th>is Active</th>
					<th>Access to Screens</th>
					<th>Action</th>
				</tr>
				
					<tr class="">
						<td>Palavi</td>
						<td>USER</td>
						<td>Yes</td>
						<td class="comment more">User Group
Display,
Display Status,
Display Images,
Capture Demo Unit,
Update Demo Unit,
Interface,
Product Master,
Stock Data to application at retail level,
ISP Quiz,
Define Quiz & Capture Questions,
Define Quiz & Upload Questions,
Assign Quiz to User,
Take Quiz,
Publish Quiz,
Monitor & track Quiz
Audit,
Survey,
Sales History,
Attendance History,
Target Vs. Achievement Report
Track User Location,
User Management,
Define Role,
User,
User Role Mapping,
User Heirarchy,
Low SKU Alert,
Repeated Low Attendance Alert,
Repeated Suspecial Attendance Alert,
Define Survey,
Define Questions for Survey,
Publish Survey,
Take Survey,
view Report Survey,
Analyze Survey Result,
Define Announcements,</td>
						<td><a href="#"
							class="w3-margin-right"><i
								class="fa fa-edit fa-fw fa-2x w3-hover-text-orange"></i></a> <a
							href="#" class=" w3-margin-right"><i
								class="fa fa-minus fa-fw fa-2x w3-hover-text-red"></i></a></td>
					</tr>
					
					
					
					<tr class="">
						<td>divvyas</td>
						<td>DBA</td>
						<td>Yes</td>
						<td class="comment more">User Group
Capture Checkout,
Sales Tracking,
Capture Zero Sales,
Block Sales,
Capture Sales Info. (Customer/mobile),
Capture Customer Info.,
Duplicate,
Duplicate Sales,
Duplicate Serial No.,
Duplicate Mobile No.,
Capture SKU,
Capture Demo Unit,
Update Demo Unit,
Interface,
Product Master,
Incentive,
Alerts,
Notification,
Generate Alerts,
Define Incentive,
Define Target,
ISP Quiz,
Define Quiz & Capture Questions,
Define Quiz & Upload Questions,
Assign Quiz to User,
Audit,
Survey,
Sales History,
Attendance History,
User Management,
Define Role,
User,
User Role Mapping,
User Heirarchy,
Define Survey,
Define Questions for Survey,
Publish Survey,
Take Survey,
view Report Survey,
Define Alerts,
Define Notification,
Define Announcements,</td>
						<td><a href="#"
							class="w3-margin-right"><i
								class="fa fa-edit fa-fw fa-2x w3-hover-text-orange"></i></a> <a
							href="#" class=" w3-margin-right"><i
								class="fa fa-minus fa-fw fa-2x w3-hover-text-red"></i></a></td>
					</tr>
					

<tr class="">
						<td>vipul</td>
						<td>USER</td>
						<td>Yes</td>
						<td class="comment more">
Capture SKU,
Display,
Display Status,
Display Images,
Capture Demo Unit,
Update Demo Unit,
Interface,
Product Master,
Stock Data to application at retail level,
Consumed Stock data to Inventory management system,
Report,
Incentive,
Alerts,
Notification,
Generate Alerts,
Define Incentive,
Define Target,
ISP Quiz,
Define Quiz & Capture Questions,
Define Quiz & Upload Questions,
Assign Quiz to User,
Take Quiz,
Publish Quiz,
Monitor & track Quiz
Audit,
Survey,
Sales History,
Attendance History,
Target Vs. Achievement Report
Track User Location,
Low SKU Alert,
Repeated Low Attendance Alert,
Repeated Suspecial Attendance Alert,Pending Attendance
</td>
						<td><a href="#"
							class="w3-margin-right"><i
								class="fa fa-edit fa-fw fa-2x w3-hover-text-orange"></i></a> <a
							href="#" class=" w3-margin-right"><i
								class="fa fa-minus fa-fw fa-2x w3-hover-text-red"></i></a></td>
					</tr>

					
					
					
<%-- 				 <c:forEach var="list" items="${userRole}"> 
					<tr class="">

						<td>${list.roleName}</td>
						<td>${list.roleDescription}</td>
						<td>${list.isActive}</td>
						<td class="comment more">${list.screenId}</td>

						<td><a href="#"
							class="w3-margin-right"><i
								class="fa fa-edit fa-fw fa-2x w3-hover-text-orange"></i></a> <a
							href="/Gionee/POC/deleteRole/${list.id}" class=" w3-margin-right"><i
								class="fa fa-minus fa-fw fa-2x w3-hover-text-red"></i></a></td>
					</tr>
				 </c:forEach>
 --%>
			</table>
"<!-- /Gionee/POC/screenAssign -->"
			<br /> <br />
			
				
			<a href ="<c:url value='/newRole' />"class="button">Add</a>
			
			<%-- <a href="<c:url value='/usermenuright'/>"
			<button class="w3-btn w3-right">Add</button>
			</a> --%>
			<br />
			<br />
			


	<!-- Footer -->
		<%@include file="Footer.jsp"%>


		</div>
	

	</div>
</body>

</html>