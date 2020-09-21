<%@page import="com.cognixia.jump.library.model.Patron"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
	table, th, td {
  		border: 1px solid black;
	}
</style>
</head>
<body>

	<%String username = (String)session.getAttribute("user"); %>
	<h1><%=username %>, here's all Patrons with active accounts</h1>

	<% ArrayList<Patron> activePatrons = (ArrayList<Patron>) request.getAttribute("activePatrons"); %>
	
	<form action="FreezePatrons" method="post">
		<table style="width: 75%; align: center">
			<tr>
				<th>ID</th>
				<th>First name</th>
				<th>Last name</th>
				<th>Username</th>
				<th>Password</th>
				<th>Account Frozen</th>
				<th>Freeze</th>
			</tr>
			
			<% for(int i = 0; i < activePatrons.size(); ++i) { %>
			<tr>
				<th><%=activePatrons.get(i).getId()%></th>
				<th><%=activePatrons.get(i).getFirstName()%></th>
				<th><%=activePatrons.get(i).getLastName()%></th>
				<th><%=activePatrons.get(i).getUsername()%></th>
				<th><%=activePatrons.get(i).getPassword()%></th>
				<th><%=activePatrons.get(i).isFrozen()%>
				<th><input type="checkbox" class="check" name="book" value="<%=activePatrons.get(i).getUsername() %>" onclick="isChecked(this)"></th>
			</tr>
			<%} %>
		</table>
		<input type="submit" id="submit_btn" value="Freeze selected accounts" disabled>
	</form>
	<br>
	<button onclick="location.href='librarian.jsp'">Go Back</button>
	
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