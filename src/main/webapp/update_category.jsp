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
<title>Update Category</title>
<%@include file="Components/common.jsp"%>
</head>
<body>
	<!--navbar -->
	<%@include file="Components/nav.jsp"%>

	<!-- update category -->
	<%
	int cid = Integer.parseInt(request.getParameter("cid"));
	Category category = catDao.getCategoryById(cid);
	%>
	<div class="container mt-5">
		<div class="row row-cols-1 row-cols-md-1 offset-md-2">
			<div class="col">
				<div class="card w-75">
					<div class="card-header text-center">
						<h3>Edit Category</h3>
					</div>
					<form action="AddOperationServlet?cid=<%=cid%>" method="post"
						enctype="multipart/form-data">
						<div class="card-body">
							<input type="hidden" name="operation" value="updateCategory">
							<div class="mb-3">
								<label class="form-label"><b>Category Name</b></label> <input
									type="text" name="category_name" value="<%=category.getCategoryName()%>" class="form-control" >
							</div>
							
							
							
						</div>
						<div class="card-footer text-center">
							<button type="submit" class="btn btn-lg btn-primary me-3">Update</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- end-->
</body>
</html>