package model;

public class Course {

	private int courseId;
	private String courseName;
	private String courseDuration;
	private int courseFee;
	private String status;
	private String courseDesc;
	private String courseImg;
	private String pdfName;
	private int userId;
	// creating constructor
	
	public Course() {
		super();
	}
	
	public Course(int courseId, String courseName, String courseDuration, int courseFee, String status,
			String courseDesc, String courseImg, String pdfName, int userId) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseDuration = courseDuration;
		this.courseFee = courseFee;
		this.status = status;
		this.courseDesc = courseDesc;
		this.courseImg = courseImg;
		this.pdfName = pdfName;
		this.userId = userId;
	}
	
	// creating getter and setter
	public int getCourseId() {
		return courseId;
	}
	
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseDuration() {
		return courseDuration;
	}
	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}
	public int getCourseFee() {
		return courseFee;
	}
	public void setCourseFee(int courseFee) {
		this.courseFee = courseFee;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCourseDesc() {
		return courseDesc;
	}
	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}
	public String getCourseImg() {
		return courseImg;
	}
	public void setCourseImg(String courseImg) {
		this.courseImg = courseImg;
	}
	public String getPdfName() {
		return pdfName;
	}
	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
}
