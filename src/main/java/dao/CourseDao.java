package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Course;

public class CourseDao {

	private Connection conn;

	public CourseDao(Connection conn) {
		super();
		this.conn = conn;
	}

	// Creating Method to add Course
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
	
	// To get all Courses
	public List<Course> getAllCourse(){
		
		// Created Empty Collection to Store Course Objects
		List<Course> coursesList = new ArrayList<Course>();
		
		try {
			
			String query="select  * from course";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				// Create Course obj
				Course c = new Course();
				c.setCourseId(rs.getInt("cid"));
				c.setCourseDesc(rs.getString("cdescription"));
				c.setCourseDuration(rs.getString("courseduration"));
				c.setCourseFee(rs.getInt("coursefee"));
				c.setCourseImg(rs.getString("courseimg"));
				c.setCourseName(rs.getString("coursename"));
				c.setPdfName(rs.getString("pdfname"));
				c.setStatus(rs.getString("status"));
				c.setUserId(rs.getInt("userid"));
				
				coursesList.add(c);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return coursesList;
		
		
	}
}
