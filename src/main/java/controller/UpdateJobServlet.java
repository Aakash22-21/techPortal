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

@WebServlet("/updatejob")
public class UpdateJobServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String jobId = req.getParameter("jobid");
		String title = req.getParameter("title");
		String location = req.getParameter("location");
		String category = req.getParameter("category");
		String status = req.getParameter("status");
		String jobDesc = req.getParameter("jobdesc");

		// Create Job Object and fill this data.
		Job job = new Job();
		job.setJobid(Integer.parseInt(jobId));
		job.setTitle(title);
		job.setLocation(location);
		job.setCategory(category);
		job.setStatus(status);
		job.setJobDesc(jobDesc);

		JobDao jDao = new JobDao(DbConnector.getDbConnection());
		boolean isUpdated = jDao.updateJob(job);

		HttpSession session = req.getSession();
		if (isUpdated) {
			session.setAttribute("msg", "Updated Sucessfully !");
			resp.sendRedirect("viewalljobs.jsp");
		} else {
			session.setAttribute("failmsg", "Something Went Wrong!....");
			resp.sendRedirect("viewalljobs.jsp");
		}
	}
}
