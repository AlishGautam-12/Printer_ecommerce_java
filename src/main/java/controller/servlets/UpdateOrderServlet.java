package controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.dao.OrderDao;
import controller.helper.ConnectionProvider;
@WebServlet("/UpdateOrderServlet")
public class UpdateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    try {
	        int oid = Integer.parseInt(request.getParameter("oid"));
	        String status = request.getParameter("status");
	        
	        // Initialize DAO objects
	        OrderDao orderDao = new OrderDao(ConnectionProvider.getConnection());
	        
	        // Update order status
	        orderDao.updateOrderStatus(oid, status);
	        
	        // Redirect to display_orders.jsp
	        response.sendRedirect("display_orders.jsp");
	    } catch (Exception e) {
	        e.printStackTrace();
	        // Handle exceptions or display error message
	        response.sendRedirect("error.jsp");
	    }
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
