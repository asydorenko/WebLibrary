<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Successfully rented</title>
</head>
<body>

	<% String username = (String)session.getAttribute("user"); %>
	<% String[] titles = (String[])request.getAttribute("rentedBooks"); %>

	<h1><%=username %>, you successfully rented:</h1>
	<ul>
		<% for(int i = 0; i < titles.length; ++i) {%>
		<li><%=titles[i] %></li>
		<%} %>
	</ul>
	<br>
	<button onclick="location.href='patron.jsp'">Back to Main</button>
	
</body>
</html>