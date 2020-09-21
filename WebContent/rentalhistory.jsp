<%@page import="com.cognixia.jump.library.model.BookCheckout"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Rental History</title>
<style>
	table, th, td {
  		border: 1px solid black;
	}
</style>
</head>
<body>

	<h1>${user}, here is your rental history</h1>
	<table style="width: 75%; align: center">
		<tr>
			<th>Rental ID</th>
			<th>ISBN</th>
			<th>Title</th>
			<th>Checkout Date</th>
			<th>Due Date</th>
			<th>Return Date</th>
		</tr>
		<c:if test="${history.size() > 0}">
			<c:forEach var="i" begin="0" end="${history.size()-1}">
				<tr>
					<th>${history.get(i).getCheckoutId()}</th>
					<th>${history.get(i).getIsbn()}</th>
					<th>${historyTitles.get(i)}</th>
					<th>${history.get(i).getCheckoutDate()}</th>
					<th>${history.get(i).getDueDate()}</th>
					<th>${history.get(i).getReturnedDate()}</th>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	<br>
	<button onclick="location.href='patron.jsp'">Go Back</button>

</body>
</html>