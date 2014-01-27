<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<form action="doRegister.do" class="form-signup" >

	<h2 class="form-signup-heading">Please sign up</h2>
	<input type="email" class="form-control" placeholder="Email address" required autofocus>
	<input type="password" class="form-control" placeholder="Password" required>
	<input type="password" class="form-control" placeholder="Password control" required>
	<input type="text" class="form-control" placeholder="Name" required>
	<input type="text" class="form-control" placeholder="Surname" required>
	<input type="date" class="form-control" placeholder="Born date" required>
	
	<button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
</form>