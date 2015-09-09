	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<nav class="navbar navbar-inverse">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand">ToDoApp</a>
	    </div>
	    <div>
	      <ul class="nav navbar-nav">
			<c:if test="${empty user}">
  					<li><a href="./LoginForm.jsp">Login</a></li>
			</c:if>
			
			<c:if test="${not empty user}">
           	 		<li><a href="ToDoList">To Do List</a></li>
   					<li><a href="History">History</a></li>
					<li><a href="Logout">Logout</a></li>
			</c:if>
	      </ul>
	    </div>
	  </div>
	</nav>