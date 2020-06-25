<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<meta charset="ISO-8859-1">
<title>Add Product</title>
</head>
<body>
<h1 class="heading">Add Product</h1>
	<form class="create" action="addproduct" method="post">
		<p>
			<label class="labels" for="ordernumber">Order Number</label>
			<input id="ordernumber" name="ordernumber" value="">
		</p>
		<p>
			<label class="labels" for="productid">Product Id</label>
			<input id="productid" name="productid" value="">
		</p>
		<p class="submitbutton">
			<input type="submit">
		</p>
		</form>
	<br/><br/>
	<p>
		<span id="successMessage">
			<b>${messages.success}</b><br></br>

</body>
</html>