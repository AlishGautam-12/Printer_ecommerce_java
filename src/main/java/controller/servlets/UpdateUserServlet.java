package controller.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.dao.UserDao;
import controller.helper.ConnectionProvider;
import controller.model.Message;
import controller.model.User;
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = request.getParameter("operation");
		HttpSession session = request.getSession();
		User oldUser = (User) session.getAttribute("activeUser");
		UserDao userDao = new UserDao(ConnectionProvider.getConnection());

		if (op.trim().equals("changeAddress")) {
			try {
				String userAddress = request.getParameter("user_address");
				String userCity = request.getParameter("city");
				

				User user = new User();
				user.setUserId(oldUser.getUserId());
				user.setUserName(oldUser.getUserName());
				user.setUserEmail(oldUser.getUserEmail());
				user.setUserPassword(oldUser.getUserPassword());
				user.setUserPhone(oldUser.getUserPhone());
				user.setUserGender(oldUser.getUserGender());		
				user.setUserAddress(userAddress);
				user.setUserCity(userCity);
				userDao.updateUserAddresss(user);
				session.setAttribute("activeUser", user);
				response.sendRedirect("checkout.jsp");

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (op.trim().equals("updateUser")) {
			try {
				String userName = request.getParameter("name");
				String userEmail = request.getParameter("email");
				String userPhone = request.getParameter("mobile_no");
				String userGender = request.getParameter("gender");
				String userAddress = request.getParameter("address");
				String userCity = request.getParameter("city");
				String userPassword = request.getParameter("password");
				

				User user = new User(userName, userEmail,userPassword, userPhone, userGender, userAddress, userCity);
				user.setUserId(oldUser.getUserId());
				user.setUserPassword(oldUser.getUserPassword());
				

				userDao.updateUser(user);
				session.setAttribute("activeUser", user);
				Message message = new Message("User information updated successfully!!", "success", "alert-success");
				session.setAttribute("message", message);
				response.sendRedirect("profile.jsp");

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (op.trim().equals("deleteUser")) {
			int uid = Integer.parseInt(request.getParameter("uid"));
			userDao.deleteUser(uid);
			response.sendRedirect("display_users.jsp");
		}
	}


	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

}
