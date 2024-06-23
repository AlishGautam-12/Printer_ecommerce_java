<%@page import="controller.model.Message"%>
<%@page import="controller.model.User"%>
<%@page errorPage="error_exception.jsp"%>
<%
User activeUser = (User) session.getAttribute("activeUser");
if (activeUser == null) {
	Message message = new Message("You are not logged in! Login first!!", "error", "alert-danger");
	session.setAttribute("message", message);
	response.sendRedirect("login.jsp");
	return;  
}
%>  


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Profile</title>
<%@include file="Components/common.jsp"%>
<style>
.cus-active {
	background-color: #e6eefa !important;
	width: 100%;
}

.list-btn {
	font-size: 20px !important;
}

.list-btn:hover {
	color: #2874f0 !important;
}
</style>
</head>
<body>
	<!--navbar -->
	<%@include file="Components/nav.jsp"%>

	<div class="container-fluid px-3 py-5">
		<div class="row">
			<div class="col-md-3">
				<div class="card">
					<div class="row mt-2 mb-2">
						<div class="col-md-4">
							
						</div>
						<div class="col-md-8">
							Hello, <br>
							<h5><%=activeUser.getUserName()%></h5>
						</div>
					</div>  
				</div>

				<div class="card mt-3">
					<div class="list-group">
						<button type="button" id="profile-btn"
							class="list-group-item list-group-item-action cus-active list-btn"
							aria-current="true">Profile Information</button>
						
						<button type="button" id="order-btn"
							class="list-group-item list-group-item-action list-btn">My
							Orders</button>
						<button type="button" id="logout-btn"
							class="list-group-item list-group-item-action list-btn"
							onclick="window.open('LogoutServlet?user=user', '_self')">Logout</button>
					</div>
				</div>
			</div>
			<div class="col-md-9">
				<div class="card">
					<div id="profile">
						<%@include file="Components/alert.jsp"%>
						<%@include file="personalInfo.jsp"%>
					</div>
											
						 
						  
						  
					</div>
					<div id="order" style="display: none;">
					 
					 <%@include file="order.jsp"%>
						   
					</div>
				</div>
			</div>  
		</div>
	</div>

	<script>
		$(document).ready(function() {

			$('#profile-btn').click(function() {

				$('#profile').show();
				
				$('#order').hide();
				
				$(this).addClass('cus-active');
				
				$('#order-btn').removeClass('cus-active');
				

			});
			
			$('#order-btn').click(function() {

				$('#order').show();
				$('#profile').hide();
				
				
				$(this).addClass('cus-active');
				$('#profile-btn').removeClass('cus-active');
				
			});
		});
	</script>
</body>
</html>