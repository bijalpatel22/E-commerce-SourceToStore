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
<title>Find products by product category</title>
</head>
<body>
<form class="create" action="findproducts" method="post">
		<h1 class="heading">Search for Products from Product category</h1>
	
		<p>
			<label class="labels" for="categorytype">Enter a Category Type :</label>
			<input id="categorytype" name="categorytype" value="${fn:escapeXml(param.firstname)}">
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
                <th>Product Name</th>
                <th>Quantity Left</th>
                <th>Price per item</th>
                <th>Add Item To Cart</th>
                
            </tr>
            <c:forEach items="${products}" var="product" >
                <tr>
                    <td><c:out value="${product.getProductId()}" /></td>
                    <td><c:out value="${product.getProductName()}" /></td>
                    <td><c:out value="${product.getQuantity()}" /></td>
                    <td><c:out value="${product.getPrice()}" /></td>
                    <td><a href="AddProduct.jsp">Add Product</a></td>
                    
                </tr>
            </c:forEach>
       </table>
</body>
</html>