package model;

public class User {
	private int userId;
	private String userFname;
	private String userLname;
	private String userEmail;
	private String role;
	private String Password;
	private String userImg;
	private String mobno;

//	Creating Constructor
	public User() {

	}

	public User(String userFname, String userLname, String userEmail, String role, String password, String userImg,
			String mobno) {
		super();
		this.userFname = userFname;
		this.userLname = userLname;
		this.userEmail = userEmail;
		this.role = role;
		Password = password;
		this.userImg = userImg;
		this.mobno = mobno;
	}

//Generating Getter & Setter 
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserFname() {
		return userFname;
	}

	public void setUserFname(String userFname) {
		this.userFname = userFname;
	}

	public String getUserLname() {
		return userLname;
	}

	public void setUserLname(String userLname) {
		this.userLname = userLname;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public String getMobno() {
		return mobno;
	}

	public void setMobno(String mobno) {
		this.mobno = mobno;
	}

}
