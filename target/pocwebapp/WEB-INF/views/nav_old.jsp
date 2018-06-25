<!-- Sidenav/menu -->
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<nav
	class="w3-sidenav w3-text-white w3-collapse w3-deep-orange w3-animate-left"
	style="z-index: 3; width: 300px;" id="mySidenav">
	<br>
	<p class="w3-right w3-hide" onClick="openchat()" id="myChatLogo">
		<i class="fa fa-comments fa-fw w3-margin-right"></i>
	</p>
	<div class="w3-container">
		<a href="#" onclick="w3_close()"
			class="w3-hide-large w3-right w3-jumbo w3-padding" title="close menu">
			<i class="fa fa-remove"></i>
		</a><img src="<c:url value="/images/Gionee.png" />" alt="TestDisplay"/>
 
		
		
		<br>
		<br>


		<hr style="width: 100%; border: 3px solid white" class="w3-round">

		<h3>
			<b>Gionee</b>
		</h3>
		<p class=""></p>
	</div>
	<div class="w3-dropdown-hover">
		<a href="#" onclick="w3_close()"
			class="w3-padding w3-text-white w3-border w3-round-xxlarge" id="0"><i
			class="fa fa-diamond fa-fw w3-margin-right"></i>Master</a>
		<div id="myAdmin" style="margin-left:10%;width:80%"
			class="w3-dropdown-content w3-orange w3-animate-zoom w3-right w3-padding w3-medium">
			<a href="/Gionee/POC/listDealer" onclick="w3_close()"
				class="w3-padding w3-border w3-round-xxlarge" id="1"><i
				class="fa fa-user-plus fa-fw w3-margin-right"></i>Partners</a> 
				<a href="/Gionee/POC/listCustomer" onclick="w3_close()"
				class="w3-padding w3-border w3-round-xxlarge" id="2"><i
				class="fa fa-street-view fa-fw w3-margin-right"></i>Customer</a> <a
				href="/Gionee/POC/listProduct" onclick="w3_close()"
				class="w3-padding w3-border w3-round-xxlarge" id="3"><i
				class="fa fa-suitcase fa-fw w3-margin-right"></i>Product</a> <a
				href="/Gionee/POC/listservice" onclick="w3_close()"
				class="w3-padding w3-border w3-round-xxlarge" id="4"><i
				class="fa fa-rss fa-fw w3-margin-right"></i>Services</a> <a
				href="/Gionee/POC/listInvestment" onClick="investment()"
				class="w3-padding w3-border w3-round-xxlarge" id="5"><i
				class="fa fa-money fa-fw w3-margin-right"></i>Investment</a> <a
				href="/Gionee/POC/listDiscount" onclick="w3_close()"
				class="w3-padding w3-border w3-round-xxlarge" id="6"><i
				class="fa fa-dot-circle-o fa-fw w3-margin-right"></i>Scheme
				Commercial</a> <a href="/Gionee/POC/listContract"
				onclick="w3_close()" class="w3-padding w3-border w3-round-xxlarge"
				id="7"><i class="fa fa-handshake-o fa-fw w3-margin-right"></i>Contract
				& Agreement</a>
		</div>
	</div>

	<div class="w3-dropdown-hover" >
		<a href="#" onclick="w3_close()"
			class="w3-padding w3-border w3-round-xxlarge" id="8"><i
			class="fa fa-file-zip-o fa-fw w3-margin-right"></i>Document</a>
		
		<div style="margin-left:10%;width:80%"
			class="w3-dropdown-content w3-orange  w3-animate-zoom w3-right w3-padding w3-medium">

			<a href="#" onClick="abcd()"
				class="w3-padding w3-border w3-round-xxlarge" id="9"><i
				class="fa fa-hourglass-half fa-fw w3-margin-right"></i>Collection</a>
			<div id="k1" class="w3-hide w3-deep-orange  w3-right w3-padding w3-medium"
				style="width: 100%">
				<a href="/Gionee/POC/selectFile" onclick="w3_close()"
					class="w3-padding w3-border w3-round-xxlarge" id="10"><i
					class="fa fa-upload fa-fw w3-margin-right"></i>Upload</a> <a
					href="/Gionee/POC/fileList" onclick="w3_close()"
					class="w3-padding w3-border w3-round-xxlarge" id="11"><i
					class="fa fa-download fa-fw w3-margin-right"></i>Import</a>

			</div>
			<a href="/Gionee/POC/associate" onclick="w3_close()"
				class="w3-padding w3-border w3-round-xxlarge" id="12"><i
				class="fa fa-asl-interpreting fa-fw w3-margin-right"></i>Associate</a> <a
				href="#" onclick="w3_close()"
				class="w3-padding w3-border w3-round-xxlarge" id="13"><i
				class="fa fa-check-circle-o fa-fw w3-margin-right"></i>Validation</a> <a
				href="#" onclick="w3_close()"
				class="w3-padding w3-border w3-round-xxlarge" id="14"><i
				class="fa fa-cube fa-fw w3-margin-right"></i>Constituition</a>


		</div>
	</div>


	<div class="w3-dropdown-hover">

		<a href="#" onclick="w3_close()"
			class="w3-padding w3-border w3-round-xxlarge" id="15"><i
			class="fa fa-hourglass-half fa-fw w3-margin-right"></i>Entity Type</a>
		<div style="margin-left:10%;width:80%"
			class="w3-dropdown-content w3-orange  w3-animate-zoom w3-right w3-padding w3-medium">

			<a href="/Gionee/POC/documentType" onClick="abcd()"
				class="w3-padding w3-border w3-round-xxlarge" id="16"><i
				class="fa fa-file fa-fw w3-margin-right"></i>Document Type</a> <a
				href="/Gionee/POC/investmentType" onclick="w3_close()"
				class="w3-padding w3-border w3-round-xxlarge" id="17"><i
				class="fa fa-star-o fa-fw w3-margin-right"></i>Investment Type</a> <a
				href="/Gionee/POC/bindingType" onclick="w3_close()"
				class="w3-padding w3-border w3-round-xxlarge" id="18"><i
				class="fa fa-asl-interpreting fa-fw w3-margin-right"></i>Binding
				Type</a>



		</div>
	</div>

	<div class="w3-dropdown-hover">
		<a href="#" onclick="w3_close()"
			class="w3-text-white w3-padding w3-border w3-round-xxlarge" id="19"><i
			class="fa fa-exchange fa-fw w3-margin-right"></i>Transactions</a>

		<div style="margin-left:10%;width:80%"
			class="w3-dropdown-content w3-orange  w3-animate-zoom w3-right w3-padding w3-medium">
			
			<a href="/Gionee/POC/orderTransList" onclick="w3_close()"
				class="w3-text-white w3-padding w3-border w3-round-xxlarge" id="20"><i
				class="fa fa-gavel fa-fw w3-margin-right"></i>Order</a> <a
				href="/Gionee/POC/invoiceList" onclick="w3_close()"
				class="w3-text-white w3-padding w3-border w3-round-xxlarge" id="21"><i
				class="fa fa-print fa-fw w3-margin-right"></i>Invoice</a> <a
				href="/Gionee/POC/paymentList" onclick="w3_close()"
				class="w3-text-white w3-padding w3-border w3-round-xxlarge" id="22"><i
				class="fa fa-usd fa-fw w3-margin-right"></i>Payment</a> <a
				href="/Gionee/POC/deliveryList" onclick="w3_close()"
				class="w3-text-white w3-padding w3-border w3-round-xxlarge" id="23"><i
				class="fa fa-truck fa-fw w3-margin-right"></i>Delivery</a>
		</div>
	</div>

	<div class="w3-dropdown-hover">
		<a href="#" onclick="w3_close()"
			class="w3-padding w3-border w3-round-xxlarge" id="24"><i
			class="fa fa-cogs fa-fw w3-margin-right"></i>Settings</a>
		<div style="margin-left:10%;width:80%"
			class="w3-dropdown-content w3-orange  w3-animate-zoom w3-right w3-padding w3-medium">
	<a href="#" onclick="w3_close()"
				class="w3-padding w3-border w3-round-xxlarge" id="25"><i
				class="fa fa-at fa-fw w3-margin-right"></i>Email settings</a> <a
				href="#" onclick="w3_close()"
				class="w3-padding w3-border w3-round-xxlarge" id="26"><i
				class="fa fa-envelope fa-fw w3-margin-right"></i>Sms Setting</a> <a
				href="#" onclick="w3_close()"
				class="w3-padding w3-border w3-round-xxlarge" id="27"><i
				class="fa fa-recycle fa-fw w3-margin-right"></i>InApp Alerts</a> <a
				href="#" onclick="w3_close()"
				class="w3-padding w3-border w3-round-xxlarge" id="28"><i
				class="fa fa-pencil-square fa-fw w3-margin-right"></i>Document
				Setting</a>
		</div>
	</div>

	<div class="w3-dropdown-hover">
		<a href="/Gionee/POC/dashboard" onclick="w3_close()"
			class="w3-text-white w3-padding w3-border w3-round-xxlarge" id="29"><i
			class="fa fa-tachometer fa-fw w3-margin-right"></i>Alerts</a>
		<!--   <div id="myAdmin" class="w3-dropdown-content w3-deep-orange w3-animate-zoom w3-right w3-padding w3-medium">
     <a href="#" onclick="w3_close()" class="w3-padding w3-border w3-round-xxlarge" id="7"><i class="fa fa-wrench fa-fw w3-margin-right"></i>Order Alert</a>
   <a href="#" onclick="w3_close()" class="w3-padding w3-border w3-round-xxlarge" id="8"><i class="fa fa-diamond fa-fw w3-margin-right"></i>Investment Alert</a>
      <a href="#" onclick="w3_close()" class="w3-padding w3-border w3-round-xxlarge" id="8"><i class="fa fa-diamond fa-fw w3-margin-right"></i>Payment Alerts</a>
            <a href="#" onclick="w3_close()" class="w3-padding w3-border w3-round-xxlarge" id="8"><i class="fa fa-diamond fa-fw w3-margin-right"></i>Invoice Alerts</a>
              <a href="#" onclick="w3_close()" class="w3-padding w3-border w3-round-xxlarge" id="8"><i class="fa fa-diamond fa-fw w3-margin-right"></i>Report Generation alerts</a>

    </div>
     -->
	</div>

	<div class="w3-dropdown-hover">
		<a href="#" onclick="w3_close()"
			class="w3-text-white w3-padding w3-border w3-round-xxlarge" id="30"><i
			class="fa fa-leaf fa-fw w3-margin-right"></i>Reports</a>
		<div style="margin-left:10%;width:80%"
			class="w3-dropdown-content w3-orange  w3-animate-zoom w3-right w3-padding w3-medium">
	
			<a href="/Gionee/POC/listSmr" onclick="w3_close()"
				class="w3-text-white w3-padding w3-border w3-round-xxlarge" id="31"><i
				class="fa fa-book fa-fw w3-margin-right"></i>SMR</a>
		</div>
	</div>

	<div class="w3-dropdown-hover">
		<a href="/Gionee/POC/getAudits" onclick="w3_close()"
			class="w3-text-white w3-padding w3-border w3-round-xxlarge" id="32"><i
			class="fa fa-history fa-fw w3-margin-right"></i>Audit</a>
		<!-- <div id="#" class="w3-dropdown-content w3-deep-orange w3-animate-zoom w3-right w3-padding w3-medium">
      <a href="#" onclick="w3_close()" class="w3-padding w3-border w3-round-xxlarge" id="8"><i class="fa fa-diamond fa-fw w3-margin-right"></i>Order</a>
       <a href="#" onclick="w3_close()" class="w3-padding w3-border w3-round-xxlarge" id="8"><i class="fa fa-diamond fa-fw w3-margin-right"></i>Investments</a>
        <a href="#" onclick="w3_close()" class="w3-padding w3-border w3-round-xxlarge" id="8"><i class="fa fa-diamond fa-fw w3-margin-right"></i>Payments</a>
         <a href="#" onclick="w3_close()" class="w3-padding w3-border w3-round-xxlarge" id="8"><i class="fa fa-diamond fa-fw w3-margin-right"></i>Deliveries</a>
          <a href="#" onclick="w3_close()" class="w3-padding w3-border w3-round-xxlarge" id="8"><i class="fa fa-diamond fa-fw w3-margin-right"></i>Motants</a>

        
     </div> -->
	</div>
 
<sec:authorize   access="hasRole('ADMIN')" >		 	
	<div class="w3-dropdown-hover">
		<a href="/pocwebapp/list" onclick="w3_close()"
			class="w3-text-white w3-padding w3-border w3-round-xxlarge" id="33"><i
			class="fa fa-users fa-fw w3-margin-right"></i>User Management </a>
		<div style="margin-left:10%;width:80%"
			class="w3-dropdown-content w3-orange  w3-animate-zoom w3-right w3-padding w3-medium">
	<a href ="<c:url value='/list' />" class="w3-text-white w3-padding w3-border w3-round-xxlarge" id="34"><i
				class="fa fa-user-circle-o fa-fw w3-margin-right"></i>User</a>
				<a href ="<c:url value='/list' />"
				class="w3-text-white w3-padding w3-border w3-round-xxlarge" id="35"><i
				class="fa fa-tree fa-fw w3-margin-right"></i>User Hierarchy</a> <a
				href="" onclick="w3_close()"
				class="w3-text-white w3-padding w3-border w3-round-xxlarge" id="36"><i
				class="fa fa-user-secret fa-fw w3-margin-right"></i>Role</a> <a
				href="" onclick="w3_close()"
				class="w3-text-white w3-padding w3-border w3-round-xxlarge" id="37"><i
				class="fa fa-tv fa-fw w3-margin-right"></i>Screen Permission</a>
		</div>
	</div>
		 	</sec:authorize>
	
	 	
	

	<div class="w3-dropdown-hover">
		<a onclick="w3_close()"
			class="w3-text-white w3-padding w3-border w3-round-xxlarge" id="38"><i
			class="fa fa-globe fa-fw w3-margin-right"></i>Communication</a>
		<div id="myChatWin"
		style="margin-left:10%;width:80%"
			class="w3-dropdown-content w3-orange  w3-animate-zoom w3-right w3-padding w3-medium">

			<a onclick="openchat()" id="myOtherLogo"
				class="w3-hide w3-text-white w3-padding w3-border w3-round-xxlarge"
				id="39"><i class="fa fa-comments fa-fw w3-margin-right"></i>Chat</a>
			<a href="/Gionee/POC/adminChat" id="myAdminLogo"
				class="w3-hide w3-text-white w3-padding w3-border w3-round-xxlarge"
				id="39"><i class="fa fa-comments fa-fw w3-margin-right"></i>Chat
				(admin)</a>
		</div>
	</div>




	<!--  <div class="w3-dropdown-hover">
   <a href="#search"  class="w3-padding w3-border w3-round-xxlarge" id="6"><i class="fa fa-user fa-fw w3-margin-right"></i>IT admin</a>
  
  
    <div id="myAdmin" class="w3-dropdown-content w3-deep-orange w3-animate-zoom w3-right w3-padding w3-medium">
	  <a href="/Gionee/POC/userMachineAccess" onclick="w3_close()" class="w3-padding w3-border w3-round-xxlarge" id="7"><i class="fa fa-wrench fa-fw w3-margin-right"></i>User Machine Access</a>
   <a href="/Gionee/POC/admin" onclick="w3_close()" class="w3-padding w3-border w3-round-xxlarge" id="8"><i class="fa fa-diamond fa-fw w3-margin-right"></i>User management</a>
   
   
    <a href="/Gionee/POC/userScreen" onclick="w3_close()" class="w3-padding w3-border w3-round-xxlarge" id="12"><i class="fa fa-bolt fa-fw w3-margin-right"></i>User Role</a>
 
   <a href="/Gionee/POC/screen" onclick="w3_close()" class="w3-padding w3-border w3-round-xxlarge" id="9"><i class="fa fa-superpowers fa-fw w3-margin-right"></i>Role Screen Permissions</a>
  
     
    </div>
   </div> -->
	<a href="#search" onclick="w3_close()"
		class="w3-text-white w3-padding w3-border w3-round-xxlarge" id="40"><i
		class="fa fa-search fa-fw w3-margin-right"></i>SEARCH</a> <a href="#about"
		onclick="w3_close()"
		class="w3-text-white w3-padding w3-border w3-round-xxlarge" id="41"><i
		class="fa fa-male fa-fw w3-margin-right"></i>ABOUT</a> <a href="#contact"
		onclick="w3_close()"
		class="w3-text-white w3-padding w3-border w3-round-xxlarge" id="42"><i
		class="fa fa-envelope fa-fw w3-margin-right"></i>CONTACT</a> 
		<a href ="<c:url value='/logout' />" 
		class="w3-text-white w3-padding w3-border w3-round-xxlarge" ><i
		class="fa fa-sign-out fa-fw w3-margin-right"></i>Log Out</a>

</nav>
<div id="confirmPopup" class="w3-right floating" style="display: none">
	<%-- <%@ include file="chatWindow.jsp"%> --%>
</div>
<script>
	var sesAttr =
<%=request.getSession().getAttribute("sessionVar")%>
	if (sesAttr == 1 & '${userName}' != "admin") {
		var x = document.getElementById("myChatLogo");
		if (x.className.indexOf("w3-show") == -1) {
			x.className += " w3-show";
		} else {
			x.className = x.className.replace(" w3-show", "");
		}

	}

	if ('${userName}' == "admin") {
		var x = document.getElementById("myAdminLogo");
		if (x.className.indexOf("w3-show") == -1) {
			x.className += " w3-show";
		} else {
			x.className = x.className.replace(" w3-show", "");
		}

	} else {
		var x = document.getElementById("myOtherLogo");
		if (x.className.indexOf("w3-show") == -1) {
			x.className += " w3-show";
		} else {
			x.className = x.className.replace(" w3-show", "");
		}

	}

	function openchat() {
<%request.getSession().setAttribute("sessionVar", 1);%>
	$('#qnimate').addClass('popup-box-on');
		document.getElementById("confirmPopup").style.display = "block";
		$.ajax({url : "/LAPP_Rest_App/rest/lappChat/setChatStatus/${UserId}",
			success : function() {

			}
		});
	}
</script>
