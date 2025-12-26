package controller;

import java.io.IOException;
import java.util.UUID;

import dao.UserDao;
import dbConnecter.DbConnector;
import helper.Helper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.User;

@WebServlet("/signup")
@MultipartConfig
public class SignUpControllerServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			String fname = req.getParameter("fname");
			String lname = req.getParameter("lname");
			String email = req.getParameter("email");
			String password = req.getParameter("pass");
			String mob = req.getParameter("mob");

			// Converting mob String type data to long type bcz in our POJO model we have
			// define mob has long.
//			long mobNo = Long.parseLong(mob);

//			This class represents a part or form item that was received within a multipart/form-data POST request.
			Part part = req.getPart("pic");
//			Now that upper part variable has the all information abut pic.
			String picName = part.getSubmittedFileName();

			// create DAO Object and call saved method.
			UserDao udao = new UserDao(DbConnector.getDbConnection());
			
			// Creating session Object
			HttpSession session = req.getSession();
			
			// Checking if user is already exists with same emailId.
			if (udao.isEmailExists(email)) {
				session.setAttribute("msg", "exists");
				resp.sendRedirect("signup.jsp");
				return;
			}

			// getting real Path of profile_img Folder
			String uploadPath = getServletContext().getRealPath("/profile_img");

			
			// changing every profile name into unique by adding timestamp & random number
			// Checking if pic name is not empty
			if(picName != null && picName.isBlank()) {
				String fileExtensioon = picName.substring(picName.lastIndexOf("."));
				picName = picName+UUID.randomUUID().toString()+"_"+System.currentTimeMillis()+fileExtensioon;
			}
			
			
			
			boolean imgSaved = Helper.savefile(part, uploadPath, picName);

			// if img uploaded in directory then saving details in Db.
			User u = new User();
			u.setUserFname(fname);
			u.setUserLname(lname);
			u.setUserEmail(email);
			u.setRole("normal");
			u.setPassword(password);
			u.setUserImg(picName);
			u.setMobno(mob);

			// checking img is saved or not in dir
			if (imgSaved) {
				boolean isSaved = udao.saveUser(u);
				
				if(isSaved) {
					session.setAttribute("msg", "success");
					resp.sendRedirect("signup.jsp");
				}
			} else {
				session.setAttribute("msg", "fail");
				resp.sendRedirect("signup.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
