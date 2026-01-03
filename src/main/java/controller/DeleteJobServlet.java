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

@WebServlet("/deletejob")
public class DeleteJobServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String jobId = req.getParameter("jid");
		HttpSession session = req.getSession();
		JobDao jDao = new JobDao(DbConnector.getDbConnection());
		boolean isDeleted = jDao.deleteJobById(Integer.parseInt(jobId));
		if (isDeleted) {
			session.setAttribute("msg", "Sucessfully Deleted!");
			resp.sendRedirect("viewalljobs.jsp");
		} else {
			session.setAttribute("failmsg", "Something Went Wrong!!");
			resp.sendRedirect("viewalljobs.jsp");
		}
	}
}
