package model;

import java.sql.Date;

public class ApplicantsData {
	private int applicationId;
	private int userId;
	private String fullName;
	private String email;
	private String applyDate;
	private String mobNo;

	public String getMobNo() {
		return mobNo;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getApplyDate() {
		return applyDate;
	}

	

}
