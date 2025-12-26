package helper;

import java.io.File;

import jakarta.servlet.http.Part;

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
}
