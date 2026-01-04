<%@page import="dbConnecter.DbConnector"%>
<%@page import="dao.CourseDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="dao.JobApplyDao"%>
<%@ page import="model.Job"%>
<%@ page import="dbConnecter.DbConnector"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Job Applicants</title>
</head>
<body>

	<c:if
		test="${not empty sessionScope.userobj.role and sessionScope.userobj.role != 'admin'}">

		<c:redirect url="login.jsp"></c:redirect>

	</c:if>

	<c:if test="${empty sessionScope.userobj }">

		<c:redirect url="login.jsp"></c:redirect>

	</c:if>

	<%@include file="navbar.jsp"%>

	<div class="container-fluid ">
		<div class="row">
			<div class="col-12 col-md-10 offset-md-1">

				<table class="table">
					<thead>
						<tr>

							<th scope="col">Sr.No</th>
							<th scope="col">Job Title</th>
							<th scope="col">Total Applicants</th>
							<th scope="col">Action</th>

						</tr>
					</thead>
					<tbody>



						<%
						JobApplyDao jaDao = new JobApplyDao(DbConnector.getDbConnection());
						List<Job> jList = jaDao.getJobApplicantCount();
						int srno = 0;

						for (Job j : jList) {
						%>

						<tr>
							<td><%=++srno%></td>
							<td><%= j.getTitle() %></td>
							<td><%=j.getTotalJobApplicants()%></td>
							<td><a href="viewapplicantsdata.jsp?jobid=<%=j.getJobid() %> " class="btn btn-primary">View Applicants</a> </td>
						</tr>

						<%
						}
						%>



					</tbody>
				</table>

			</div>

		</div>


	</div>

	<!-- Success or Fail message  -->

	<c:if test="${not empty sessionScope.msg}">
		<div class="alert alert-success" role="alert">${sessionScope.msg}</div>
		<c:remove var="msg" scope="session" />
	</c:if>

	<c:if test="${not empty sessionScope.failmsg}">
		<div class="alert alert-danger" role="alert">${sessionScope.failmsg}</div>
		<c:remove var="failmsg" scope="session" />
	</c:if>

</body>
</html>