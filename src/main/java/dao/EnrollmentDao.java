package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class EnrollmentDao {

	private Connection conn;

	public EnrollmentDao(Connection conn) {
		super();
		this.conn = conn;
	}

	// method to save Enrollment Details
	public boolean enrollUser(int userId, int courseId) {
		boolean isenroll = false;

		try {
			// enrollid, myuserid, mycourseid
			String query = "insert into enrollment (myuserid,mycourseid) values (?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			ps.setInt(2, courseId);
			int i = ps.executeUpdate();
			if (i == 1) {
				isenroll = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isenroll;

	}

	// method to check if user is already enroll or not
	public boolean isUserAlreadyEnrolled(int userId, int courseId) {
		boolean isAlreadyenroll = false;

		try {
			// enrollid, myuserid, mycourseid
			String query = "select * from enrollment where myuserid=? and mycourseid=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			ps.setInt(2, courseId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				isAlreadyenroll = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return isAlreadyenroll;

	}

	// get Course name based on id
	public String getCourseNameById(int courseId) {
		String courseName = "";
		try {
			String query = "select coursename from course where cid=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, courseId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				courseName = rs.getString("coursename");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseName;
	}

	// get Enrollment details by cid and by joing user and course table
	public List<User> getUserInfoByCourseId(int courseId) {

		List<User> enrolledUser = new ArrayList<User>();

		try {
			String query = "select u.userid, u.fname, u.email, u.mobno from user u "
					+ "JOIN enrollment e ON u.userid = e.myuserid where e.mycourseid = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, courseId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("userid"));
				user.setUserFname(rs.getString("fname"));
				user.setUserEmail(rs.getString("email"));
				user.setMobno(rs.getString("mobno"));

				enrolledUser.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return enrolledUser;

	}
	

}
