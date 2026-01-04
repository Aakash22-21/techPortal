package controller;

import java.io.IOException;
import java.util.List;

import dao.CourseDao;
import dao.JobDao;
import dbConnecter.DbConnector;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Course;
import model.Job;

@WebServlet("/indexpagesearch")
public class SearchBarController extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String jobSearch = req.getParameter("jobSearch");
		String courseSearch = req.getParameter("courseSearch");
		
		if(jobSearch != null && !jobSearch.trim().isEmpty()) {
			JobDao jdao = new JobDao(DbConnector.getDbConnection());
			List<Job> jobList = jdao.getJobByName(jobSearch);
			req.setAttribute("jobs", jobList);
			RequestDispatcher rd = req.getRequestDispatcher("searchresults.jsp");
			rd.forward(req, resp);
		}else if(courseSearch != null && !courseSearch.trim().isEmpty()) {
			CourseDao cdao = new CourseDao(DbConnector.getDbConnection());
			List<Course> courseList = cdao.getCourseByName(courseSearch);
			req.setAttribute("course", courseList);
			RequestDispatcher rd = req.getRequestDispatcher("viewcourses");
			rd.forward(req, resp);
		}else {
			 req.setAttribute("msg", "Please enter a search term");
			    req.getRequestDispatcher("index.jsp").forward(req, resp);
		}
	}
}
