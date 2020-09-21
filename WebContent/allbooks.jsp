<%@page import="com.cognixia.jump.library.model.Book"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All books</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<style>
	table, th, td {
  		border: 1px solid black;
	}
</style>
</head>
<body onload="availableBooks()">

	<h1 id="greet">${user}, here are all the books</h1>
	
	<c:if test="${rentedBooks != null && rentedBooks.size() != 0 }">
		<p style="color: green">You have successfully rented: </p>
		<ul>
			<c:forEach var="book" items="${rentedBooks}">
				<li style="color: green"> ${book}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<label for="onlyAvailableBooks">Show only available books</label>
	<input type="checkbox" id="availableBooks" name="onlyAvailableBooks" onClick="availableBooks()">
	
	<div id="react">
	</div>
	<br>
	<button onclick="location.href='patron.jsp'">Go Back</button>
	
	
	<script type="text/javascript">
		function isChecked(){
			
			const values = document.getElementsByClassName("check");
			for(const box of values) {
				if(box.checked) {
					document.getElementById("submit_btn").disabled = false;
					return;
				}
			}
			document.getElementById("submit_btn").disabled = true;
		};
		
	</script>
	
	<script type="text/babel">

		function availableBooks() {
			
			const cont = document.getElementById('react');
			const avail = document.getElementById('availableBooks');

			if(avail.checked) {
				var booksTable = (
					<tbody>
						<c:forEach var="book" items="${availableBooks}">
							<tr>
								<th>${book.getIsbn()}</th>
								<th>${book.getTitle()}</th>
								<th>${book.getDescription()}</th>
								<th>${book.getDate()}</th>
								<c:choose>
									<c:when test="${book.isRented() == 1}">
										<th>No</th>
										<th></th>
									</c:when>
									<c:otherwise>
										<th>Yes</th>
										<th><input type="checkbox" className="check" name="book" value="${book.getIsbn()}" onClick={isChecked} /></th>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
					</tbody>
				);
			} else {
				var booksTable = (
					<tbody>
						<c:forEach var="book" items="${allBooks}">
							<tr>
								<th>${book.getIsbn()}</th>
								<th>${book.getTitle()}</th>
								<th>${book.getDescription()}</th>
								<th>${book.getDate()}</th>
								<c:choose>
									<c:when test="${book.isRented() == 1}">
										<th>No</th>
										<th></th>
									</c:when>
									<c:otherwise>
										<th>Yes</th>
										<th><input type="checkbox" className="check" name="book" value="${book.getIsbn()}" onClick={isChecked} /></th>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
					</tbody>

				);
			}

			const myTable = (
				<form action="CheckoutBooks" method="post" id="myForm">
					<table style={{width: "75%", align: "center"}}>
						<thead>
						<tr>
							<th>ISBN</th>
							<th>Title</th>
							<th>Description</th>
							<th>Date added</th>
							<th>Available</th>
							<th>Checkout</th>
						</tr>
						</thead>
						{booksTable}
					</table>
					<input type="submit" id="submit_btn" value="Rent selected books" disabled />
				</form>
			);
			
			ReactDOM.render(myTable, cont);
	}
	</script>
		
	
 <!-- Load React. -->
  <!-- Note: when deploying, replace "development.js" with "production.min.js". -->
  <script src="https://unpkg.com/react@16/umd/react.development.js" crossorigin></script>
  <script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js" crossorigin></script>

  
  <script src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>
  
  
  
  	<!-- JS, Popper.js, and jQuery -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
</body>
</html>