<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book rental history</title>
<style>
	table, th, td {
  		border: 1px solid black;
	}
</style>
</head>
<body>

	<h1 id="greet">${user}, here is the rental history for "${bookTitle}" (ISBN: ${bookIsbn})</h1>
	
	<table>
		<thead>
			<tr>
				<th>Username</th>
				<th>Checkout date</th>
				<th>Due date</th>
				<th>Returned date</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="book" items="${bookHistory}">
			<tr>
				<th>${book[0]}</th>
				<th>${book[1]}</th>
				<th>${book[2]}</th>
				<th>${book[3]}</th>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<a href="LibrarianBooks"><button>Go Back</button></a>

</body>
</html>