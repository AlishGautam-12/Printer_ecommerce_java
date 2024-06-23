<%@page import="controller.model.Message"%>
<%@page import="controller.dao.ProductDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="controller.dao.CartDao"%>
<%@page errorPage="error_exception.jsp"%>
<%
User activeUser = (User) session.getAttribute("activeUser");
if (activeUser == null) {
	Message message = new Message("You are not logged in! Login first!!", "error", "alert-danger");
	session.setAttribute("message", message);
	response.sendRedirect("login.jsp");
	return;  
}
String from = (String)session.getAttribute("from");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Checkout</title>
<%@include file="Components/common.jsp"%>
</head>
<body>
	<!--navbar -->
	<%@include file="Components/nav.jsp"%>

	<div class="container mt-5" style="font-size: 17px;">
		<div class="row">


			<!-- right column -->
			<div class="col-md-4">
				<div class="card">
					<div class="container px-3 py-3">
						<h4>Price Details</h4>
						<hr>
						<%
						if (from.trim().equals("cart")) {
							CartDao cartDao = new CartDao(ConnectionProvider.getConnection());
							int totalProduct = cartDao.getCartCountByUserId(user.getUserId());
							float totalPrice = (float) session.getAttribute("totalPrice");
						%>
						<table class="table table-borderless">
							<tr>
								<td>Total Item</td>
								<td><%=totalProduct%></td>
							</tr>
							<tr>
								<td>Total Price</td>
								<td>&#8360;<%=totalPrice%></td>
							</tr>
							
							
							<tr>
								<td><h5>Amount Payable :</h5></td>
								<td><h5>
										&#8360;
										<%=totalPrice%></h5></td>
							</tr>
						</table>
						<%
						} else {
							ProductDao productDao = new ProductDao(ConnectionProvider.getConnection());
							int pid = (int) session.getAttribute("pid");
							float price = productDao.getProductPriceById(pid);
						%>
						<table class="table table-borderless">
							<tr>
								<td>Total Item</td>
								<td>1</td>
							</tr>
							<tr>
								<td>Total Price</td>
								<td>&#8360;<%=price%></td>
							</tr>
							
							<tr>
								<td><h5>Amount Payable :</h5></td>
								<td><h5>
										&#8360;
										<%=price%></h5></td>
										
							</tr>
							
						</table>
						<%
						}
						%>
						<div class="col-md-8">
				
						
						
						<hr>
						
						<form action="OrderOperationServlet" method="post">
							
							
								<button type="submit"
									class="btn btn-lg btn-outline-primary mt-3">Order</button>
							</div>
						</form>
					</div>
				</div>
			</div>
					</div>
				</div>
			</div>
			<!-- end of column -->
		</div>
	</div>


	

</body>
</html>