<%@page import="dbConnecter.DbConnector"%>
<%@page import="dao.CourseDao"%>
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
<title>View Course</title>
</head>
<body>
	<%@include file="navbar.jsp"%>

	<div class="container-fluid ">
		<div class="row">
			<div class="col-12 col-md-10 offset-md-1">

				<table class="table">
					<thead>
						<tr>

							<th scope="col">Course Img</th>
							<th scope="col">Course Name</th>
							<th scope="col">Status</th>
							<th scope="col">Edit</th>
							<th scope="col">Delete</th>
							<th scope="col">Enrollments</th>
						</tr>
					</thead>
					<tbody>

						<%
						CourseDao cdao = new CourseDao(DbConnector.getDbConnection());
						List<Course> clist = cdao.getAllCourse();

						for (Course c : clist) {
						%>



						<tr>
							<td><img src="course_img/<%=c.getCourseImg()%>"
								class="img-thumbnail" width="50px" height="50px"
								alt="course_Image"></td>
							<td><%=c.getCourseName()%></td>
							<td><%=c.getStatus()%></td>
							<td><a href="editcourse.jsp?cid=<%=c.getCourseId()%>"
								class="btn btn-warning">Edit</a></td>
							<td><a href="delete?cid=<%=c.getCourseId()%>" class="btn btn-danger">Delete</a></td>
							<td><a href="" class="btn btn-success">Enrollment</a></td>
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