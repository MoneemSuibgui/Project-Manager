<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Project Manger Dashboard</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->
</head>
<body>
   <div class="container mt-3 pt-3">
	   	<div class="d-flex justify-content-between mt-4">
	   		<div>
	   			<h1>Project : <c:out value="${project.title}"></c:out></h1>
	   			<h3>Project lead : <c:out value="${project.creator.firstName}"></c:out></h3>
	   		</div>
	   		<p><a href="/dashboard">Back to Dashboard</a></p>
	   	</div>
	   	<div class="mb-4">
	   	<!-- add new task using Spring MVC form -->
		   	<form:form action="/projects/${project.id}/tasks" method="POST" modelAttribute="task">
		   		
		   		
		   		<div class="row mt-5">
		   			<p class="text-danger"><form:errors path="ticket"/></p>
		   			<form:label path="ticket" class="col-3">Add a task ticket for this team</form:label>
		   			<form:textarea path="ticket" class="col-5 form-control"  placeholder="Enter task ticket for this team project" />
		   		</div>	
		   		<div class="d-flex justify-content-end mt-3">
		   			<button class="btn btn-success btn-lg shadow">Submit</button>
		   		</div>
		   		
		   	</form:form>
	   	</div>
	   	<div class="mb-3">
	   	<!-- Display all tasks info for project -->
	   		<c:forEach var="task" items="${tasks}">
	   			<h3 class="mb-1">Added By : <c:out value="${task.creatorTask.firstName}"/>
	   			at <span class="bg-warning p-1 rounded-3"><fmt:formatDate pattern="HH:mm MMM dd" value="${task.getCreatedAt() }"/></span> :
	   			</h3>
	   			<p><c:out value="${task.ticket}"></c:out>.</p>
	   		</c:forEach>
	   	</div>
   	</div>
</body>
</html>