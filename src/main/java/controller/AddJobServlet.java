package controller;

import java.io.IOException;

import dao.JobDao;
import dbConnecter.DbConnector;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Job;

@WebServlet("/addjob")
public class AddJobServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String location = req.getParameter("location");
		String category = req.getParameter("category");
		String status = req.getParameter("status");
		String jobDesc = req.getParameter("jobdesc");

		// Create Job Object and fill this data.
		Job job = new Job();
		job.setTitle(title);
		job.setLocation(location);
		job.setCategory(category);
		job.setStatus(status);
		job.setJobDesc(jobDesc);

		// Creating JobDao Object
		JobDao jDao = new JobDao(DbConnector.getDbConnection());
		boolean isJobPost = jDao.addJob(job);

		HttpSession session = req.getSession();

		if (isJobPost) {
			session.setAttribute("msg", "Sucessfully Job posted!");
			resp.sendRedirect("addjobs.jsp");
		} else {
			session.setAttribute("failmsg", "Something Went Wrong!!");
			resp.sendRedirect("addjobs.jsp");
		}

	}
}
