<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="dao.JobDao"%>
<%@ page import="model.Job"%>
<%@ page import="dbConnecter.DbConnector"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Jobs</title>
</head>
<body>
	<c:if test="${empty sessionScope.userobj}">
		<c:redirect url="login.jsp"></c:redirect>
	</c:if>
	<%@include file="navbar.jsp"%>

	<!-- Success or Fail message  -->

	<c:if test="${not empty sessionScope.msg}">
		<div class="alert alert-success" role="alert">${sessionScope.msg}</div>
		<c:remove var="msg" scope="session" />
	</c:if>

	<c:if test="${not empty sessionScope.warnmsg}">
		<div class="alert alert-warning" role="alert">${sessionScope.warnmsg}</div>
		<c:remove var="warnmsg" scope="session" />
	</c:if>

	<c:if test="${not empty sessionScope.failmsg}">
		<div class="alert alert-danger" role="alert">${sessionScope.failmsg}</div>
		<c:remove var="failmsg" scope="session" />
	</c:if>

	<div class="container-fluid">
		<div class="row">



			<div class="col-12 col-md-10 offset-md-1">


				<%
				JobDao jDao = new JobDao(DbConnector.getDbConnection());
				User user = (User) session.getAttribute("userobj");
				List<Job> jobList = jDao.getAllJobs(user.getRole());

				if (jobList != null && !jobList.isEmpty()) {

					for (Job j : jobList) {
				%>

				<div class="card w-100 m-2 ">
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

						<c:if test="${sessionScope.userobj.role=='normal' }">

							<a href="appplyjob?jid=<%=j.getJobid()%>" class="btn btn-primary">Apply</a>
							<a href="" class="btn btn-success">Read More...</a>

						</c:if>

						<c:if test="${sessionScope.userobj.role=='admin' }">

							<a href="editjob.jsp?jid=<%=j.getJobid()%>"
								class="btn btn-warning">Edit</a>
							<a href="deletejob?jid=<%=j.getJobid()%>" class="btn btn-danger">Delete</a>

						</c:if>
					</div>
				</div>


				<%
				}
				} else {
				%>
				<p class="lead text-primary">No Active Jobs Available!..</p>

				<%
				}
				%>



			</div>




		</div>
	</div>

</body>
</html>