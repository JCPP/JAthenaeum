<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<html:errors/>
<form class="form-signin" role="form" action="doSignin.do" method="post" name="signinform">
	<h2 class="form-signin-heading">Sign in</h2>
	<input type="email" name="email" class="form-control" placeholder="Email address" value="${signinform.email}" required autofocus>
	<input type="password" name="password" class="form-control" placeholder="Password" required>
	<label class="checkbox"> <input type="checkbox" value="remember-me">Remember me</label>
	<button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
</form>