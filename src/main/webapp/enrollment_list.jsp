<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="dao.CourseDao"%>
<%@ page import="dbConnecter.DbConnector"%>
<%@ page import="java.util.*"%>
<%@ page import="model.Course"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Enrollment List</title>
</head>
<body>
	<%@include file="navbar.jsp"%>

	<div class="container-fluid ">
		<div class="row">
			<div class="col-12 col-md-10 offset-md-1">

				<table class="table">
					<thead>
						<tr>

							<th scope="col">Sr.No</th>
							<th scope="col">User Name</th>
							<th scope="col">Email</th>
							<th scope="col">Contact no</th>

						</tr>
					</thead>
					<tbody>

						<%
						List<User> enrolledUser = (List<User>) request.getAttribute("allenrolleduser");

						if (enrolledUser == null && enrolledUser.isEmpty()) {
						%>

						<tr>
							<td>No User Enrolled in this Course</td>
						</tr>


						<%
						} else {
						int sno = 1;
						for (User u : enrolledUser) {
						%>

						<tr>
							<td><%=sno++%></td>
							<td><%=u.getUserFname()%></td>
							<td><%=u.getUserEmail()%></td>
							<td><%=u.getMobno()%></td>
						</tr>

						<%
						}
						}
						%>


					</tbody>
				</table>

			</div>

		</div>


	</div>


</body>
</html>