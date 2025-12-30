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

@WebServlet("/editcourse")
@MultipartConfig
public class EditCourseControllerServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// getting all form data
		String status = req.getParameter("cstatus");
		String cname = req.getParameter("cname");
		String cdur = req.getParameter("cdur");
		String cdesc = req.getParameter("cdesc");
		String cfee = req.getParameter("cfee"); // converting into int
		int courseFee = Integer.parseInt(cfee);

		String courseId = req.getParameter("courseid");

		// getting old img Course name
		String existingCourseImgName = req.getParameter("existingpic");

		// getting old PDF Course name
		String existingCoursePdfName = req.getParameter("existingpdf");

		// image and Course Validation Code
		if (existingCourseImgName != null) {
			existingCourseImgName = existingCourseImgName.trim();
		}

		if (existingCoursePdfName != null) {
			existingCoursePdfName = existingCoursePdfName.trim();
		}

		// getting latest img course part and file name
		Part imagePart = req.getPart("cpic");
		String latestimageName = imagePart.getSubmittedFileName();

		// getting latest pdf part and file name
		Part pdfPart = req.getPart("cpdf");
		String latestpdfName = pdfPart.getSubmittedFileName();

		// get session obj
		HttpSession session = req.getSession();

		// creating course object with latest updated values
		Course course = new Course();
		course.setCourseName(cname);
		course.setCourseDuration(cdur);
		course.setCourseDesc(cdesc);
		course.setCourseFee(courseFee);
		course.setStatus(status);
		course.setCourseId(Integer.parseInt(courseId));

		// handling latest img
		if (latestimageName != null && !latestimageName.trim().isEmpty()) {

			latestimageName = UUID.randomUUID().toString() + "_" + System.currentTimeMillis()
					+ latestimageName.substring(latestimageName.lastIndexOf("."));
			// get Uloaded Dir
			String imgUploadedPath = getServletContext().getRealPath("/course_img");

			boolean isImgSaved = Helper.savefile(imagePart, imgUploadedPath, latestimageName);

			if (isImgSaved) {
				course.setCourseImg(latestimageName);
			} else {
				course.setCourseImg(existingCourseImgName);
			}

		} else {
			course.setCourseImg(existingCourseImgName);
		}

		// handling latest pdf
		if (latestpdfName != null && !latestpdfName.trim().isEmpty()) {
			latestpdfName = UUID.randomUUID().toString() + "_" + System.currentTimeMillis()
					+ latestpdfName.substring(latestpdfName.lastIndexOf("."));
			// getting path of upload Dir
			String pdfUploadPath = getServletContext().getRealPath("/course_pdf");

			boolean isPdfSaved = Helper.savefile(pdfPart, pdfUploadPath, latestpdfName);

			if (isPdfSaved) {
				course.setPdfName(latestpdfName);
			} else {
				course.setPdfName(existingCoursePdfName);
			}

		} else {
			course.setPdfName(existingCoursePdfName);
		}

		// Creating Dao object and calling Update method
		CourseDao cdao = new CourseDao(DbConnector.getDbConnection());
		boolean isUpdated = cdao.updateCourse(course);

		if (isUpdated) {
			session.setAttribute("msg", "Sucessfully Updated");
		} else {
			session.setAttribute("failmsg", "Something went WWrong!!");
		}

		resp.sendRedirect("viewcourse.jsp");
	}

}
