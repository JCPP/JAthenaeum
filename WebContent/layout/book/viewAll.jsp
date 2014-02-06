<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<h1><tiles:getAsString name="title" /></h1>
<logic:empty name="books">
	<p>Empty books list.</p>
	<p><a class="btn btn-default" href="book.do?op=add" role="button">Add a book &raquo;</a></p>
</logic:empty>

<logic:notEmpty name="books">
	<div class="row">
		<logic:iterate name="books" id="booksId">
			<div class="col-md-4">
				<h2>${booksId.title}</h2>
				<h4>
					<bean:size id="length" name="booksId" property="authors"/>
					<% int counter = 0; %>
					<logic:iterate name="booksId" property="authors" id="authors" >
						${authors.name} ${authors.surname}<%
							counter++;
							if(counter < length){
								out.write(", ");
							}
						%>
					</logic:iterate>
				</h4>
				<h5>${booksId.genre}</h5>
				
				<logic:notEmpty name="booksId" property="cover">
					<a class="pull-left" href="${booksId.cover}">
					    <img src="${booksId.cover}" class="img" style="min-height:50px; height:150px;" />
				  	</a>
				</logic:notEmpty>
				
				<h6>${booksId.isbnCode}</h6>
				<p>${booksId.description}</p>
				<p>
					<a class="btn btn-default" href="book.do?op=edit&id=${booksId.id}" role="button">Edit &raquo;</a>
					<a class="btn btn-danger" href="book.do?op=delete&id=${booksId.id}" role="button">Delete &raquo;</a>
				</p>
			</div>
		</logic:iterate>
	</div>
</logic:notEmpty>