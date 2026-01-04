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
<title>Edit Course</title>
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

	<%
	CourseDao cdao = new CourseDao(DbConnector.getDbConnection());
	String cid = request.getParameter("cid");
	int courseId = Integer.parseInt(cid);
	Course c = cdao.getCourseById(courseId);
	%>



	<div class="container my-2">

		<div class="row">
			<div class="col-12 col-md-6 offset-md-3">

				<form action="editcourse" method="Post" enctype="multipart/form-data">
					<h1>Edit Course</h1>
					
					<input type="hidden" name="courseid" value="<%=c.getCourseId() %>">
					
					<div class="form-group">
						<label for="exampleFormControlSelect1">Status</label> <select
							class="form-control" name="cstatus"
							id="exampleFormControlSelect1">
							<option value="<%=c.getStatus()%>" ><%=c.getStatus()%></option>
							<option value="Started">Started</option>
							<option value="Comming Soon">Comming Soon</option>
							<option value="Currently Not Available">Currently Not
								Available</option>

						</select>
					</div>

					<div class="form-group">
						<label for="cn">Course Name</label> <input type="text"
							name="cname" value="<%=c.getCourseName()%>"
							class="form-control" id="ln" aria-describedby="lnn">
					</div>

					<div class="form-group">
						<label for="exampleFormControlSelect1">Course Duration</label> <select
							class="form-control" name="cdur" id="exampleFormControlSelect1">
							<option value="<%=c.getCourseDuration()%>" ><%=c.getCourseDuration()%></option>
							<option value="3 Months">3 Months</option>
							<option value="6 Months">6 Months</option>
							<option value="12 Months">12 Months</option>
							<option value="24 Months (2 Years)">24 Months (2 Years)</option>

						</select>
					</div>

					<div class="form-group">
						<label for="cf">Course Fee</label> <input type="number"
							name="cfee" value="<%=c.getCourseFee()%>" class="form-control"
							id="ln" aria-describedby="lnn">
					</div>

					<div class="form-group">
						<label for="exampleFormControlTextarea1">Course
							Description</label>
						<textarea class="form-control" id="exampleFormControlTextarea1"
							name="cdesc" value="<%=c.getCourseDesc()%>" rows="3"><%=c.getCourseDesc()%></textarea>
					</div>

					<div class="form-group">

					<input type="hidden" name="existingpic" value=" <%=c.getCourseImg()%>">

						<img src="course_img/<%=c.getCourseImg()%>" height="90px"
							width="90px" class="m-2"> <input type="file" name="cpic"
							class="form-control" id="ln" aria-describedby="lnn"> <label
							for="cic">Upload Course Image</label>
						<c:if test="${not empty sessionScope.imgmsg}">

							<div class="alert alert-warning" role="alert">${sessionScope.imgmsg}</div>


							<c:remove var="imgmsg" scope="session" />
						</c:if>
					</div>

					<div class="form-group">
					
					<input type="hidden" name="existingpdf" value=" <%=c.getPdfName()%>">
					
					<a href="course_pdf/<%= c.getPdfName() %> " target="_blank" >View Pdf</a>
					
						<input type="file" name="cpdf" class="form-control"
							accept="application/pdf" id="ln" aria-describedby="lnn">

						<label for="cpdf">Upload Course Syllabus</label>

						<c:if test="${not empty sessionScope.pdfmsg}">

							<div class="alert alert-warning" role="alert">${sessionScope.pdfmsg}</div>


							<c:remove var="pdfmsg" scope="session" />
						</c:if>
					</div>

					<button type="submit" class="btn admincard">Update</button>
				</form>

				<!-- Success or Fail message  -->

				<c:if test="${not empty sessionScope.msg}">
					<div class="alert alert-success" role="alert">${sessionScope.msg}</div>
					<c:remove var="msg" scope="session" />
				</c:if>

				<c:if test="${not empty sessionScope.failmsg}">
					<div class="alert alert-danger" role="alert">${sessionScope.failmsg}</div>
					<c:remove var="failmsg" scope="session" />
				</c:if>


			</div>
		</div>

	</div>

</body>
</html>