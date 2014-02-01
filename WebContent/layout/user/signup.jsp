<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html:errors/>

<form class="form-signup" role="form" action="doRegister.do" method="post" name="registerform">
	<h2 class="form-signup-heading">Sign Up</h2>
	<input type="email" name="email" class="form-control" placeholder="Email address" value="${registerform.email}" required autofocus>
	<input type="password" name="password" class="form-control" placeholder="Password" required>
	<input type="password" name="passwordControl" class="form-control" placeholder="Retype Password" required>
	<input type="text" name="name" class="form-control" placeholder="Name" value="${registerform.name}">
	<input type="text" name="surname" class="form-control" placeholder="Surname" value="${registerform.surname}">
	<input type="date" name="bornDate" class="form-control" placeholder="01/02/1970" value="${registerform.bornDate}">
	<button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
</form>