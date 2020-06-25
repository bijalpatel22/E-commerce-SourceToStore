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
<title>Delete an item from order</title>
</head>
<body>
<h1 class="heading">Item Delete</h1>
	<form class="create" action="itemdelete" method="post">
		<p>
			<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<label class="labels" for="ordernumber">Order Number</label>
				<input id="ordernumber" name="ordernumber" value="${fn:escapeXml(param.username)}">
			</div>
		</p>
		<p>
			<div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
				<label class="labels" for="productid">Product Id</label>
				<input id="productid" name="productid" value="${fn:escapeXml(param.username)}">
			</div>
		</p>
		<p class="submitbutton">
			<span id="submitButton" <c:if test="${messages.disableSubmit}">style="display:none"</c:if>>
			<input type="submit">
			</span>
		</p>
	</form>
	<br/><br/>

</body>
</html>