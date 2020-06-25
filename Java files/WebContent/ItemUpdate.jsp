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
<title>Update an item in the order</title>
</head>
<body>
<h1 class="heading">Update Product</h1>
	<form class="create" action="itemupdate" method="post">
		<p>
			<label for="ordernumber">Order Number</label>
			<input id="ordernumber" name="ordernumber" value="${fn:escapeXml(param.username)}">
		</p>
		<p>
			<label for="productreplace">Product Id to be replaced</label>
			<input id="productreplace" name="productreplace" value="${fn:escapeXml(param.username)}">
		</p>
		<p>
			<label for="product">New Product Id</label>
			<input id="product" name="product" value="">
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