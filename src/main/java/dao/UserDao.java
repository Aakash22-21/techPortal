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

}
