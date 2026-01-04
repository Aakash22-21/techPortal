package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.print.event.PrintJobAttributeListener;

import model.Job;

public class JobDao {
	private Connection conn;

	public JobDao(Connection conn) {
		super();
		this.conn = conn;
	}

	// Method to add job
	public boolean addJob(Job job) {
		boolean jobAdded = false;

		try {
//			jobid, title, jobdesc, cat, status, location, postdate
			String query = "insert into job (title, jobdesc, cat, status, location) values (?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, job.getTitle());
			ps.setString(2, job.getJobDesc());
			ps.setString(3, job.getCategory());
			ps.setString(4, job.getStatus());
			ps.setString(5, job.getLocation());

			int i = ps.executeUpdate();
			if (i == 1) {
				jobAdded = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobAdded;

	}

	// Method to get List of all Jobs
	public List<Job> getAllJobs(String role) {

		List<Job> jobList = new ArrayList<Job>();
		Job j = null;

		try {

			String query;
			// Check user role and based on that fetch only Jobs if user is Normal then only
			// fetch ACTIVE jobs
			// and if User is admin then fetch all jobs.
			if (role.equals("normal")) {
				query = "select * from job where status='active' order by jobid DESC";
			} else {
				query = "select * from job order by jobid DESC";
			}

			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				j = new Job();
//				jobid, title, jobdesc, cat, status, location, postdate
				j.setJobid(rs.getInt("jobid"));
				j.setTitle(rs.getString("title"));
				j.setJobDesc(rs.getString("jobdesc"));
				j.setCategory(rs.getString("cat"));
				j.setStatus(rs.getString("status"));
				j.setLocation(rs.getString("location"));
				j.setpDate(rs.getTimestamp("postdate") + "");

				jobList.add(j);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return jobList;

	}

	// get job by jobid
	public Job getJobsDataById(int id) {
		Job j = null;

		try {
			String query = "select * from job where jobid=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				j = new Job();

				j.setJobid(rs.getInt("jobid"));
				j.setTitle(rs.getString("title"));
				j.setJobDesc(rs.getString("jobdesc"));
				j.setCategory(rs.getString("cat"));
				j.setStatus(rs.getString("status"));
				j.setLocation(rs.getString("location"));
				j.setpDate(rs.getTimestamp("postdate") + "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}

	// Method to Update Job By Id
	public boolean updateJob(Job job) {
		boolean isUpdated = false;

		try {
//			jobid, title, jobdesc, cat, status, location, postdate
			String query = "update job set title=?, jobdesc=?, cat=?, status=?, location=? where jobid = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, job.getTitle());
			ps.setString(2, job.getJobDesc());
			ps.setString(3, job.getCategory());
			ps.setString(4, job.getStatus());
			ps.setString(5, job.getLocation());
			ps.setInt(6, job.getJobid());
			int i = ps.executeUpdate();
			if (i == 1)
				isUpdated = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUpdated;
	}

	// Delete Job by Id
	public boolean deleteJobById(int id) {
		boolean isDeleted = false;

		try {
			String query = "delete from job where jobid = ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, id);
			int i = ps.executeUpdate();
			if (i == 1)
				isDeleted = true;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return isDeleted;
	}

	// Apply Job by id
	public boolean applyForJob(int userId, int JobId) {
		boolean isApplied = false;

		try {
			String query = "insert into jobapplication (userid, jobid) values (?,?)";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			ps.setInt(2, JobId);
			int i = ps.executeUpdate();

			if (i == 1)
				isApplied = true;

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return isApplied;

	}

	// Checking if User is already applied in one job for multiple times
	public boolean checkAlreadyApplied(int userId, int JobId) {
		boolean alreadyApplied = false;

		try {
			String query = "select * from jobapplication where userid=? and jobid=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, userId);
			ps.setInt(2, JobId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				alreadyApplied = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return alreadyApplied;

	}
	// Method to Search job by name
	public List<Job> getJobByName(String jname){
		List<Job> jobList = new ArrayList<Job>();
		
		try {
			String query = "select * from job where title like ?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, "%" + jname + "%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Job job = new Job();
//				jobid, title, jobdesc, cat, status, location, postdate
				job.setTitle(rs.getString("title"));
				job.setJobDesc(rs.getString("jobdesc"));
				job.setCategory(rs.getString("cat"));
				job.setStatus(rs.getString("status"));
				job.setLocation(rs.getString("location"));
				job.setpDate(rs.getTimestamp("postdate")+ "");
				
				jobList.add(job);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobList;
	}
	

}
