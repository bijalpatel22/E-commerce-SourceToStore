<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<meta charset="ISO-8859-1">
<title>Create a new account</title>
</head>
<body>
	<h1 class="heading">Create Account</h1>
	<form class="create" action="accountcreate" method="post">
		<p>
			<label class="labels" for="firstname">First Name</label>
			<input id="firstname" name="firstname" value="">
		</p>
		<p>
			<label class="labels" for="lastname">Last Name</label>
			<input id="lastname" name="lastname" value="">
		</p>
		<p>
			<label class="labels" for="address">Address</label>
			<input id="address" name="address" value="">
		</p>
		<p>
			<label class="labels" for="phone">Phone number</label>
			<input id="phone" name="phone" value="">
		</p>
		<p>
			<label class="labels" for="email">E-mail</label>
			<input id="email" name="email" value="">
		</p>
		<p>
			<label class="labels" for="carddetails">Card details</label>
			<input id="carddetails" name="carddetails" value="">
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
			<b>${messages.success}</b><br></br>
			<!-- <a href="setpassworduser">Set your password here if you are an user.</a><br></br> -->
			<!-- <a href="setpasswordretailer">Set your password here if you are a retailer.</a><br></br> -->
		</span>
	</p>
</body>
</html>