package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Course;

public class CourseDao {

	private Connection conn;

	public CourseDao(Connection conn) {
		super();
		this.conn = conn;
	}

	public boolean saveCourse(Course course) {

		boolean f = false;
		// cid, coursename, courseduration, coursefee, status, cdescription, courseimg,
		// pdfname, userid
		try {

			String query = "insert into course (coursename,courseduration,coursefee,status,cdescription,courseimg,pdfname,userid) values (?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, course.getCourseName());
			ps.setString(2, course.getCourseDuration());
			ps.setInt(3, course.getCourseFee());
			ps.setString(4, course.getStatus());
			ps.setString(5, course.getCourseDesc());
			ps.setString(6, course.getCourseImg());
			ps.setString(7, course.getPdfName());
			ps.setInt(8, course.getUserId());

			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;

	}
}
