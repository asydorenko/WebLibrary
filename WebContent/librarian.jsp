<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Librarian Main Page</title>
</head>
<body>

	<h1>Hello ${user}, welcome to the Library</h1>
	<h3>What would you like to do?</h3>

	
	<a href="AllPatrons">Manage Patrons</a><br>
	<a href="LibrarianBooks">Manage books</a><br>
	<a href="EditLibrarian">Edit your account</a><br>
	<br>
	<a href="LogOut"><button>Log Out</button></a>

</body>
</html>