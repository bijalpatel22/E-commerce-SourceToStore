<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Set password</title>
</head>
<body>
<h1 class="heading">Create Password</h1>
	<form class="create" action="setpassworduser" method="post">
		<p>
			<label class="labels" for="personid">Person Id</label>
			<input id="personid" name="personid" value="">
		</p>
		<p>
			<label class="labels" for="accountno">Account Number</label>
			<input id="accountno" name="accountno" value="">
		</p>
		<p>
			<label class="labels" for="password">Password</label>
			<input type="password" id="password" name="password" value="">
		</p>
		<p class="submitbutton">
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage">
			<b>${messages.success}</b>
		</span>
	</p>
</body>
</html>