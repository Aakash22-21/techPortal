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
import model.User;

@WebServlet("/appplyjob")
public class ApplyJobServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String jid = req.getParameter("jid");
		int jodId = Integer.parseInt(jid);

		// Getting Session from login login user
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("userobj");
		if (user == null) {
			session.setAttribute("failmsg", "Please Login first !!");
			resp.sendRedirect("login.jsp");
			return;
		}

		JobDao jDao = new JobDao(DbConnector.getDbConnection());
		boolean alreadyApplied = jDao.checkAlreadyApplied(user.getUserId(), jodId);

		if (alreadyApplied) {
			session.setAttribute("warnmsg", "Thanks for interst. You Already Applied for this Job");
			resp.sendRedirect("viewalljobs.jsp");
		} else {

			boolean isappplied = jDao.applyForJob(user.getUserId(), jodId);

			if (isappplied) {
				session.setAttribute("msg", "Sucessfully Applied!");
				resp.sendRedirect("viewalljobs.jsp");
			} else {
				session.setAttribute("failmsg", "Something Went Wrong!!");
				resp.sendRedirect("viewalljobs.jsp");
			}

		}
	}
}
