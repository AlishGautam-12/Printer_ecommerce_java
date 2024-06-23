<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Page not found</title>
<%@include file="Components/common.jsp"%>
</head>
<body>

	<div class="container p-5 text-center">
		<img src="Images\no-results.png" class="img-fluid" style="max-width: 400px;">
		<h1>Sorry! Page not found</h1>
		<a href="index.jsp" class="btn btn-outline-primary btn-lg mt-3">Home
			Page</a>
	</div>
</body>
</html>