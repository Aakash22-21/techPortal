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
	public List<Course> getAllCourse() {

		// Created Empty Collection to Store Course Objects
		List<Course> coursesList = new ArrayList<Course>();

		try {

			String query = "select  * from course";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
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

	// get Course by id from Db
	public Course getCourseById(int courseId) {
		Course course = null;
		try {
			String query = "select * from course where cid=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, courseId);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				course = new Course();
				course.setCourseId(rs.getInt("cid"));
				course.setCourseName(rs.getString("coursename"));
				course.setCourseDuration(rs.getString("courseduration"));
				course.setCourseDesc(rs.getString("cdescription"));
				course.setCourseFee(rs.getInt("coursefee"));
				course.setCourseImg(rs.getString("courseimg"));
				course.setPdfName(rs.getString("pdfname"));
				course.setStatus(rs.getString("status"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return course;
	}

	// Creating Method for update Course.
	public boolean updateCourse(Course course) {
		boolean updated = false;

		try {
			// cid, coursename, courseduration, coursefee, status, cdescription, courseimg,
			// pdfname, userid

			String query = "update course set coursename=?, courseduration=?, coursefee=?, status=?, cdescription=?, courseimg=?, pdfname=? where cid=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, course.getCourseName());
			ps.setString(2, course.getCourseDuration());
			ps.setInt(3, course.getCourseFee());
			ps.setString(4, course.getStatus());
			ps.setString(5, course.getCourseDesc());
			ps.setString(6, course.getCourseImg());
			ps.setString(7, course.getPdfName());
			ps.setInt(8, course.getCourseId());

			int i = ps.executeUpdate();

			if (i > 0) {
				updated = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return updated;

	}

	// Creating Method to Delete Course
	public boolean deleteCourseById(int id) {
		boolean isDeleted = false;

		try {

			String query = "delete from course where cid=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			int i = ps.executeUpdate();
			if (i == 1) {
				isDeleted = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isDeleted;
	}

	// Get Latest 6 Courses
	public List<Course> getLatestCourses() {

		// Created Empty Arraylist to Store Course Obj from Db
		List<Course> latestCourses = new ArrayList<Course>();

		try {

			String query = "select cid, coursename, coursefee, status, cdescription, courseimg from course order by cid desc limit 6";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("cid"));
				course.setCourseName(rs.getString("coursename"));
				course.setCourseDesc(rs.getString("cdescription"));
				course.setCourseFee(rs.getInt("coursefee"));
				course.setCourseImg(rs.getString("courseimg"));
				course.setStatus(rs.getString("status"));

				latestCourses.add(course);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return latestCourses;
	}

	// Creating method to show all Courses
	public List<Course> getAllCourses() {

		// Created Empty Arraylist to Store Course Obj from Db
		List<Course> latestCourses = new ArrayList<Course>();

		try {

			String query = "select cid, coursename, coursefee, status, cdescription, courseimg from course order by cid desc";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("cid"));
				course.setCourseName(rs.getString("coursename"));
				course.setCourseDesc(rs.getString("cdescription"));
				course.setCourseFee(rs.getInt("coursefee"));
				course.setCourseImg(rs.getString("courseimg"));
				course.setStatus(rs.getString("status"));

				latestCourses.add(course);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return latestCourses;
	}

	// Creating Method to get Pdf Name From Db
	public String getCoursePdfName(int id) {

		String pdfName = null;
		try {

			String query = "select pdfname from course where cid=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				pdfName = rs.getString("pdfname");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pdfName;

	}

	// Creating Method to get Distinct Status From Db
	public List<String> getDistinctStatus() {

		List<String> statusList = new ArrayList<String>();

		try {
			String query = "select distinct status from course";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				statusList.add(rs.getString("status"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return statusList;
	}

	// creating Method search by course name
	public List<Course> getCourseByName(String cname) {
		List<Course> courses = new ArrayList<Course>();

		try {
			String query = "select * from course where coursename like ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, "%" + cname + "%");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("cid"));
				course.setCourseName(rs.getString("coursename"));
				course.setCourseDesc(rs.getString("cdescription"));
				course.setCourseFee(rs.getInt("coursefee"));
				course.setCourseImg(rs.getString("courseimg"));
				course.setStatus(rs.getString("status"));

				courses.add(course);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return courses;

	}

	// creating Method search by status
	public List<Course> getCourseByStatus(String status) {
		List<Course> courses = new ArrayList<Course>();

		try {
			String query = "select * from course where status = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, status);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("cid"));
				course.setCourseName(rs.getString("coursename"));
				course.setCourseDesc(rs.getString("cdescription"));
				course.setCourseFee(rs.getInt("coursefee"));
				course.setCourseImg(rs.getString("courseimg"));
				course.setStatus(rs.getString("status"));

				courses.add(course);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return courses;
	}

	// creating Method search by fee
	public List<Course> getCourseByFee(String order) {
		List<Course> courses = new ArrayList<Course>();

		// default Sorting Order
		String sortOrder = "ASC";

		if (order.equals("high-low")) {
			sortOrder = "DESC";
		}

		try {
			String query = "select * from course order by coursefee " + sortOrder;
			PreparedStatement ps = conn.prepareStatement(query);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("cid"));
				course.setCourseName(rs.getString("coursename"));
				course.setCourseDesc(rs.getString("cdescription"));
				course.setCourseFee(rs.getInt("coursefee"));
				course.setCourseImg(rs.getString("courseimg"));
				course.setStatus(rs.getString("status"));

				courses.add(course);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return courses;

	}
}
