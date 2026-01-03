<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Panel</title>
</head>
<body>
	<%@include file="navbar.jsp"%>

	<div class="container-fluid">
		<div class="row">
			
			<div class="col-md-3">

				<div class="card admincard mycard" >
					<div class="card-body">
						<a href="addcourse.jsp" class="card-link text-light">Add Course </a>
					</div>
				</div>

			</div>
			<div class="col-md-3">
			
			<div class="card admincard mycard" >
					<div class="card-body">
						<a href="viewcourse.jsp" class="card-link text-light">View Course Info </a>
					</div>
				</div>
			
			</div>
			<div class="col-md-3">
			
			<div class="card admincard mycard" >
					<div class="card-body">
						<a href="addjobs.jsp" class="card-link text-light">Post Jobs </a>
					</div>
				</div>
			
			</div>
			<div class="col-md-3">
			
			<div class="card admincard mycard" >
					<div class="card-body">
						<a href="viewalljobs.jsp" class="card-link text-light">View Jobs </a>
					</div>
				</div>
			
			</div>
		</div>
	</div>

</body>
</html>