<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:errors/>
<form class="form-signup" role="form" action="doAuthor.do?op=add" method="post" name="addAuthorForm">
	<h2 class="form-signup-heading">Add an Author</h2>
	<input type="text" name="name" class="form-control" placeholder="Name" value="${addAuthorForm.name}" required autofocus>
	<input type="text" name="surname" class="form-control" placeholder="Surname" value="${addAuthorForm.surname}" required>
	<input type="text" name="photo" class="form-control" placeholder="Photo URL" value="${addAuthorForm.photo}">
	<input type="date" name="bornDate" class="form-control" placeholder="01/02/1970" value="${addAuthorForm.bornDate}">
	<textarea name="biography" class="form-control" rows="3" placeholder="Biography">${addAuthorForm.biography}</textarea>
	<button class="btn btn-lg btn-primary btn-block" type="submit">Add Author</button>
</form>