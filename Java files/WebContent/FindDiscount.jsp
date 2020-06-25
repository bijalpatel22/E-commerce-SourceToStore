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
<title>Find discount</title>
</head>
<body>
<form class="create" action="finddiscount" method="post">
		<h1 class="heading">Check the validity period of the discount for an account number</h1>
		<p>
			<label class="labels" for="accno">Enter a Account Number :</label>
			<input id="accno" name="accno" value="${fn:escapeXml(param.firstname)}">
		</p>
		<p class="submitbutton">
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<br/>
	<h1>Discounts</h1>
        <table border="1">
            <tr>
            	<th>Discount Id</th>
            	<th>Discount Amount</th>
            	<th>Validity</th>
            	<th>Description</th>
            	<th>Account Created</th>
            </tr>
                <tr>
                    <td><c:out value="${discounts.getDiscountId()}" /></td>
                    <td><c:out value="${discounts.getDisountAmount()}" /></td>
                    <td><c:out value="${discounts.getValidity()}" /></td>
                    <td><c:out value="${discounts.getDescription()}" /></td>
                    <td><c:out value="${discounts.getDate()}"/></td>
                </tr>
       </table>
</body>
</html>