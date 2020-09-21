<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accounts successfully frozen</title>
</head>
<body>

	<% String username = (String)session.getAttribute("user"); %>
	<% String[] patrons = (String[])request.getAttribute("frozenAccounts"); %>

	<h1><%=username %>, you successfully froze:</h1>
	<ul>
		<% for(int i = 0; i < patrons.length; ++i) {%>
		<li><%=patrons[i] %></li>
		<%} %>
	</ul>
	<br>
	<button onclick="location.href='librarian.jsp'">Back to Main</button>

</body>
</html>