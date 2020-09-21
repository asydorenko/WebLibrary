<%@page import="com.cognixia.jump.library.model.Book"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Available books</title>
<style>
	table, th, td {
  		border: 1px solid black;
	}
</style>
</head>
<body>

	<%String username = (String)session.getAttribute("user"); %>
	<h1><%=username %>, here are all available books</h1>

	<% ArrayList<Book> availableBooks = (ArrayList<Book>) request.getAttribute("availableBooks"); %>
	<% ArrayList<String> rentedBooks = (ArrayList<String>) request.getAttribute("rentedBooks"); %>
	
	<% if(rentedBooks.size() != 0) { %>
		<p style="color: green">You have successfully rented: </p>
		<ul>
		<% for(int i = 0; i < rentedBooks.size(); ++i) { %>
			<li style="color: green"> <%=rentedBooks.get(i) %></li>
	<% }} %>
		</ul>
	
	<form action="CheckoutBooks" method="post">
		<table style="width: 75%; align: center">
			<tr>
				<th>ISBN</th>
				<th>Title</th>
				<th>Description</th>
				<th>Date added</th>
				<th>Checkout</th>
			</tr>
			
			<% for(int i = 0; i < availableBooks.size(); ++i) { %>
			<tr>
				<th><%= availableBooks.get(i).getIsbn() %></th>
				<th><%= availableBooks.get(i).getTitle() %></th>
				<th><%= availableBooks.get(i).getDescription() %></th>
				<th><%= availableBooks.get(i).getDate() %></th>
				<th><input type="checkbox" class="check" name="book" value="<%=availableBooks.get(i).getIsbn() %>" onclick="isChecked(this)"></th>
			</tr>
			<%} %>
		</table>
		<input type="submit" id="submit_btn" value="Rent selected books" disabled>
	</form>
	<br>
	<button onclick="location.href='patron.jsp'">Go Back</button>
	
	<script>
	function isChecked(termsCheckBox){
		
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