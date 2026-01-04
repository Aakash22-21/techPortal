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
	<section class="hero-job">
    <div class="container">
        <div class="row">
            <div class="col-md-8">

                <h1 class="hero-title">
                    Your Next Big Move <br>
                    <span>Starts Here.</span>
                </h1>

                <p class="hero-subtitle">
                    Learn skills, explore jobs, and grow your career with us.
                </p>

                <!-- Search Bar -->
                <form action="indexpagesearch" method="get" class="hero-search mt-4">
                    <input type="text" name="jobSearch" class="form-control"
                           placeholder="Job title, keywords">
                    <input type="text" name="courseSearch"  class="form-control"
                           placeholder="Search Courses">
                    <button class="btn btn-custom">Search</button>
                </form>

            </div>
        </div>
    </div>
</section>
	
	<!-- Main Body Started -->
	<div class="container">

		<div class="row">

			<%
			CourseDao cdao = new CourseDao(DbConnector.getDbConnection());
			List<Course> list = cdao.getLatestCourses();

			for (Course c : list) {
			%>

			<div class="col-md-4 col-12 container py-5">
				<div class="card card-custom course-img-wrapper m-4 h-100">
					<img src="course_img/<%=c.getCourseImg() %>" 
     class="card-img-top img-fluid"
     style="height:200px; object-fit:contain;">

					<div class="card-body">
						<h5 class="card-title"><%=c.getCourseName()%></h5>
						<p class="card-text"><%=c.getCourseDesc()%></p>
						<p class="card-text course-price"><%=c.getCourseFee()%></p>
						<p class="card-text course-status"><%=c.getStatus()%></p>
						
						<a href="download?cid=<%=c.getCourseId() %>" class="btn btn-custom">Download Syllabus</a>
					</div>
				</div>
			</div>
			<%
			}
			%>

		</div>
	</div>
</body>
<script>
document.querySelector('[name="jobSearch"]').addEventListener('input', function() {
    document.querySelector('[name="courseSearch"]').disabled = this.value.length > 0;
});
document.querySelector('[name="courseSearch"]').addEventListener('input', function() {
    document.querySelector('[name="jobSearch"]').disabled = this.value.length > 0;
});
</script>

</html>