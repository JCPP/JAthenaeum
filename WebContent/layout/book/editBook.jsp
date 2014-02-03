<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html:errors/>
<form class="form-signup" role="form" action="doBook.do?op=edit&id=id" method="post" name="addBookForm">
	<h2 class="form-signup-heading">Add a Book</h2>
	<input type="text" name="title" class="form-control" placeholder="Title" value="${addBookForm.title}" required autofocus>
	<input type="text" name="cover" class="form-control" placeholder="Cover" value="${addBookForm.cover}">
	<input type="text" name="genre" class="form-control" placeholder="Genre" value="${addBookForm.genre}">
	<input type="text" name="isbn" class="form-control" placeholder="ISBN" value="${addBookForm.isbn}">
	<input type="text" name="description" class="form-control" placeholder="Description" value="${addBookForm.description}">
	<select name="authors" multiple class="form-control">
		<logic:iterate name="authors" id="authorsId">
			<option value="${authorsId.id}">${authorsId.name} ${authorsId.surname}</option>
		</logic:iterate>
	</select>
	<button class="btn btn-lg btn-primary btn-block" type="submit">Add Book</button>
</form>