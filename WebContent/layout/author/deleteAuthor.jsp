<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html:errors/>
<form class="form-signup" role="form" action="doAuthor.do?op=delete&id=${author.id}" method="post" name="deleteAuthorForm">
	<button class="btn btn-lg btn-danger btn-block" type="submit">Delete Author "${author.name} ${author.surname}"</button>
</form>