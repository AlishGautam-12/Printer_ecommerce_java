<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page errorPage="error_exception.jsp"%>

<%@page import="controller.model.Message"%>

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
<title> Page</title>
<%@include file="Components/common.jsp"%>

</head>
<body>
	<!--navbar -->
	<%@include file="Components/nav.jsp"%>
	
	<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h1 class="modal-title fs-5" id="addCategoryModalLabel">Add
						Category Here</h1>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<form action="AddOperationServlet" method="post"
					enctype="multipart/form-data">
					<div class="modal-body">
						<input type="hidden" name="operation" value="addCategory">

						<div class="mb-3">
							<label class="form-label"><b>Category Name</b></label> <input
								type="text" name="category_name"
								placeholder="Enter category here" class="form-control" required>
						</div>
						
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-primary me-3">Add
							Category</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
</body>
</html>
  