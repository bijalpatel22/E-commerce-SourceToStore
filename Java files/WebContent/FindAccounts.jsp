<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Find an account</title>
</head>
<body>
	<form action="findaccounts" method="post">
		<h1 class="heading">Search for a Account by AccountNumber</h1>
		<p>
			<label for="AccountNumber">AccountNumber</label>
			<input id="AccountNumber" name="AccountNumber" value="${fn:escapeXml(param.AccountNumber)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<h1>Account Information</h1>
        <table border="1">
            <tr>
                <th>AccountNumber</th>
                <th>BillingAddress</th>
                <th>CardDetails</th>
                <th>AccountCreated</th>
            </tr>
                <tr>
                    <td><c:out value="${account.getAccountNumber()}" /></td>
                    <td><c:out value="${account.getBillingAddress()}" /></td>
                    <td><c:out value="${account.getCardDetails()}" /></td>
                    <td><c:out value="${account.getAccountCreated()}" /></td>
                </tr>
       </table>
</body>
</html>