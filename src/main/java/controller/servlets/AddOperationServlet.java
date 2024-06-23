package controller.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import controller.dao.CategoryDao;
import controller.dao.ProductDao;
import controller.helper.ConnectionProvider;
import controller.model.Category;
import controller.model.Message;
import controller.model.Product;

@WebServlet("/AddOperationServlet")

@MultipartConfig
public class AddOperationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String operation = request.getParameter("operation");
		CategoryDao catDao = new CategoryDao(ConnectionProvider.getConnection());
		ProductDao pdao = new ProductDao(ConnectionProvider.getConnection());
		HttpSession session = request.getSession();
		Message message = null;

		if (operation.trim().equals("addCategory")) {
		    String categoryName = request.getParameter("category_name");
		    Category category = new Category(categoryName);
		    boolean flag = catDao.saveCategory(category);

		    if (flag) {
		        message = new Message("Category added successfully!!", "success", "alert-success");
		    } else {
		        message = new Message("Something went wrong! Try again!!", "error", "alert-danger");
		    }
		    session.setAttribute("message", message);
		    response.sendRedirect("admin.jsp");
		

		} else if (operation.trim().equals("addProduct")) {

		    // add product to database
		    String pName = request.getParameter("name");
		    String pDesc = request.getParameter("description");
		    float pPrice = Float.parseFloat(request.getParameter("price"));

		    int pDiscount = Integer.parseInt(request.getParameter("discount"));
		    if (pDiscount < 0 || pDiscount > 100) {
		        pDiscount = 0;
		    }
		    int pQuantity = Integer.parseInt(request.getParameter("quantity"));
		    Part part = request.getPart("photo");
		    int categoryType = Integer.parseInt(request.getParameter("categoryType"));

		    Product product = new Product(pName, pDesc, pPrice, pDiscount, pQuantity, part.getSubmittedFileName(),
		            categoryType);
		    boolean flag = pdao.saveProduct(product);

		    // Specify the desired path where you want to save the image
		    String path = "C:\\Users\\dell\\eclipse-workspace\\PrinterHub\\src\\main\\webapp\\product_imgs\\" + part.getSubmittedFileName();

		    try {
		        FileOutputStream fos = new FileOutputStream(path);
		        InputStream is = part.getInputStream();
		        byte[] data = new byte[is.available()];
		        is.read(data);
		        fos.write(data);
		        fos.flush();
		        fos.close();

		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    if (flag) {
		        message = new Message("Product added successfully!!", "success", "alert-success");
		    } else {
		        message = new Message("Something went wrong! Try again!!", "error", "alert-danger");
		    }
		    session.setAttribute("message", message);
		    response.sendRedirect("admin.jsp");
		
		
			
		} else if (operation.trim().equals("updateCategory")) {
		    int cid = Integer.parseInt(request.getParameter("cid"));
		    String name = request.getParameter("category_name");
		    Category category = new Category(cid, name);
		    boolean flag = catDao.updateCategory(category);

		    if (flag) {
		        message = new Message("Category updated successfully!!", "success", "alert-success");
		    } else {
		        message = new Message("Something went wrong! Try again!!", "error", "alert-danger");
		    }
		    session.setAttribute("message", message);
		    response.sendRedirect("display_category.jsp");
		} else if (operation.trim().equals("deleteCategory")) {
		    int cid = Integer.parseInt(request.getParameter("cid"));
		    catDao.deleteCategory(cid);
		    response.sendRedirect("display_category.jsp");
		


		} else if (operation.trim().equals("updateProduct")) {

			int pid = Integer.parseInt(request.getParameter("pid"));
			String name = request.getParameter("name");
			float price = Float.parseFloat(request.getParameter("price"));
			String description = request.getParameter("description");
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			int discount = Integer.parseInt(request.getParameter("discount"));
			if (discount < 0 || discount > 100) {
				discount = 0;
			}
			Part part = request.getPart("product_img");
			int cid = Integer.parseInt(request.getParameter("categoryType"));
			if (cid == 0) {
				cid = Integer.parseInt(request.getParameter("category"));
			}
			if (part.getSubmittedFileName().isEmpty()) {
				String image = request.getParameter("image");
				Product product = new Product(pid, name, description, price, discount, quantity, image, cid);
				pdao.updateProduct(product);
			} else {

				Product product = new Product(pid, name, description, price, discount, quantity,
						part.getSubmittedFileName(), cid);
				pdao.updateProduct(product);
				// product image upload
				String path = request.getServletContext().getRealPath("/") + "Product_imgs" + File.separator
						+ part.getSubmittedFileName();
				try {
					FileOutputStream fos = new FileOutputStream(path);
					InputStream is = part.getInputStream();
					byte[] data = new byte[is.available()];
					is.read(data);
					fos.write(data);
					fos.flush();
					fos.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			message = new Message("Product updated successfully!!", "success", "alert-success");
			session.setAttribute("message", message);
			response.sendRedirect("display_products.jsp");

		} else if (operation.trim().equals("deleteProduct")) {

			int pid = Integer.parseInt(request.getParameter("pid"));
			pdao.deleteProduct(pid);
			response.sendRedirect("display_products.jsp");

		}
		return;
	}

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
}
