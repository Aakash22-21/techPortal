package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.ApplicantsData;
import model.Job;

public class JobApplyDao {

	private Connection conn;

	public JobApplyDao(Connection conn) {
		super();
		this.conn = conn;
	}

	// getting Job Applicant List Count
	public List<Job> getJobApplicantCount() {

		List<Job> jobsApplicants = new ArrayList<Job>();

		try {
			String query = "select j.jobid, j.title,count(ja.appid) AS total_Applicants  from job j LEFT JOIN jobapplication ja "
					+ "ON j.jobid = ja.jobid GROUP BY j.jobid, j.title";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Job job = new Job();
				job.setTitle(rs.getString("title"));
				job.setTotalJobApplicants(rs.getInt("total_Applicants"));
				job.setJobid(rs.getInt("jobid"));

				jobsApplicants.add(job);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jobsApplicants;

	}

	// Get Applicants Data
	public List<ApplicantsData> getApplicantsData(int jobid) {

		List<ApplicantsData> applicantsList = new ArrayList<ApplicantsData>();

		try {
			String query = "select ja.appid, ja.appdate, u.userid, u.fname, u.lname, u.email,u.mobno  from jobapplication ja JOIN user u"
					+ " ON ja.userid = u.userid where ja.jobid=?";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, jobid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				ApplicantsData data = new ApplicantsData();
				data.setApplicationId(rs.getInt("appid"));
				data.setEmail(rs.getString("email"));
				data.setFullName(rs.getString("fname") + " " + rs.getString("lname"));
				data.setUserId(rs.getInt("userid"));
				data.setMobNo(rs.getString("mobno"));
				data.setApplyDate(rs.getTimestamp("appdate") + "");

				applicantsList.add(data);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return applicantsList;
	}
}
