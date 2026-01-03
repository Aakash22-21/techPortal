<%@page import="dao.JobDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="dao.JobDao"%>
<%@ page import="dbConnecter.DbConnector"%>
<%@ page import="java.util.*"%>
<%@ page import="model.Job"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Jobs</title>
</head>
<body>
	<%@include file="navbar.jsp"%>

	<%
	JobDao jDao = new JobDao(DbConnector.getDbConnection());
	String jobId = request.getParameter("jid");
	Job j = jDao.getJobsDataById(Integer.parseInt(jobId));
	%>

	<div class="container my-2">

		<div class="row">
			<div class="col-12 col-md-6 offset-md-3">

				<form action="updatejob" method="Post">
					<h1>Edit Jobs</h1>

					<input type="hidden" name="jobid" value="<%=j.getJobid()%>">
					<div class="form-group">
						<label for="cn">Job Title</label> <input type="text" name="title"
							class="form-control" id="ln" value="<%=j.getTitle()%>"
							aria-describedby="lnn">
					</div>

					<div class="form-row">
						<div class="form-group col-md-4">
							<label for="inputState">Location</label> <select id="inputState"
								name="location" class="form-control">
								<option value="<%=j.getLocation()%>"><%=j.getLocation()%></option>
								<option value="Mumbai">Mumbai</option>
								<option value="Pune">Pune</option>
								<option value="Bengluru">Bengluru</option>
							</select>
						</div>



						<div class="form-group col-md-4">
							<label for="inputState">Category</label> <select id="inputState"
								name="category" class="form-control">
								<option value="<%=j.getCategory()%>"><%=j.getCategory()%>
								</option>
								<option value="IT">IT</option>
								<option value="Non-IT">Non-IT</option>
							</select>
						</div>


						<div class="form-group col-md-4">
							<label for="inputState">Status</label> <select id="inputState"
								name="status" class="form-control">
								<option value="<%=j.getStatus()%>"><%=j.getStatus()%></option>
								<option value="Active">Active</option>
								<option value="In-Active">In-Active</option>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label for="exampleFormControlTextarea1">Job Description</label>
						<textarea class="form-control" id="exampleFormControlTextarea1"
							name="jobdesc" rows="3"><%=j.getJobDesc()%></textarea>
					</div>

					<button type="submit" class="btn btn-success">Update</button>
				</form>

			</div>
		</div>
	</div>

	<!-- Success or Fail message  -->

	


	</div>
	</div>

	</div>

</body>
</html>