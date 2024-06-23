package controller.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.dao.contactInfoDao;
import controller.helper.ConnectionProvider;
import controller.model.contactInfo;

@WebServlet("/contactInfoServlet")
public class contactInfoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String phoneNumber = request.getParameter("phoneNumber");
        String email = request.getParameter("email");
        String message = request.getParameter("message");

        contactInfo info = new contactInfo(phoneNumber, email, message);

        Connection con = null;
        try {
            con = ConnectionProvider.getConnection(); // Change to ConnectionProvider
            contactInfoDao dao = new contactInfoDao(con);
            boolean saved = dao.saveInfo(info);

            if (saved) {
                // Redirect to a success page or display a success message
            	request.setAttribute("successMessage", "Message sent successfully!");
                response.sendRedirect("aboutUs.jsp");
                
            } else {
                // Redirect to an error page or display an error message
                request.setAttribute("errorMessage", "Database error occurred.");
            }
        } finally {
            // Don't close the connection here
        }
    }
}
