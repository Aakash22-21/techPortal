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
<title>Post Jobs</title>
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

				<form action="addjob" method="Post">
					<h1>Add Jobs</h1>


					<div class="form-group">
						<label for="cn">Job Title</label> <input type="text" name="title"
							class="form-control" id="ln" aria-describedby="lnn">
					</div>

					<div class="form-row">
						<div class="form-group col-md-4">
							<label for="inputState">Location</label> <select id="inputState"
								name="location" class="form-control">
								<option selected disabled="disabled">Choose..</option>
								<option value="Mumbai">Mumbai</option>
								<option value="Pune">Pune</option>
								<option value="Bengluru">Bengluru</option>
							</select>
						</div>



						<div class="form-group col-md-4">
							<label for="inputState">Category</label> <select id="inputState"
							name="category"	class="form-control">
								<option selected disabled="disabled">Choose..</option>
								<option value="IT">IT</option>
								<option value="Non-IT">Non-IT</option>
							</select>
						</div>


						<div class="form-group col-md-4">
							<label for="inputState">Status</label> <select id="inputState"
							name="status"	class="form-control">
								<option selected disabled="disabled">Choose..</option>
								<option value="Active">Active</option>
								<option value="In-Active">In-Active</option>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label for="exampleFormControlTextarea1">Job Description</label>
						<textarea class="form-control" id="exampleFormControlTextarea1"
							name="jobdesc" rows="3"></textarea>
					</div>

					<button type="submit" class="btn btn-custom">Add</button>
				</form>

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


	</div>
	</div>

	</div>

</body>
</html>