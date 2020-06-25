<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css"/>
<meta charset="ISO-8859-1">
<title>Create a product</title>
</head>
<body>
	<h1 class="heading">Create Product</h1>
	<form class="create" action="productcreate" method="post">
		<p>
			<label class="labels" for="productid">Product Id</label>
			<input id="productid" name="productid" value="">
		</p>
		<p>
			<label class="labels" for="productname">Product Name</label>
			<input id="productname" name="productname" value="">
		</p>
		<p>
			<label class="labels" for="quantity">Quantity</label>
			<input id="quantity" name="quantity" value="">
		</p>
		<p>
			<label class="labels" for="price">Price of the product</label>
			<input id="price" name="price" value="">
		</p>
		<p>
			<label class="labels" for="type">Category in which the product belongs</label>
			<input id="type" name="type" value="">
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