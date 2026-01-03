package controller;

import java.io.IOException;
import java.util.List;

import dao.CourseDao;
import dbConnecter.DbConnector;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Course;

@WebServlet("/viewcourses")
public class ViewAllCoursesServletController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CourseDao cdao = new CourseDao(DbConnector.getDbConnection());
		List<Course> allCourses;

		// fetch all data from filter fields
		String search = req.getParameter("search");
		String status = req.getParameter("status");
		String feeOrder = req.getParameter("fee");

		if (search != null && !search.trim().isEmpty()) {
			allCourses = cdao.getCourseByName(search);
		} else if (status != null && !status.isEmpty()) {
			allCourses = cdao.getCourseByStatus(status);
		} else if (feeOrder != null && !feeOrder.isEmpty()) {
			allCourses = cdao.getCourseByFee(feeOrder);
		} else {
			allCourses = cdao.getAllCourse();
		}

		req.setAttribute("courses", allCourses);

		// We can use these method if our status are dynamic so every time when user
		// click on /viewcourse it gets data from db not from contexListner
		// getting Unique Status from Db

//		System.out.println("Load status from db");
//		List<String> statusList = cdao.getDistinctStatus();
//		req.setAttribute("statusList", statusList);

		// Filter operations search by name, search by status, search by fee.

		RequestDispatcher rd = req.getRequestDispatcher("allcourses.jsp");
		rd.forward(req, resp);
	}

}
