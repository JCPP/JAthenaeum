<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:errors/>
<form class="form-signup" role="form" action="doAuthor.do?op=edit&id=${editAuthorForm.id}" method="post" name="editAuthorForm">
	<h2 class="form-signup-heading">Edit Author</h2>
	<input type="text" name="name" class="form-control" placeholder="Name" value="${editAuthorForm.name}" required autofocus>
	<input type="text" name="surname" class="form-control" placeholder="Surname" value="${editAuthorForm.surname}" required>
	<input type="text" name="photo" class="form-control" placeholder="Photo URL" value="${editAuthorForm.photo}">
	<input type="date" name="bornDate" class="form-control" placeholder="01/02/1970" value="${editAuthorForm.bornDate}">
	<textarea name="biography" class="form-control" rows="3" placeholder="Biography">${editAuthorForm.biography}</textarea>
	<button class="btn btn-lg btn-primary btn-block" type="submit">Edit Author</button>
</form>