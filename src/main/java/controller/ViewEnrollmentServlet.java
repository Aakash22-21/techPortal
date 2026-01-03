package controller;

import java.io.IOException;
import java.util.List;

import dao.EnrollmentDao;
import dbConnecter.DbConnector;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

@WebServlet("/viewwnrollment")
public class ViewEnrollmentServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cid = req.getParameter("cid");
		int courseId = Integer.parseInt(cid);

		EnrollmentDao eDao = new EnrollmentDao(DbConnector.getDbConnection());
		List<User> listOfEnrolledUser = eDao.getUserInfoByCourseId(courseId);

		req.setAttribute("allenrolleduser", listOfEnrolledUser);
		req.getRequestDispatcher("enrollment_list.jsp").forward(req, resp);

	}
}
