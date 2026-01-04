<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dbConnecter.DbConnector"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="dao.JobApplyDao"%>
<%@ page import="model.ApplicantsData"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Applicants Data</title>
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
							<th scope="col">Name</th>
							<th scope="col">Email</th>
							<th scope="col">Contact No.</th>
							<th scope="col">Application Date</th>

						</tr>
					</thead>
					<tbody>

						<%
						String jid = request.getParameter("jobid");
						int jobId = Integer.parseInt(jid);

						JobApplyDao jad = new JobApplyDao(DbConnector.getDbConnection());
						List<ApplicantsData> applicantList = jad.getApplicantsData(jobId);
						int srno = 1;
						for (ApplicantsData ad : applicantList) {
						%>

						<tr>
							<td><%=srno++%></td>
							<td><%=ad.getFullName()%></td>
							<td><%=ad.getEmail()%></td>
							<td><%=ad.getMobNo()%></td>
							<td><%=ad.getApplyDate()%></td>
						</tr>

						<%
						}
						%>


					</tbody>
				</table>

			</div>

		</div>


	</div>

</body>
</html>