<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Books</title>
<style>
	table, th, td {
  		border: 1px solid black;
	}
	
	body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

/* Button used to open the contact form - fixed at the bottom of the page */
.open-button {
  background-color: #555;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  opacity: 0.8;
  position: fixed;
  bottom: 23px;
  right: 28px;
  width: 280px;
}

.open-button2 {
  background-color: #555;
  color: white;
  border: none;
  cursor: pointer;
  opacity: 0.8;
}

/* The popup form - hidden by default */
.form-popup {
  display: none;
  position: fixed;
  bottom: 0;
  right: 15px;
  border: 3px solid #f1f1f1;
  z-index: 9;
}

/* Add styles to the form container */
.form-container {
  max-width: 300px;
  padding: 10px;
  background-color: white;
}

/* Full-width input fields */
.form-container input[type=text], .form-container textarea {
  width: 100%;
  padding: 15px;
  margin: 5px 0 22px 0;
  border: none;
  background: #f1f1f1;
}

/* When the inputs get focus, do something */
.form-container input[type=text]:focus, .form-container textarea:focus {
  background-color: #ddd;
  outline: none;
}

/* Set a style for the add button */
.form-container .btn {
  background-color: #4CAF50;
  color: white;
  padding: 16px 20px;
  border: none;
  cursor: pointer;
  width: 100%;
  margin-bottom:10px;
  opacity: 0.8;
}

/* Add a red background color to the cancel button */
.form-container .cancel {
  background-color: red;
}

/* Add some hover effects to buttons */
.form-container .btn:hover, .open-button:hover {
  opacity: 1;
}
	
</style>
</head>
<body>

	<script>
		function openForm() {
  			document.getElementById("myForm").style.display = "block";
		}

		function closeForm() {
  			document.getElementById("myForm").style.display = "none";
		}
		
		function closeEditForm() {
  			document.getElementById("editBook").style.display = "none";
		}
		
		function openEditForm() {
  			document.getElementById("editBook").style.display = "block";
		}
		function closeDeleteForm() {
  			document.getElementById("deleteBook").style.display = "none";
		}
		
		function openDeleteForm() {
  			document.getElementById("deleteBook").style.display = "block";
		}
	</script>

	<div class="form-popup" id="editBook">
  		<form action="SaveEditBook" class="form-container">
    		<h1>Edit book:</h1>

    		<label for="isbn"><b>ISBN:</b></label>
    		<input id="editisbn" type="text" placeholder="Enter ISBN" name="isbn" required value="${editBook.getIsbn()}" readonly>

    		<label for="title"><b>Title:</b></label>
    		<input id="edittitle" type="text" placeholder="Enter Title" name="title" required value="${editBook.getTitle()}">
    		
    		<label for="description"><b>Description</b></label>
    		<textarea id="editdescription" name="description" rows="4" cols="25" placeholder="Enter Description" required>${editBook.getDescription()}</textarea>

    		<button class="btn">Save</button>
    		<button type="button" class="btn cancel" onclick="closeEditForm()">Cancel</button>
  		</form>
	</div>
	
	<div class="form-popup" id="deleteBook">
  		<form action="DeleteBook" class="form-container">
    		<h1>Are you sure you want to delete "${bookToDelete.getTitle()}"?</h1>

    		<button class="btn">Delete</button>
    		<button type="button" class="btn cancel" onclick="closeDeleteForm()">Cancel</button>
  		</form>
	</div>
	

	<h1 id="greet">${user}, here are all the books</h1>
	
	<c:if test="${bookAdded}">
		<p style="color: green">"${title}" successfully added</p>
		<% session.removeAttribute("bookAdded");
		   session.removeAttribute("title"); %>
	</c:if> 
	
	<c:if test="${bookDeleted}">
		<p style="color: green">"${deletedTitle}" successfully deleted</p>
		<% session.removeAttribute("bookDeleted");
		   session.removeAttribute("deletedTitle"); %>
	</c:if> 
	
	<c:if test="${showEditForm}">
		<% session.removeAttribute("showEditForm"); 
		   session.removeAttribute("editBook"); %>
		<script>openEditForm();</script>
	</c:if> 
	
	<c:if test="${showDeleteForm}">
		<% session.removeAttribute("showDeleteForm"); %>
		<script>openDeleteForm();</script>
	</c:if> 
	
	<input type="checkbox" id="all" class="books" name="all" onclick="showBooks(this)" checked />
	<label for="all">All books</label>
	
	<input type="checkbox" id="rented" class="books" name="rented" onclick="showBooks(this)" />
	<label for="rented">Rented</label>
	
	<input type="checkbox" id="available" class="books" name="available" onclick="showBooks(this)" />
	<label for="available">Available</label>

	<div id="react">
		<table>
		<thead>
			<tr>
				<th>ISBN</th>
				<th>Title</th>
				<th>Description</th>
				<th>Date added</th>
				<th>Available</th>
				<th>Checked out by</th>
				<th>Checkout date</th>
				<th>Due date</th>
				<th>Rent history</th>
				<th>Edit book</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="i" begin="0" end="${allBooks.size()-1}">
			<tr>
				<th>${allBooks.get(i).getIsbn()}</th>
				<th>${allBooks.get(i).getTitle()}</th>
				<th>${allBooks.get(i).getDescription()}</th>
				<th>${allBooks.get(i).getDate()}</th>
				<c:if test="${allBooks.get(i).isRented() == 0}"><th>Yes</th></c:if>
				<c:if test="${allBooks.get(i).isRented() == 1}"><th>No</th></c:if>
				<th>${renterInfo.get(i)[0]}</th>
				<th>${renterInfo.get(i)[1]}</th>
				<th>${renterInfo.get(i)[2]}</th>
				<th><form action="BookHistory" method="post"><button><input type="hidden" name="rentHistory" value="${allBooks.get(i).getIsbn()}"/>View</button></form></th>
				<th>
					<button class="open-button2" onclick="allBooksStart(event)">
						<input type="hidden" name="editBookIsbn" value="${allBooks.get(i).getIsbn()}"/>
						<input type="hidden" name="editBookTitle" value="${allBooks.get(i).getTitle()}"/>
						<input type="hidden" name="editBookDescription" value="${allBooks.get(i).getDescription()}"/>
					Edit</button>
					<form style="display: inline" action="PopupDelete" method="post"><button class="open-button2"><input type="hidden" name="deleteBook" value="${allBooks.get(i).getIsbn()}"/>Delete</button></form>
				</th>
			</tr>
			</c:forEach>
			
		</tbody>
	</table>
	</div>
	
	<br>
	<button onclick="location.href='librarian.jsp'">Go Back</button>
	
	
	<button class="open-button" onclick="openForm()">+ New book</button>
	
	<div class="form-popup" id="myForm">
  		<form action="AddBookServlet" class="form-container" method="post">
    		<h1>Add a new book</h1>

    		<label for="isbn"><b>ISBN:</b></label>
    		<input type="text" placeholder="Enter ISBN" name="isbn" required>

    		<label for="title"><b>Title:</b></label>
    		<input type="text" placeholder="Enter Title" name="title" required>
    		
    		<label for="description"><b>Description</b></label>
    		<textarea name="description" rows="4" cols="25" placeholder="Enter Description" required></textarea>

    		<button type="submit" class="btn">Add</button>
    		<button type="button" class="btn cancel" onclick="closeForm()">Cancel</button>
  		</form>
	</div>
	
	<script type="text/babel">

		function allBooksStart(event) {
			const editButton = event.target;
			const isbn = editButton.children[0].value;
			const title = editButton.children[1].value;
			const description = editButton.children[2].value;
			document.getElementById('editisbn').value = isbn;
			document.getElementById('edittitle').value = title;
			document.getElementById('editdescription').innerHTML = description;
			openEditForm();

		}

				const allBooksTable = (
			<tbody>
			<c:forEach var="i" begin="0" end="${allBooks.size()-1}">
			<tr>
				<th>${allBooks.get(i).getIsbn()}</th>
				<th>${allBooks.get(i).getTitle()}</th>
				<th>${allBooks.get(i).getDescription()}</th>
				<th>${allBooks.get(i).getDate()}</th>
				<c:if test="${allBooks.get(i).isRented() == 0}"><th>Yes</th></c:if>
				<c:if test="${allBooks.get(i).isRented() == 1}"><th>No</th></c:if>
				<th>${renterInfo.get(i)[0]}</th>
				<th>${renterInfo.get(i)[1]}</th>
				<th>${renterInfo.get(i)[2]}</th>
				<th><form action="BookHistory" method="post"><button><input type="hidden" name="rentHistory" value="${allBooks.get(i).getIsbn()}"/>View</button></form></th>
				<th>
					<button className="open-button2" onClick={() => allBooksStart(event)}>
						<input type="hidden" name="editBookIsbn" value="${allBooks.get(i).getIsbn()}"/>
						<input type="hidden" name="editBookTitle" value="${allBooks.get(i).getTitle()}"/>
						<input type="hidden" name="editBookDescription" value="${allBooks.get(i).getDescription()}"/>
					Edit</button>
					<form style={{display: "inline"}} action="PopupDelete" method="post"><button className="open-button2"><input type="hidden" name="deleteBook" value="${allBooks.get(i).getIsbn()}"/>Delete</button></form>
				</th>
			</tr>
			</c:forEach>
			
		</tbody>
		);

		const rentedBooksTable = (
			<tbody>
			<c:forEach var="i" begin="0" end="${allBooks.size()-1}">
			<c:if test="${allBooks.get(i).isRented() == 1}">
			<tr>
				<th>${allBooks.get(i).getIsbn()}</th>
				<th>${allBooks.get(i).getTitle()}</th>
				<th>${allBooks.get(i).getDescription()}</th>
				<th>${allBooks.get(i).getDate()}</th>
				<th>Yes</th>
				<th>${renterInfo.get(i)[0]}</th>
				<th>${renterInfo.get(i)[1]}</th>
				<th>${renterInfo.get(i)[2]}</th>
				<th><form action="BookHistory" method="post"><button><input type="hidden" name="rentHistory" value="${allBooks.get(i).getIsbn()}"/>View</button></form></th>
				<th>
					<button className="open-button2" onClick={() => allBooksStart(event)}>
						<input type="hidden" name="editBookIsbn" value="${allBooks.get(i).getIsbn()}"/>
						<input type="hidden" name="editBookTitle" value="${allBooks.get(i).getTitle()}"/>
						<input type="hidden" name="editBookDescription" value="${allBooks.get(i).getDescription()}"/>
					Edit</button>
					<form style={{display: "inline"}} action="PopupDelete" method="post"><button className="open-button2"><input type="hidden" name="deleteBook" value="${allBooks.get(i).getIsbn()}"/>Delete</button></form>
				</th>
			</tr>
			</c:if>
			</c:forEach>
			
		</tbody>
		);

		const availableBooksTable = (
			<tbody>
			<c:forEach var="i" begin="0" end="${allBooks.size()-1}">
			<c:if test="${allBooks.get(i).isRented() == 0}">
			<tr>
				<th>${allBooks.get(i).getIsbn()}</th>
				<th>${allBooks.get(i).getTitle()}</th>
				<th>${allBooks.get(i).getDescription()}</th>
				<th>${allBooks.get(i).getDate()}</th>
				<th>No</th>
				<th></th>
				<th></th>
				<th></th>
				<th><form action="BookHistory" method="post"><button><input type="hidden" name="rentHistory" value="${allBooks.get(i).getIsbn()}"/>View</button></form></th>
				<th>
					<button className="open-button2" onClick={() => allBooksStart(event)}>
						<input type="hidden" name="editBookIsbn" value="${allBooks.get(i).getIsbn()}"/>
						<input type="hidden" name="editBookTitle" value="${allBooks.get(i).getTitle()}"/>
						<input type="hidden" name="editBookDescription" value="${allBooks.get(i).getDescription()}"/>
					Edit</button>
					<form style={{display: "inline"}} action="PopupDelete" method="post"><button className="open-button2"><input type="hidden" name="deleteBook" value="${allBooks.get(i).getIsbn()}"/>Delete</button></form>
				</th>
			</tr>
			</c:if>
			</c:forEach>
			
		</tbody>
		);		

		function showBooks(checkbox) {
			checkbox.checked = true;
			const boxes = document.getElementsByClassName("books");
			for(const box of boxes) {
				if(box !== checkbox) {
					box.checked = false;
				}
			}
			
			if(checkbox.name === "all") {
				ReactDOM.render(<DisplayBooks table = {allBooksTable} />, document.getElementById('react'));
			} else if(checkbox.name === "rented") {
				ReactDOM.render(<DisplayBooks table = {rentedBooksTable} />, document.getElementById('react'));
			} else if(checkbox.name === "available"){
				ReactDOM.render(<DisplayBooks table = {availableBooksTable} />, document.getElementById('react'));
			}
		}

		class DisplayBooks extends React.Component {
			render() {
				return (
			<table>
		<thead>
			<tr>
				<th>ISBN</th>
				<th>Title</th>
				<th>Description</th>
				<th>Date added</th>
				<th>Available</th>
				<th>Checked out by</th>
				<th>Checkout date</th>
				<th>Due date</th>
				<th>Rent history</th>
				<th>Edit book</th>
			</tr>
		</thead>
		{this.props.table}
	</table>
		);
			}
		}

	</script>
	
	
	<!-- Load React. -->
  <!-- Note: when deploying, replace "development.js" with "production.min.js". -->
  <script src="https://unpkg.com/react@16/umd/react.development.js" crossorigin></script>
  <script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js" crossorigin></script>

  
  <script src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>
	

</body>
</html>