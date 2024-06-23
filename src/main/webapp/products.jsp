
<%@page import="controller.model.User"%>
<%@page import="controller.dao.CategoryDao"%>
<%@page import="controller.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="controller.helper.ConnectionProvider"%>
<%@page import="controller.dao.ProductDao"%>
<%
User u = (User) session.getAttribute("activeUser");


String searchKey = request.getParameter("search");
String catId = request.getParameter("category");
CategoryDao categoryDao = new CategoryDao(ConnectionProvider.getConnection());
String message = "";

ProductDao productDao = new ProductDao(ConnectionProvider.getConnection());
List<Product> prodList = null;
if (searchKey != null) {
	if (!searchKey.isEmpty()) {
		message = "Showing results for \"" + searchKey + "\"";
	}else{
		message = "No product found!";
	}
	prodList = productDao.getAllProductsBySearchKey(searchKey);

} else if (catId != null && !(catId.trim().equals("0"))) {
	prodList = productDao.getAllProductsByCategoryId(Integer.parseInt(catId.trim()));
	message = "Showing results for \"" + categoryDao.getCategoryName(Integer.parseInt(catId.trim())) + "\"";
} else {
	prodList = productDao.getAllProducts();
	message = "All Products";
}

if (prodList != null && prodList.size() == 0) {

	message = "No items are available for \""
	+ (searchKey != null ? searchKey : categoryDao.getCategoryName(Integer.parseInt(catId.trim()))) + "\"";

	prodList = productDao.getAllProducts();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Products</title>
<%@include file="Components/common.jsp"%>
<style>
.real-price {
	font-size: 22px !important;
	font-weight: 600;
}

.product-price {
	font-size: 17px !important;
	text-decoration: line-through;
}

.product-discount {
	font-size: 15px !important;
	color: #027a3e;
}


</style>
</head>
<body style="background-color: #f0f0f0;">
	<!--navbar -->
	<%@include file="Components/nav.jsp"%>

	<!--show products-->
	<h4 class="text-center mt-2"><%=message%></h4>
	<div class="container-fluid my-3 px-5">

		<div class="row row-cols-1 row-cols-md-4 g-3">
			<%
			for (Product p : prodList) {
			%>
			<div class="col">

				<div class="card h-100 px-2 py-2">
					<div class="container text-center">
					<img src="product_imgs/<%= p.getProductImages() %>"
						
							class="card-img-top m-2"
							style="max-width: 100%; max-height: 200px; width: auto;">
						
							
						<h5 class="card-title text-center"><%=p.getProductName()%></h5>

						<div class="container text-center">
							<span class="real-price">&#8360;<%=p.getProductPriceAfterDiscount()%></span>&ensp;
							<span class="product-price">&#8360;<%=p.getProductPrice()%></span>&ensp;
							<span class="product-discount">&#8360;<%=p.getProductDiscount()%>off</span>
						</div>
						<div class="container text-center mb-2 mt-2">
							<button type="button"
								onclick="window.open('viewProduct.jsp?pid=<%=p.getProductId()%>', '_self')"
								class="btn btn-primary text-white"> Details</button>
						</div>
						
					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>
</body>
</html>

