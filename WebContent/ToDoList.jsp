<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/jquery.validate.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/additional-methods.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.14.0/additional-methods.min.js"></script>
<style>
	.item
	{
		padding:10px;
		margin-top:20px;
		border: 1px solid black;
		
	}

</style>
<title>To Do List</title>

</head>
<body>
	<jsp:include page="./header.jsp"/>
	
	
	<div class="col-sm-6 col-sm-offset-3">

	  <a class ="btn btn-primary" href="AddItem.jsp">Add an Item</a>
	</div>

	
	<c:forEach var="item" items="${items}">
		<div class="item col-sm-6 col-sm-offset-3">
					<h2>${item.description}</h2>
					<p>Due Date: ${item.dueDate}</p>
					<p>Priority: ${item.itemPriority}</p>
					<p>Status: ${item.statusCode}  </p>
					<a href="CompleteItem?itemId=${item.itemId}" class="btn btn-success">Complete</a>
					<a href="EditItem?itemId=${item.itemId}" class="btn btn-primary">Edit</a>
		</div>
	</c:forEach>
	
</body>
</html>