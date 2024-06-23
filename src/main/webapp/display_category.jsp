<%@page import="controller.model.Message"%>
<%@page errorPage="error_exception.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
Admin activeAdmin = (Admin) session.getAttribute("activeAdmin");
if (activeAdmin == null) {
	Message message = new Message("You are not logged in! Login first!!", "error", "alert-danger");
	session.setAttribute("message", message);
	response.sendRedirect("adminlogin.jsp");
	return;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Categories</title>
<%@include file="Components/common.jsp"%>
</head>
<body>
	<!--navbar -->
	<%@include file="Components/nav.jsp"%>

	<!-- Category -->
	<div class="container mt-3">
	<%@include file="Components/alert.jsp"%>
		<table class="table table-hover">
			<tr class="table-primary text-center" style="font-size: 20px;">
				
				<th>Category Name</th>
				<th>Action</th>
			</tr>
			<%
			for (Category c : categoryList) {
			%>
			<tr class="text-center">
				
				
				<td><%=c.getCategoryName()%></td>
				<td><a href="update_category.jsp?cid=<%=c.getCategoryId()%>" role="button" class="btn btn-secondary">Update</a>&emsp;<a
					href="AddOperationServlet?cid=<%=c.getCategoryId()%>&operation=deleteCategory"
					class="btn btn-danger" role="button">Delete</a></td>
			</tr>
			<%
			}
			%>
		</table>
	</div>

</body>
</html>
