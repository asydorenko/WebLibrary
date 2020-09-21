<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Book</title>
<style>
	.div {
		display: inline-block;
	}
</style>
</head>
<body>

	<h3>${user}, here you can edit "${book.getTitle()}"</h3>
	
	<c:if test="${updated}">
		<p style="color: green">Changes saved</p>
	</c:if>
	
	<form action="SaveEditBook" method="post">
		<fieldset>
			<legend>Edit book details</legend>
			<div class="div">
				<label>ISBN: </label><br>
				<input type="text" name="isbn" value="${book.getIsbn()}" readonly> 
				<br><br>
			</div>
			<div class="div">
				<label>Title: </label><br>
				<input type="text" name="title" value="${book.getTitle()}" required> 
				<br><br>
			</div>
			<br>
			<label>Description: </label><br>
			<textarea name="description" rows="4" cols="50">${book.getDescription()}</textarea>
			<br>
			<input type="submit" value="Save">
		</fieldset>
	</form>
	<br>
	<a href="LibrarianBooks"><button>Go Back</button></a>
	
</body>
</html>