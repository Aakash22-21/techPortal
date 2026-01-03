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
<title>Index</title>
</head>
<body>
	<%@include file="navbar.jsp"%>
	<!-- Slider -->
	<div id="carouselExampleControls" class="carousel slide"
		data-ride="carousel">
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="Slider_images/img1.jpg" class="d-block w-100" alt="img1">
			</div>
			<div class="carousel-item">
				<img src="Slider_images/img2.jpg" class="d-block w-100" alt="img2">
			</div>
			<div class="carousel-item">
				<img src="Slider_images/img3.jpg" class="d-block w-100" alt="img3">
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-target="#carouselExampleControls" data-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-target="#carouselExampleControls" data-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">Next</span>
		</button>
	</div>
	<!-- Main Body Started -->
	<div class="conatiner">

		<div class="row">

			<%
			CourseDao cdao = new CourseDao(DbConnector.getDbConnection());
			List<Course> list = cdao.getLatestCourses();

			for (Course c : list) {
			%>

			<div class="col-md-4 col-12">
				<div class="card m-4" style="width: 18rem;">
					<img src="course_img/<%=c.getCourseImg() %>" width="150px" height="200px"
						class="card-img-top" alt="...">
					<div class="card-body">
						<h5 class="card-title"><%=c.getCourseName()%></h5>
						<p class="card-text"><%=c.getCourseDesc()%></p>
						<p class="card-text"><%=c.getCourseFee()%></p>
						<p class="card-text"><%=c.getStatus()%></p>
						
						<a href="download?cid=<%=c.getCourseId() %>" class="btn btn-primary">Download Syllabus</a>
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