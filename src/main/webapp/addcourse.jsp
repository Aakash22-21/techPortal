<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Course</title>
<link rel="stylesheet" href="style.css">
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

		<div class="container my-2">

			<div class="row">
				<div class="col-12 col-md-6 offset-md-3">

					<form action="addcourse" method="Post"
						enctype="multipart/form-data">
						<h1>Add Course</h1>
						<div class="form-group">
							<label for="exampleFormControlSelect1">Status</label> <select
								class="form-control" name="cstatus"
								id="exampleFormControlSelect1">
								<option value="" selected disabled>Select</option>
								<option value="Started">Started</option>
								<option value="Comming Soon">Comming Soon</option>
								<option value="Currently Not Available">Currently Not
									Available</option>

							</select>
						</div>

						<div class="form-group">
							<label for="cn">Course Name</label> <input type="text"
								name="cname" class="form-control" id="ln" aria-describedby="lnn">
						</div>

						<div class="form-group">
							<label for="exampleFormControlSelect1">Course Duration</label> <select
								class="form-control" name="cdur" id="exampleFormControlSelect1">
								<option value="" selected disabled>Select</option>
								<option value="3 Months">3 Months</option>
								<option value="6 Months">6 Months</option>
								<option value="12 Months">12 Months</option>
								<option value="24 Months (2 Years)">24 Months (2 Years)</option>

							</select>
						</div>

						<div class="form-group">
							<label for="cf">Course Fee</label> <input type="number"
								name="cfee" class="form-control" id="ln" aria-describedby="lnn">
						</div>

						<div class="form-group">
							<label for="exampleFormControlTextarea1">Course
								Description</label>
							<textarea class="form-control" id="exampleFormControlTextarea1"
								name="cdesc" rows="3"></textarea>
						</div>

						<div class="form-group">
							<label for="cic">Upload Course Image</label> <input type="file"
								name="cpic" class="form-control" id="ln" aria-describedby="lnn">

							<c:if test="${not empty sessionScope.imgmsg}">

								<div class="alert alert-warning" role="alert">${sessionScope.imgmsg}</div>


								<c:remove var="imgmsg" scope="session" />
							</c:if>
						</div>

						<div class="form-group">
							<label for="cpdf">Upload Course Syllabus</label> <input
								type="file" name="cpdf" class="form-control"
								accept="application/pdf" id="ln" aria-describedby="lnn">

							<c:if test="${not empty sessionScope.pdfmsg}">

								<div class="alert alert-warning" role="alert">${sessionScope.pdfmsg}</div>


								<c:remove var="pdfmsg" scope="session" />
							</c:if>
						</div>

						<button type="submit" class="btn btn-custom">Add</button>
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