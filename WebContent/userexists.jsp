<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error</title>
</head>
<body>
	<h1>User with such username already exists</h1>
	<%String username = (String)session.getAttribute("user"); %>
	<% if(username == null) { %>
		<button onclick="location.href='index.html'">Back to Log In</button>
	<% } else { %>
		<button onclick="history.back()">Go Back</button>
	<%} %>

</body>
</html>