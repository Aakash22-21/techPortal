package controller;

import java.io.IOException;

import dao.CourseDao;
import dbConnecter.DbConnector;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Course;

@WebServlet("/delete")
public class DeleteCourse extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String courseId = req.getParameter("cid");

		CourseDao cdao = new CourseDao(DbConnector.getDbConnection());
		boolean isDeleted = cdao.deleteCourseById(Integer.parseInt(courseId));

		// getting Session Obj
		HttpSession session = req.getSession();
		if (isDeleted) {
			session.setAttribute("msg", "Sucessfully Deleted");
			resp.sendRedirect("viewcourse.jsp");
		} else {
			session.setAttribute("failmsg", "Something Went Wrong !!");
			resp.sendRedirect("viewcourse.jsp");
		}

	}
}
