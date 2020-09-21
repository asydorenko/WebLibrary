<%@page import="com.cognixia.jump.library.model.Patron"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Frozen Patrons</title>
<style>
	table, th, td {
  		border: 1px solid black;
	}
</style>
</head>
<body>

	<%String username = (String)session.getAttribute("user"); %>
	<h1><%=username %>, here's all Patrons with frozen accounts</h1>

	<% ArrayList<Patron> frozenPatrons = (ArrayList<Patron>) request.getAttribute("frozenPatrons"); %>
	
	<form action="UnfreezePatrons" method="post">
		<table style="width: 75%; align: center">
			<tr>
				<th>ID</th>
				<th>First name</th>
				<th>Last name</th>
				<th>Username</th>
				<th>Password</th>
				<th>Account Frozen</th>
				<th>Unfreeze</th>
			</tr>
			
			<% for(int i = 0; i < frozenPatrons.size(); ++i) { %>
			<tr>
				<th><%=frozenPatrons.get(i).getId()%></th>
				<th><%=frozenPatrons.get(i).getFirstName()%></th>
				<th><%=frozenPatrons.get(i).getLastName()%></th>
				<th><%=frozenPatrons.get(i).getUsername()%></th>
				<th><%=frozenPatrons.get(i).getPassword()%></th>
				<th><%=frozenPatrons.get(i).isFrozen()%>
				<th><input type="checkbox" class="check" name="book" value="<%=frozenPatrons.get(i).getUsername() %>" onclick="isChecked(this)"></th>
			</tr>
			<%} %>
		</table>
		<input type="submit" id="submit_btn" value="Unfreeze selected accounts" disabled>
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