<%@page import="com.cognixia.jump.library.model.Patron"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit your account details</title>
</head>
<body>

	<h1>${user}, edit your profile details</h1>
	<p>First name: ${patron.getFirstName()}</p>
	<p>Last name: ${patron.getLastName()}</p>
	<p>Username: ${patron.getUsername()}</p>
	<p>Password: ${patron.getPassword()}</p>
	
	<form action="ApplyChangesPatron" method="post">
		<fieldset>
			<legend>Edit your account details here:</legend>
			<label for="firstname">First name:</label><br>
			<input type="text" id="firstname" name="firstname" value="${patron.getFirstName()}" required><br>
			
			<label for="lastname">Last name:</label><br>
			<input type="text" id="lastname" name="lastname" value="${patron.getLastName()}" required><br>
			
			<label for="username">Username:</label><br>
			<input type="text" id="username" name="username" value="${patron.getUsername()}" required><br>
			
			<label for="password">Password:</label><br>
			<input type="password" id="password" name="password" value="${patron.getPassword()}" required><br>
			
			<input type="submit" value="Submit changes">
			
		</fieldset>
	</form>
	<br>
	<button onclick="location.href='patron.jsp'">Go Back</button>

</body>
</html>