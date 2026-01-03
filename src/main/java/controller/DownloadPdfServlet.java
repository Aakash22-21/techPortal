package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import dao.CourseDao;
import dbConnecter.DbConnector;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/download")
public class DownloadPdfServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Getting Course id.
		String cid = req.getParameter("cid");
		HttpSession session = req.getSession();
		
		CourseDao cdao = new CourseDao(DbConnector.getDbConnection());
		String pdfName = cdao.getCoursePdfName(Integer.parseInt(cid));

		if(pdfName != null) {
			
			//getting pdf Path
			String pdfDirLocation = session.getServletContext().getRealPath("course_pdf");
			
			// pdf Full path with pdf name
			String fullPath = pdfDirLocation+File.separator+pdfName;
			
			//Checking pdf exists or not
			File file = new File(fullPath);
			
			if(!file.exists()) {
				session.setAttribute("msg", "No File Found Please Contact Admin");
				resp.sendRedirect("allcourses.jsp");
				return;
			}
			
			// Download Logic for Pdf 
			resp.setContentType("application/pdf");
			// to download pdf set header
			resp.setHeader("Content-Disposition", "attachment; filename="+pdfName);
			
			//read and write pdf operation
			try(FileInputStream fis = new FileInputStream(file);
					ServletOutputStream sos =resp.getOutputStream();
					) {
				
				byte[] buffer  = new byte[8192]; // 8kb bufffer empty array
				int byteRead;
				
				while((byteRead = fis.read(buffer)) != -1) {
					sos.write(buffer, 0, byteRead);	
					sos.flush();
				}
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
	}
}
