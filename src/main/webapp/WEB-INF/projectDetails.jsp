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
	   	<div class="d-flex justify-content-between mb-5">
	   		<h1>Project Details</h1>
	   		<h4><a href="/dashboard">Back to Dashboard</a></h4>
	   	</div>
	   	<div class="d-flex justify-content-around  mb-3">
	   		<h4>Project :</h4>
	   		<h4><c:out value="${project.title}"/></h4>
	   	</div>
	   	
	   	<div class="d-flex justify-content-around mb-3">
	   		<h4>Description :</h4>
	   		<h4><c:out value="${project.description}"/></h4>
	   	</div>
	   	
	   	<div class="d-flex justify-content-around mb-3">
	   		<h4>Due Date :</h4>
	   		<h4><fmt:formatDate value="${project.dueDate}" pattern="MM/dd/yyyy"/></h4>
	   	</div>
	   	
	   	<!-- If the logged user in the list of usersTeam of project or the logged user who's create the project,it can See and add tasks -->
	   	<c:if test="${project.usersTeam.contains(loggedUser)}">
	   		<a href="/projects/${project.id}/tasks">See Tasks</a>
   		</c:if>
   		
   		<!-- if the creator of project is the logged in user ,it can delete her own project   -->
   		<c:if test="${project.creator.id == loggedUser.id }">
	   		<div class="d-flex justify-content-end mt-3">
	   			<form action="/projects/${project.id }" method="POST">
	   				<input type="hidden" name="_method" value="DELETE"/>
	   				<button class="btn btn-danger btn-lg shadow">Delete Project</button>
	   			</form>
	   		</div>
	   	</c:if>
   </div>
   
   

</body>
</html>