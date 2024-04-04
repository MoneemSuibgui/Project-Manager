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
    <title>Project Manager</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->
</head>
<body>
   <div class="container mt-5 p-3">
	   <div class="text-center">
		   	<h1 class="text-info">Project Manager</h1>
		   	<p>A place for Teams to manage projects</p>
	   	</div>
   		<div class="d-flex justify-content-between mb-3">
   		<!-- this div for register contain spring MVC form (adding modelAttribute object new user) to fill in the empty user object -->
   			<div class="col-6 ">
   				<h2 class="mb-3">Register</h2>
   				<form:form action="/register" method="POST" modelAttribute="user">
   				
   					<!-- displaying custom errors -->
   					<p class="text-danger"><form:errors path="firstName"></form:errors></p>
   					<div class="d-flex justify-content-around mb-3 mt-3">
   						<form:label path="firstName" class="col-4">First Name : </form:label>
   						<form:input path="firstName" class="form-control" placeholder="Enter your first name"/>
   					</div>
   					 
   					 <!-- displaying custom errors -->
   					<p><form:errors class="text-danger" path="lastName"></form:errors></p>
   					<div class="d-flex justify-content-around mb-3">
   						<form:label path="lastName" class="col-4">Last Name : </form:label>
   						<form:input path="lastName" class="form-control" placeholder="Enter your last name"/>
   					</div>
   					
   					<!-- displaying custom errors -->
   					<p><form:errors path="email" class="text-danger"></form:errors></p>
   					<div class="d-flex justify-content-around mb-3">
   						<form:label path="email" class="col-4">Email : </form:label>
   						<form:input path="email" class="form-control" type="email" placeholder="Enter your email adresse"/>
   					</div>
   					
   					<!-- displaying custom errors -->
   					<p><form:errors path="password" class="text-danger"></form:errors></p>
   					<div class="d-flex justify-content-around mb-3">
   						<form:label path="password" class="col-4">Password : </form:label>
   						<form:input path="password" class="form-control" type="password" placeholder="Enter your password"/>
   					</div>
   					
   					<!-- displaying custom errors -->
   					<p><form:errors path="confirm" class="text-danger"></form:errors></p>
   					<div class="d-flex justify-content-around mb-3">
   						<form:label path="confirm" class="col-4">Confirm PW : </form:label>
   						<form:input path="confirm" class="form-control" placeholder="Enter your confirm password"/>
   					</div>
   					<div class="d-flex justify-content-center">
   						<button class="btn btn-primary btn-lg shadow">Submit</button>
   					</div>
   				</form:form>
   				</div>
   				<div class="col-4 ">
   					<h2 class="mb-3">Login</h2>
   					<!-- for the login form passing loggedUser empty object through modelAttribute -->
   					<form:form action="/login" method="POST" modelAttribute="loggedUser">
   					
   						<!-- displaying custom errors -->
   						<p class="text-danger"><form:errors path="email"/></p>
   						<div class="d-flex justify-content-around mb-3">
	   						<form:label path="email" class="col-4">Email : </form:label>
	   						<form:input path="email" class="form-control" type="email" placeholder="Enter your email adresse"/>
   						</div>
   						
   						<!-- displaying custom errors -->
   						<p class="text-danger"><form:errors path="password"/></p>
   						<div class="d-flex justify-content-around mb-3">
	   						<form:label path="password"  class="col-4">Password : </form:label>
	   						<form:input path="password" class="form-control" placeholder="Enter your password"/>
   						</div>
						<div class="d-flex justify-content-center">
   							<button class="btn btn-primary btn-lg shadow">Submit</button>
   						</div>
   					</form:form>
   				</div>
   		</div>
   </div>
</body>
</html>