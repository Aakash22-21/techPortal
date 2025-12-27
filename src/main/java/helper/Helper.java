package helper;

import java.io.File;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.User;

public class Helper {

	public static boolean savefile(Part part, String uploadDir, String fileName) {

		boolean f = false;

		try {
			File dir = new File(uploadDir);

			if (!dir.exists()) {
				dir.mkdirs();
			}

			// construct full file path :- /profile_img\img1.png
			String filePath = uploadDir + File.separator + fileName;
			System.out.println(filePath);

			// Saving file in folder
			part.write(filePath);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	// Get profile img
	public static String getUserProffileImg(HttpSession session) {

		// getting real path of upload path directory
		String uploadedPath = session.getServletContext().getRealPath("profile_img");

		// getting login user information from session
		User user = (User) session.getAttribute("userobj");

		if (user != null && user.getUserImg() != null && !user.getUserImg().isBlank()) {

			String imgPath = uploadedPath + File.separator + user.getUserImg(); // profile_img/userimg
			File file = new File(imgPath);
			if (file.exists()) {
				return user.getUserImg();
			}

		}

		return "default.png";

	}
}
