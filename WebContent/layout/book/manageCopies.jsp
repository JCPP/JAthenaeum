<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html:errors/>
<form class="form-signup" role="form" action="doBook.do?op=manageCopies&id=${book.id}" method="post" name="manageBookCopiesForm">
	<h2 class="form-signup-heading">Manage Book Copies</h2>
	<input type="number" name="numberOfCopies" class="form-control" placeholder="Number Of Copies" value="${book.numberOfCopies}" required autofocus>
	<button class="btn btn-lg btn-primary btn-block" type="submit">Manage Book Copies</button>
</form>