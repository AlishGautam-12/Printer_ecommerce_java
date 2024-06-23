package controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.dao.AdminDao;
import controller.helper.ConnectionProvider;
import controller.model.Admin;
import controller.model.Message;


@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String operation = request.getParameter("operation");
		AdminDao adminDao = new AdminDao(ConnectionProvider.getConnection());
		HttpSession session = request.getSession();
		Message message = null;
		
		if(operation.trim().equals("save")) {
			
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String phone = request.getParameter("phone");
			
			Admin admin = new Admin(name, email, phone, password);
			boolean flag = adminDao.saveAdmin(admin);

			if(flag) {
				message = new Message("New admin register !", "success", "alert-success");
			}else {
				message = new Message(" Something went wrong", "error", "alert-danger");
			}

		}else if(operation.trim().equals("delete")) {
			
			int id = Integer.parseInt(request.getParameter("id"));
			boolean flag = adminDao.deleteAdmin(id);
			if(flag) {
				message = new Message(" deleted successfully!", "success", "alert-success");
			}else {
				message = new Message(" Something went wrong", "error", "alert-danger");
			}
		}
		session.setAttribute("message", message);
		response.sendRedirect("display_]admin.jsp");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
