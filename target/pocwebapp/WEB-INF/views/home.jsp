<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="include.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page isELIgnored="false" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Capgemini</title>
<style>
.myMain {
  background-image:<img src="D:/Java user Mangment Workspace/pocwebapp/src/main/webapp/static/images/gionee.gif" alt="Mountain View">
	/* background-image: url("<c:url value='D:/Java user Mangment Workspace/pocwebapp/src/main/webapp/static/images/gionee.gif'/>"); */
	min-height: 100%;
	height: 500px;
	background-position: center;
	background-size: cover;
	opacity: 0.03;
	filter: alpha(opacity = 60);
}

#myForm {
	opacity: 1.0;
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


		<div class="w3-row-padding w3-center w3-text-blue">
<h3 ><i>Hello ${name}
 
 </i></h3><br />
<span></span>
			<div class=" w3-container w3-margin-bottom myMain">
				<div class="w3-container w3-white">
					<div class="w3-container w3-margin w3-text-deep-orange" id="myHome">

						
					</div>

				</div>
			</div>


		</div>
		
		
		
			<%@ include file="Footer.jsp"%>
	</div>


	



</body>
</html>