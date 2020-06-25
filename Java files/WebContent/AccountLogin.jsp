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
<title>Login</title>
</head>
<body>
<h1 class="heading">Login</h1>
	<form class="create" action="accountlogin" method="post">
		<p>
			<%-- <div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>> --%>
				<label class="labels" for="accountnumber">Account Number</label>
				<input id="accountnumber" name="accountnumber" value="${fn:escapeXml(param.username)}">
			<!-- </div> -->
		</p>
		<p>
			<%-- <div <c:if test="${messages.disableSubmit}">style="display:none"</c:if>> --%>
				<label class="labels" for="password">Password</label>
				<input type="password" id="password" name="password" value="${fn:escapeXml(param.username)}">
			<!-- </div> -->
		</p>
		<p class="submitbutton">
			<span id="submitButton">
			<input type="submit">
			</span>
		</p>
	</form>
	<br/><br/>
	<script type="text/javascript">
 
    $(document).ready(function() {
        $("#loginForm").validate({
            rules: {
                accountnumber: {
                    required: true,
                    accountnumber: true
                },
         
                password: "required",
            },
             
            messages: {
                accountnumber: {
                    required: "Please enter Account Number",
                    accountnumber: "Please enter a valid Account Number"
                },
                 
                password: "Please enter password"
            }
        });
 
    });
</script>

</body>
</html>