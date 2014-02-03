<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<h1><tiles:getAsString name="title" /></h1>
<div class="row">
	<logic:iterate name="books" id="booksId">
		<div class="col-md-4">
			<h2>${booksId.title}</h2>
			<p>${booksId.description}</p>
			<p>
				<a class="btn btn-default" href="book.do?op=edit&id=${booksId.id}" role="button">Edit &raquo;</a>
			</p>
		</div>
	</logic:iterate>
</div>