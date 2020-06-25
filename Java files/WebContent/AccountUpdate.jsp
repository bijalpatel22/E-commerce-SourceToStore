<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<meta charset="ISO-8859-1">
<title>Update an account</title>
</head>
<body>
	<h1 class="heading">Update Account</h1>
	<form class="create" action="accountupdate" method="post">
		<p>
			<label class="labels" for="accountnumber">Account Number</label>
			<input id="accountnumber" name="accountnumber" value="${fn:escapeXml(param.username)}">
		</p>
		<p>
			<label class="labels" for="carddetails">New Card Details</label>
			<input id="carddetails" name="carddetails" value="">
		</p>
		<p class="submitbutton">
			<input type="submit">
		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>