<%@page import="com.cognixia.jump.library.model.Book"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Rented books</title>
<style>
	table, th, td {
  		border: 1px solid black;
	}
</style>
</head>
<body>
	<h1>${user}, here are your all rented books</h1>
	
	<c:if test="${returnedBooks.size() != 0}">
		<p style="color: green">You have successfully returned: </p>
		<ul>
			<c:forEach var="book" items="${returnedBooks}">
				<li style="color: green"> <c:out value="${book}"/></li>
			</c:forEach>
		</ul>
	</c:if>
	
	<form action="ReturnBooks" method="post">
		<table style="width: 75%; align: center">
			<tr>
				<th>ISBN</th>
				<th>Title</th>
				<th>Description</th>
				<th>Date added</th>
				<th>Date rented</th>
				<th>Due date</th>
				<th>Return book</th>
			</tr>
			
			<c:forEach var="book" items="${rentedBooks}">
				<tr>
					<th><c:out value="${book.getIsbn()}" /></th>
					<th><c:out value="${book.getTitle()}" /></th>
					<th><c:out value="${book.getDescription()}" /></th>
					<th><c:out value="${book.getDate()}" /></th>
					<th><c:out value="${bdao.getCheckoutDate(book.getIsbn())}" /></th>
					<th><c:out value="${bdao.getDueDate(book.getIsbn())}" /></th>
					<th><input type="checkbox" class="check" name="book" value=<c:out value="${book.getIsbn()}" /> onclick="isChecked(this)"></th>
				</tr>
			</c:forEach>
					
		</table>
		<input type="submit" id="submit_btn" value="Return selected books" disabled>
	</form>
	<br>
	<button onclick="location.href='patron.jsp'">Go Back</button>
	
	<script>
	function isChecked(returnCheckBox){
		
		const values = document.getElementsByClassName("check");
		for(const box of values) {
			if(box.checked) {
				document.getElementById("submit_btn").disabled = false;
				return;
			}
		}
		document.getElementById("submit_btn").disabled = true;
	}
	</script>

</body>
</html>