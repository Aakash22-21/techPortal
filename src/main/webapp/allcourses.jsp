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
<title>All Courses</title>
</head>
<body>
	<%@include file="navbar.jsp"%>

	<!-- filter portion 	 -->



	<div class="d-flex flex-row bd-highlight mb-3">

		<div class="p-2 bd-highlight">
			<form action="viewcourses" method="get"
				class="form-inline my-2 my-lg-0">

				<input name="search" class="form-control mr-sm-2" type="text"
					placeholder="Search" aria-label="Search">
				<button name="" class="btn btn-outline-success my-2 my-sm-0"
					type="submit">Search</button>

			</form>
		</div>

		<div class="p-2 bd-highlight">
			<div class="form-group">
				<form action="viewcourses" method="get"
					class="form-inline my-2 my-lg-0">

					<select class="form-control" name="status"
						id="exampleFormControlSelect1" onchange="this.form.submit()">
						<option value="">All Status</option>
						<%
						List<String> statuses = (List<String>) application.getAttribute("statusList");
						if (statuses != null) {

							for (String s : statuses) {
						%>
						<option value="<%=s%>"
							<%=s.equals(request.getParameter("status")) ? "selected" : ""%>><%=s%></option>

						<%
						}
						}
						%>

					</select>
			</div>
			</form>
		</div>

		<div class="p-2 bd-highlight">
			<div class="form-group">
				<form action="viewcourses" method="get"
					class="form-inline my-2 my-lg-0">

					<select class="form-control" name="fee"
						id="exampleFormControlSelect1" onchange="this.form.submit()">
						<option value="">Sort by Fees</option>
						<option value="low-high"
							<%="low-high".equals(request.getParameter("fee")) ? "selected" : ""%>>Low
							- High</option>
						<option value="high-low"
							<%="high-low".equals(request.getParameter("fee")) ? "selected" : ""%>>High
							- Low</option>
					</select>
			</div>
			</form>
		</div>
	</div>
	<!-- filter portion 	 -->

	<!-- Success or Fail message  -->

	<c:if test="${not empty sessionScope.msg}">
		<div class="alert alert-success" role="alert">${sessionScope.msg}</div>
		<c:remove var="msg" scope="session" />
	</c:if>

	<c:if test="${not empty sessionScope.failmsg}">
		<div class="alert alert-danger" role="alert">${sessionScope.failmsg}</div>
		<c:remove var="failmsg" scope="session" />
	</c:if>

	<!-- Success or Fail message  -->
	
	<div class="row m-2">

		<%
		List<Course> list = (List<Course>) request.getAttribute("courses");
		if (list == null || list.isEmpty()) {
		%>

		<p class="text-success">No Course Available !</p>

		<%
		} else {
		for (Course c : list) {
		%>

		<div class="col-md-4 col-12">
			<div class="card m-4" style="width: 18rem;">
				<img src="course_img/<%=c.getCourseImg()%>" width="150px"
					height="200px" class="card-img-top" alt="Course Img">
				<div class="card-body">
					<h5 class="card-title"><%=c.getCourseName()%></h5>
					<p class="card-text"><%=c.getCourseDesc()%></p>
					<p class="card-text"><%=c.getCourseFee()%></p>
					<p class="card-text"><%=c.getStatus()%></p>

					<a href="download?cid=<%=c.getCourseId()%>" class="btn btn-primary">Download
						Syllabus</a> <a href="enrollment?cid=<%=c.getCourseId()%>"
						class="btn btn-primary">Enroll</a>
				</div>
			</div>
		</div>
		<%
		}
		}
		%>

	</div>

</body>
</html>