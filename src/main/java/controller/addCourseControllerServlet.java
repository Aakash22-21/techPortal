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
public class AddCourseControllerServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// getting all form data
		String status = req.getParameter("cstatus");
		String cname = req.getParameter("cname");
		String cdur = req.getParameter("cdur");
		String cdesc = req.getParameter("cdesc");
		String cfee = req.getParameter("cfee"); // converting into int
		int courseFee = Integer.parseInt(cfee);

		// getting img course part and file name
		Part imagePart = req.getPart("cpic");
		String imageName = imagePart.getSubmittedFileName();

		// getting pdf part and file name
		Part pdfPart = req.getPart("cpdf");
		String pdfName = pdfPart.getSubmittedFileName();

		// get Session obj
		HttpSession session = req.getSession();


		// checking pdf and img uploaded or not
		boolean hasError = false;

		if (imageName == null || imageName.isBlank()) {
			session.setAttribute("imgmsg", "Please Select Course Img");
			hasError = true;

		}

		if (pdfName == null || pdfName.isBlank()) {
			session.setAttribute("pdfmsg", "Please upload PDF file");
			hasError = true;

		}

		if (hasError) {
			resp.sendRedirect("addcourse.jsp");
			return;
		}

		// genrating unique name for pd/image using UUid and timestam
		imageName = UUID.randomUUID().toString() + "_" + System.currentTimeMillis()
				+ imageName.substring(imageName.lastIndexOf("."));
		pdfName = UUID.randomUUID().toString() + "_" + System.currentTimeMillis()
				+ pdfName.substring(pdfName.lastIndexOf("."));

		// getting real path of dir
		String imgUpoadedPath = getServletContext().getRealPath("course_img");
		String pdfUploadPath = getServletContext().getRealPath("course_pdf");

		// save file to directory
		boolean isImgSaved = Helper.savefile(imagePart, imgUpoadedPath, imageName);
		boolean isPdfSaved = Helper.savefile(pdfPart, pdfUploadPath, pdfName);

		// course data save to Db
		if (isImgSaved && isPdfSaved) {
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
			// getting user object from session obj and get user id from there and sett it
			// to course.
			User user = (User) session.getAttribute("userobj");
			course.setUserId(user.getUserId());

			CourseDao cdao = new CourseDao(DbConnector.getDbConnection());
			boolean isCourseSaved = cdao.saveCourse(course);

			if (isCourseSaved) {
				session.setAttribute("msg", "Course Added!");
				resp.sendRedirect("addcourse.jsp");
			} else {
				session.setAttribute("failmsg", "Failed to add Course!");
				resp.sendRedirect("addcourse.jsp");
			}

		} else {

		}

	}

}
