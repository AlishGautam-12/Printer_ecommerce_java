<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<%@include file="Components/common.jsp"%>
<style>
label{
	font-weight: bold;
}
</style>
</head>
<body >  

	<!--navbar -->
	<%@include file="Components/nav.jsp"%>

	<div class="container-fluid">
		<div class="row mt-5">
			<div class="col-md-4 offset-md-4">
				<div class="card">
					<div class="card-body px-5">

						
						<h3 class="text-center">Sign-In</h3>
						<%@include file="Components/alert.jsp" %>
						
						<!--login-form-->
						<form id="login-form" action="LoginServlet" method="post">
							<input type="hidden" name="login" value="user"> 
							<div class="mb-3">
								<label class="form-label">Email</label> <input
									type="email" name="user_email" placeholder="Email address"
									class="form-control" required>
							</div>
							<div class="mb-3">
								<label class="form-label">Password</label>
								<input type="password" name="user_password"
									placeholder="Enter your password" class="form-control" required>
							</div>
							<div id="login-btn" class="container text-center">
								<button type="submit" class="btn btn-outline-primary me-3">Login</button>
							</div>
						</form>
						<div class="mt-3 text-center">
							
								Don't have an account?<a href="register.jsp"
									style="text-decoration: none"> Sign Up</a>
							</h6>
						</div>
					</div>  

				</div>
			</div>
		</div>
	</div>
</body>
</html>