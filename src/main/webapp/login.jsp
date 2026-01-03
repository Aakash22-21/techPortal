<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
	<%@include file="navbar.jsp"%>


	<div class="container">
		<div class="row">
			<div class="col-12 col-md-6 offset-md-3 my-2 border">
				<!-- login Form Starts -->
				<form action="login" method="post">
					<div class="form-group">
						<label for="exampleInputEmail1">Email address</label> <input
							type="email" name="email" class="form-control"
							id="exampleInputEmail1" aria-describedby="emailHelp"> <small
							id="emailHelp" class="form-text text-muted">We'll never
							share your email with anyone else.</small>
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Password</label> <input
							type="password" name="pass" class="form-control"
							id="exampleInputPassword1">
					</div>

					<button type="submit" class="btn btn-primary">login</button>
				</form>
				<!-- login Form Ends -->
				<c:if test="${not empty sessionScope.msg}">
					<div class="alert alert-success" role="alert">${sessionScope.msg}</div>
					<c:remove var="msg" scope="session" />
				</c:if>

				<!-- Invalid login MS -->
				<c:if test="${not empty sessionScope.msg}">

					<c:if test="${sessionScope.msg == 'Invalid email or Password'}">

						<div class="alert alert-danger" role="alert">Invalid email
							or Password</div>
					</c:if>
					<c:remove var="msg" scope="session" />
				</c:if>


			</div>
		</div>


	</div>

</body>
</html>