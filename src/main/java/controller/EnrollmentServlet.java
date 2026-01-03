package controller;

import java.io.IOException;

import dao.EnrollmentDao;
import dbConnecter.DbConnector;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebServlet("/enrollment")
public class EnrollmentServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// getting Course id
		String cid = req.getParameter("cid");
		int courseId = Integer.parseInt(cid);

		// check if the user is login or not

		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("userobj");

		if (user == null) {
			session.setAttribute("msg", "Please Login First !!");
			resp.sendRedirect("login.jsp");
			return;
		}
		// Enrollment Logic.
		EnrollmentDao eDao = new EnrollmentDao(DbConnector.getDbConnection());

		String courseName = eDao.getCourseNameById(courseId);

		if (eDao.isUserAlreadyEnrolled(user.getUserId(), courseId)) {
			session.setAttribute("failmsg", "Already Enroll in " + courseName + " Course!");
			resp.sendRedirect("viewcourses");

		} else {

			boolean isEnrolled = eDao.enrollUser(user.getUserId(), courseId);

			if (isEnrolled) {
				
				session.setAttribute("msg", "Thanks for Enrolling in " + courseName + " Course!");
			} else {
				session.setAttribute("failmsg", "Something Went Wrong!");
			}

			resp.sendRedirect("viewcourses");
		}

	}
}
