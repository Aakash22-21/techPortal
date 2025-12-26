package controller;

import java.io.IOException;

import dao.UserDao;
import dbConnecter.DbConnector;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet("/login")
public class loginControllerServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String pass = req.getParameter("pass");
		
		UserDao udao = new UserDao(DbConnector.getDbConnection());
		User u = udao.getUserByEmailAndPassword(email, pass);
		
		// getting session object which we get created while signup
		HttpSession session = req.getSession();
		
		if(u!=null) {
			if(u.getRole().equals("admin")) {
				// this if will execute if user role is  admin
				session.setAttribute("userobj", u);
				session.setAttribute("role",u.getRole());
				// sending request to admin page
				resp.sendRedirect("admin.jsp");
				
			}else {
				// Normal user
				session.setAttribute("userobj", u);
				session.setAttribute("role",u.getRole());
				// sending request to index page
				resp.sendRedirect("index.jsp");
			}
		}else {
			session.setAttribute("msg", "Invalid email or Password");
			resp.sendRedirect("login.jsp");
		}
		
		
	}
}
