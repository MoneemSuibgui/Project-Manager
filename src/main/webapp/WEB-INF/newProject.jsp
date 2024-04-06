<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Project</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<!-- change to match your file/naming structure -->
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/app.js"></script>
<!-- change to match your file/naming structure -->
</head>
<body>
	<div class="container mt-3 pt-3">
		<h1 class="mb-4">Create a Project</h1>
		<div class="col-8 mt-5 mx-5">
			<form:form action="/projects/new" methods="post" modelAttribute="project">
	
				<!-- displaying custom errors -->
				<p class="text-danger"><form:errors path="title"></form:errors></p>
				<div class="d-flex justify-content-around mb-3 mt-5">
					<form:label path="title" class="col-4">Project Title : </form:label>
					<form:input path="title" class="form-control" placeholder="Enter your project title" />
				</div>
				
				<!-- displaying custom errors -->
				<p class="text-danger"><form:errors path="description"></form:errors></p>
				<div class="d-flex justify-content-around mb-3">
					<form:label path="description" class="col-4">Project Description : </form:label>
					<form:textarea path="description" class="form-control" placeholder="Enter few line to describe your project"/>
				</div>
				
				<!-- displaying custom errors -->
				<p class="text-danger"><form:errors path="dueDate"></form:errors></p>
				<div class="d-flex justify-content-around mb-3">
					<form:label path="dueDate" class="col-4">Due Date : </form:label>
					<form:input path="dueDate" type="date" class="form-control" placeholder="Enter few line to describe your project"/>
				</div>
				
				<div class="d-flex justify-content-end mt-5">
					<a href="/dashboard" class="btn btn-warning btn-lg mx-3">Cancel</a>
	   				<button class="btn btn-primary btn-lg shadow  btn-lg mx-3">Submit</button>
	   			</div>
			</form:form>
		</div>
	</div>
</body>
</html>