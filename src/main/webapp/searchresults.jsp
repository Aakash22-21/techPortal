<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="dao.CourseDao"%>
<%@ page import="dao.JobDao"%>
<%@ page import="dbConnecter.DbConnector"%>
<%@ page import="java.util.*"%>
<%@ page import="model.Course"%>
<%@ page import="model.Job"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Result</title>
</head>
<body>
	<%@include file="navbar.jsp"%>


	<!-- Page Header -->
	<section class="py-4">
		<div class="container">
			<h2 class="mb-3">Job Search Results</h2>

			<c:if test="${empty jobs}">
				<div class="alert alert-warning">No jobs found for your
					search.</div>
			</c:if>
		</div>
	</section>

	<!-- Job Cards -->
	<div class="container">
		<div class="row">
			<%

List<Job> list = (List<Job>) request.getAttribute("jobs");

for (Job j : list) {


%>

			<div class="col-md-4 col-sm-6 mb-4">

				<div class="card h-100">
					<div class="card-body">

						<h5 class="card-title"><%=j.getTitle()%></h5>
						<p class="card-text">
							Desc:-
							<%=j.getJobDesc()%></p>
						<p class="card-text">
							<b>Location </b><%=j.getLocation()%>
							<b>Category </b>
							<%=j.getCategory()%><b> Status:- </b><span
								style="color:<%=j.getStatus().equals("Active") ? "green" : "red"%>">
								<%=j.getStatus()%>
							</span>
						</p>
						<p class="card-text">
							Posted on:-
							<%=j.getpDate()%></p>

					</div>

					<div class="card-footer bg-transparent border-0">
						<a href="viewalljobs.jsp?jobId=<%=j.getJobid()%>"
							class="btn btn-custom btn-sm"> View Details </a>


					</div>
				</div>

			</div>
			<%
}
				%>


		</div>
	</div>







</body>
</html>