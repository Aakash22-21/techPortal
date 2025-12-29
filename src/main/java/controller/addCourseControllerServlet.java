package controller;

import java.io.IOException;
import java.util.UUID;

import dao.CourseDao;
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
import model.Course;
import model.User;

@WebServlet("/addcourse")
@MultipartConfig
public class addCourseControllerServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// getting all form data
		String status = req.getParameter("cstatus");
		String cname = req.getParameter("cname");
		String cdur = req.getParameter("cdur");
		String cdesc = req.getParameter("cdesc");
		String cfee = req.getParameter("cfee"); // converting into int
		int courseFee = Integer.parseInt(cfee);

		//getting img course part and file name
		Part imagePart = req.getPart("cpic");
		String imageName = imagePart.getSubmittedFileName();
		
		//getting pdf part and file name
		Part pdfPart = req.getPart("cpdf");
		String pdfName = pdfPart.getSubmittedFileName();
		
		//get Session obj
		HttpSession session = req.getSession();
		
		if((imageName == null || imageName.trim().isEmpty()) && pdfName == null || pdfName.isBlank()) {
			session.setAttribute("imgmsg", "please select course img");
			session.setAttribute("pdfmsg", "Plaese upload Pdf File");
			resp.sendRedirect("addcourse.jsp");
			return;
		}
		
		// genrating unique name for pd/image using UUid and timestam
	imageName =	UUID.randomUUID().toString()+"_"+System.currentTimeMillis()+imageName.substring(imageName.lastIndexOf("."));
	pdfName =	UUID.randomUUID().toString()+"_"+System.currentTimeMillis()+pdfName.substring(pdfName.lastIndexOf("."));
		
	// getting real path of dir
	String imgUpoadedPath = getServletContext().getRealPath("course_img");
	String pdfUploadPath = getServletContext().getRealPath("course_pdf");
	
	// save file to directory
	boolean isImgSaved = Helper.savefile(imagePart, imgUpoadedPath, imageName);
	boolean isPdfSaved = Helper.savefile(pdfPart, pdfUploadPath, pdfName);
	
	//course data save to Db
	if(isImgSaved && isPdfSaved) {
		// creating course obj
		Course course = new Course();
		course.setCourseName(cname);
		course.setCourseDuration(cdur);
		course.setCourseDesc(cdesc);
		course.setCourseFee(courseFee);
		course.setCourseImg(imageName);
		course.setPdfName(pdfName);
		course.setStatus(status);
		
		// set user Id
		// getting user object  from session obj and get user id from there and sett it to course.
		User user = (User)session.getAttribute("userobj");
		course.setUserId(user.getUserId());
		
		CourseDao cdao = new CourseDao(DbConnector.getDbConnection());
		cdao.saveCourse(course);
		
		
	}else {
		
	}
		
		
	}

}
