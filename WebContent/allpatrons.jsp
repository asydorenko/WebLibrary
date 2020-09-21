<%@page import="com.cognixia.jump.library.model.Patron"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Patrons</title>
<style>
	table, th, td {
  		border: 1px solid black;
	}
</style>
</head>
<body onload="allPatrons()">
	<h1>${user}, here's the list of all Patrons</h1>
	
	<c:if test="${modifiedPatrons != null}">
		<p style="color: green">You have successfully modified: </p>
		<ul>
			<c:forEach var="patron" items="${modifiedPatrons}">
				<li style="color: green"> ${patron}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	
	<input type="checkbox" id="all" class="freeze" name="all" onclick="freezer(this)" checked />
	<label for="all">All patrons</label>
	
	<input type="checkbox" id="frozen" class="freeze" name="frozen" onclick="freezer(this)" />
	<label for="frozen">Frozen</label>
	
	<input type="checkbox" id="active" class="freeze" name="active" onclick="freezer(this)" />
	<label for="active">Active</label>
	
	<div id="react"></div>
	
	<br>
	<button onclick="location.href='librarian.jsp'">Go Back</button>
	
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

		var frozen = (
			<tbody>
			<c:forEach var="patron" items="${allPatrons}">
			<c:if test="${patron.isFrozen() == 1}">
				<tr>
					<th>${patron.getId()}</th>
					<th>${patron.getFirstName()}</th>
					<th>${patron.getLastName()}</th>
					<th>${patron.getUsername()}</th>
					<th>${patron.getPassword()}</th>
					<th>Frozen</th>
					<th><input type="checkbox" className="check" name="patron" value="${patron.getUsername()}" onClick={isChecked} /></th>
				</tr>
			</c:if>
			</c:forEach>
			</tbody>
		);

		var active = (
		<tbody>
					<c:forEach var="patron" items="${allPatrons}">
			<c:if test="${patron.isFrozen() == 0}">
				<tr>
					<th>${patron.getId()}</th>
					<th>${patron.getFirstName()}</th>
					<th>${patron.getLastName()}</th>
					<th>${patron.getUsername()}</th>
					<th>${patron.getPassword()}</th>
					<th>Active</th>
					<th><input type="checkbox" className="check" name="patron" value="${patron.getUsername()}" onClick={isChecked} /></th>
				</tr>
			</c:if>
				</c:forEach>
					</tbody>
		);

		var allPatronsTable = (
			<tbody>
			<c:forEach var="patron" items="${allPatrons}">
			<tr>
				<th>${patron.getId()}</th>
				<th>${patron.getFirstName()}</th>
				<th>${patron.getLastName()}</th>
				<th>${patron.getUsername()}</th>
				<th>${patron.getPassword()}</th>
				<c:choose>
					<c:when test="${patron.isFrozen() == 1}">
						<th>Frozen</th>
					</c:when>
					<c:otherwise>
						<th>Active</th>
					</c:otherwise>
				</c:choose>
				<th><input type="checkbox" className="check" name="patron" value="${patron.getUsername()}" onClick={isChecked} /></th>
			</tr>
			</c:forEach>
			</tbody>
		);

		function freezer(checkbox) {
			checkbox.checked = true;
			const boxes = document.getElementsByClassName("freeze");
			for(const box of boxes) {
				if(box !== checkbox) {
					box.checked = false;
				}
			}
			
			if(checkbox.name === "all") {
				ReactDOM.render(<DisplayPatrons table = {allPatronsTable} />, document.getElementById('react'));
			} else if(checkbox.name === "frozen") {
				ReactDOM.render(<DisplayPatrons table = {frozen} />, document.getElementById('react'));
			} else if(checkbox.name === "active"){
				ReactDOM.render(<DisplayPatrons table = {active} />, document.getElementById('react'));
			}
		}

		class DisplayPatrons extends React.Component {
			render() {
				return (
			<form action="FreezePatrons" method="post">
				<table style={{width: "75%", align: "center"}}>
				<thead>
					<tr>
						<th>ID</th>
						<th>First name</th>
						<th>Last name</th>
						<th>Username</th>
						<th>Password</th>
						<th>Account Status</th>
						<th>Change Status</th>
					</tr>
				</thead>
					{this.props.table}
				
				</table>
				<input type="submit" id="submit_btn" value="Apply changes" disabled />
			</form>
		);
			}
		}

		function allPatrons() {
			ReactDOM.render(<DisplayPatrons table = {allPatronsTable} />, document.getElementById('react'));
		};

		
	</script>
	
	
	<!-- Load React. -->
  <!-- Note: when deploying, replace "development.js" with "production.min.js". -->
  <script src="https://unpkg.com/react@16/umd/react.development.js" crossorigin></script>
  <script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js" crossorigin></script>

  
  <script src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>
	
</body>
</html>