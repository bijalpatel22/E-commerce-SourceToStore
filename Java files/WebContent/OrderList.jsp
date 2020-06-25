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
<title>Find products of an order</title>
</head>
<body>
<h1 class="heading">Search Products in a particular order</h1>
		<form class="create" action="orderlist" method="post">
		<p>
			<label class="labels" for="orderid">Enter an order number :</label>
			<input id="orderid" name="orderid" value="${fn:escapeXml(param.firstname)}">
		</p>
		<p class="submitbutton">
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<br/>
	<h1>Products</h1>
        <table border="1">
            <tr>
                <th>Product Id</th>
                <th>Delete Product</th>
                <th>Update Product</th>
            </tr>
            <c:forEach items="${products}" var="product" >
                <tr>
                    <td><c:out value="${product.getProductId()}" /></td>
                    <td><a href="itemdelete?ordernumber=<c:out value="${product.getOrderNumber()}"/>
                    ?productid=<c:out value="${product.getProductId()}"/>">Delete</a></td>
                    <td><a href="itemupdate">Update</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>