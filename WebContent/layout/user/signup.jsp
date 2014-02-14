<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html:errors/>

<form class="form-signup" role="form" action="doUser.do?op=signup" method="post" name="signupForm">
	<h2 class="form-signup-heading">Sign Up</h2>
	<input type="email" name="email" class="form-control" placeholder="Email address" value="${signupForm.email}" required autofocus>
	<input type="password" name="password" class="form-control" placeholder="Password" required>
	<input type="password" name="passwordControl" class="form-control" placeholder="Retype Password" required>
	<input type="text" name="name" class="form-control" placeholder="Name" value="${signupForm.name}">
	<input type="text" name="surname" class="form-control" placeholder="Surname" value="${signupForm.surname}">
	<input type="date" name="bornDate" class="form-control" placeholder="01/02/1970" value="${signupForm.bornDate}">
	<button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
</form>