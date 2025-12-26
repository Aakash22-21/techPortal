package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;

public class UserDao {

	private static Connection conn;

	public UserDao(Connection conn) {
		super();
		this.conn = conn;
	}

	// Save user details

	public boolean saveUser(User user) {
		boolean saved = false;

		try {

			String query = "insert into user(fname,lname,email,role,pass,imagename,mobno) values(?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, user.getUserFname());
			ps.setString(2, user.getUserLname());
			ps.setString(3, user.getUserEmail());
			ps.setString(4, user.getRole());
			ps.setString(5, user.getPassword());
			ps.setString(6, user.getUserImg());
			ps.setString(7, user.getMobno());

			int i = ps.executeUpdate();

			if (i == 1) {
				saved = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return saved;

	}

	// Check email is already present or not

	public boolean isEmailExists(String email) {
		boolean exists = false;

		try {
			String query = "select * from user where email=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				exists = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return exists;
	}

	// Checking email and password for login purpose
	public User getUserByEmailAndPassword(String email, String pass) {

		User user = null;

		try {
			String query = "select * from user where email=? and pass=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, email);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				// fetching all the data from rs and set the data to object
				// userid, fname, lname, email, role, pass, imagename, mobno
				int uid = rs.getInt("userid");
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				String uemail = rs.getString("email");
				String role = rs.getString("role");
				String img = rs.getString("imagename");
				String mob = rs.getString("mobno");

				user = new User();
				user.setUserId(uid);
				user.setUserFname(fname);
				user.setUserLname(lname);
				user.setUserEmail(uemail);
				user.setRole(role);
				user.setUserImg(img);
				user.setMobno(mob);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;

	}

}
