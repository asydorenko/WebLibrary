<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error</title>
</head>
<body>
	
	<h1 id="message"></h1>
	<button id="btn" onclick="location.href='index.jsp'">Back to Log In</button>
	
	<script>

		switch(${errorMessage}) {
		case 1:
			document.title = "Invalid login"
			document.getElementById("message").innerHTML = "The username and/or password you have entered is incorrect";
			break;
		case 2:
			document.title = "Account frozen"
			document.getElementById("message").innerHTML = "The account is currently frozen. Please try again later...";
			break;
		case 3:
			document.title = "Signup successful"
			document.getElementById("message").innerHTML = "You have successfully signed up and can log in now";
			break;
		case 4:
			document.title = "Error"
			document.getElementById("message").innerHTML = "Oops, there was an error, try again...";
			document.getElementById('btn').innerHTML = "Go Back"
			document.getElementById('btn').setAttribute("onclick", "location.href='signup.html'")
			break;
		case 5:
			document.title = "Username taken"
			document.getElementById("message").innerHTML = "User with such username already exists";
			document.getElementById('btn').innerHTML = "Go Back"
			document.getElementById('btn').setAttribute("onclick", "history.back()")
			break;
		case 6:
			document.title = "Account updated"
			document.getElementById("message").innerHTML = "You have successfully update your profile info";
			document.getElementById('btn').innerHTML = "Go Back"
			document.getElementById('btn').setAttribute("onclick", "location.href='patron.jsp'")
			break;
		}
	
	</script>

</body>
</html>