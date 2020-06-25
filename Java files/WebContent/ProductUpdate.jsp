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
<title>Update product</title>
</head>
<body>
	<h1 class="heading">Update the price of a product</h1>
	<form class="create" action="productupdate" method="post">
		<p>
			<label class="labels" for="productid">Product Id</label>
			<input id="productid" name="productid" value="">
		</p>
		<p>
			<label class="labels" for="price">New Price</label>
			<input id="price" name="price" value="">
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