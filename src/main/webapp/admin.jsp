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

	<c:if
		test="${not empty sessionScope.userobj.role and sessionScope.userobj.role != 'admin'}">

		<c:redirect url="login.jsp"></c:redirect>

	</c:if>

	<c:if test="${empty sessionScope.userobj }">

		<c:redirect url="login.jsp"></c:redirect>

	</c:if>

	<%@include file="navbar.jsp"%>

	<div class="container py-5">
	 <h2 class="mb-4">Admin Dashboard</h2>
		<div class="row">

			 <div class="col-md-3 col-sm-6">
            <a href="addcourse.jsp" class="card-link">
                <div class="card card-custom text-center p-4">
                    <h5>Add Course</h5>
                </div>
            </a>
        </div>
        
		  <div class="col-md-3 col-sm-6">
            <a href="viewcourse.jsp" class="card-link">
                <div class="card card-custom text-center p-4">
                    <h5>View Course Info</h5>
                </div>
            </a>
        </div>
        
		 <div class="col-md-3 col-sm-6">
            <a href="addjobs.jsp" class="card-link">
                <div class="card card-custom text-center p-4">
                    <h5>Post Jobs</h5>
                </div>
            </a>
        </div>
        
			 <div class="col-md-3 col-sm-6">
            <a href="viewalljobs.jsp" class="card-link">
                <div class="card card-custom text-center p-4">
                    <h5>View Jobs</h5>
                </div>
            </a>
        </div>

			   <div class="col-md-3 col-sm-6">
            <a href="jobapplicants.jsp" class="card-link">
                <div class="card card-custom text-center p-4">
                    <h5>Job Applications</h5>
                </div>
            </a>
        </div>

		</div>
	</div>

</body>
</html>