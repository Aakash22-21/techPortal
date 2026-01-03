package model;

public class Job {

	private int jobid;
	private String title;
	
	private String category;
	private String status;
	private String location;
	private String pDate;
	private String jobDesc;
	
	// to Store JobAplicants Count
	private int totalJobApplicants;
	
	public int getTotalJobApplicants() {
		return totalJobApplicants;
	}
	public void setTotalJobApplicants(int totalJobApplicants) {
		this.totalJobApplicants = totalJobApplicants;
	}
	public String getJobDesc() {
		return jobDesc;
	}
	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}
	public int getJobid() {
		return jobid;
	}
	public void setJobid(int jobid) {
		this.jobid = jobid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getpDate() {
		return pDate;
	}
	public void setpDate(String pDate) {
		this.pDate = pDate;
	}
	
	//getter Setter
	
	
}
