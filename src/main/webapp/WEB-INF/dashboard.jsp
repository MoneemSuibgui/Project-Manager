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
   	<div class="d-flex justify-content-between">
   		<h1>Welcome, <c:out value="${loggedUser.firstName }"></c:out></h1>
   		<p><a href="/logout">log out</a></p>
   	</div>
   	<div class="d-flex justify-content-between mt-3 mb-2">
   		<h3>All Projects</h3>
   		<a href="/projects/new" class="btn btn-success">+ new project</a>
   	</div>
   	<table class="table table-bordered table-hover text-center">
   		<tr>
   			<th>Project</th>
   			<th>Team Lead</th>
   			<th>Due Date</th>
   			<th class="text-start">Actions</th>
   		</tr>
   		   
   		 <!-- iterate through projects(all project exclude loggedUser project) and put condition to display all projects is not the list of loggedUser teamProjects -->
   		<c:forEach  var="project" items="${projects}">
   			
   			<c:if test="${!loggedUser.projectsTeam.contains(project)}">
	   			<tr>
		   				<th><a href="/projects/${project.id}"><c:out value="${project.title}"/></a></th>
		   				<th><c:out value="${project.creator.firstName}"/></th>
		   				<th><fmt:formatDate value="${project.dueDate}" pattern="MMMM yyyy"/></th>
		   				<th class="text-start">
			   				<a href="/join/project/${project.id}">Join teams</a>
		   				</th>
	   			</tr>
	   		</c:if>
   		</c:forEach>
   	</table>
   	
   	
   	<h3>Your projects</h3>
   		<table class="table table-bordered table-hover text-center">
   		<tr>
   			<th>Project</th>
   			<th>Lead</th>
   			<th>Due Date</th>
   			<th class="text-start">Actions</th>
   		</tr>
   		
   		<!-- iterate through all projects and check if project in the list of loggedUser teamProject or the loggedUser who created to diplay them -->
   		<c:forEach var="project" items="${allProjects}">
	   		<c:if test="${myProjects.contains(project) || (loggedUser.projectsTeam.contains(project))}">
		   			<tr>
			   			<th><a href="/projects/${project.id}"><c:out value="${project.title}"/></a></th>
			   			<th><c:out value="${project.creator.firstName}"/></th>
			   			<th> <fmt:formatDate pattern = "MMMM yyyy" value = "${project.dueDate}" /></th>
			   			<th class="text-start">
				   			<c:choose>
				   				<c:when test="${loggedUser.projectsTeam.contains(project)}">
				   					<a href="/leave/project/${project.id}">leave teams</a>
				   				</c:when>
				   				<c:otherwise>
				   					<a href="/projects/edit/${project.id}">edit</a>
				   				</c:otherwise>
				   			</c:choose>
			   			</th>
			   		</tr>
			  </c:if>
	   	</c:forEach>	
   	</table>
   </div>

</body>
</html>