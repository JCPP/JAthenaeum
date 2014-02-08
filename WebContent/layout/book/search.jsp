<%@ page import="com.github.jcpp.jathenaeum.Author" %>

<%@ page import="java.util.ArrayList" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%
	ArrayList<Author> authors = (ArrayList<Author>) request.getAttribute("authors");
%>

<h1><tiles:getAsString name="title" /></h1>
<logic:notEqual name="search" value="true">
	<logic:empty name="books">
		<p>Empty books list.</p>
		<p><a class="btn btn-default" href="book.do?op=add" role="button">Add a book &raquo;</a></p>
	</logic:empty>
</logic:notEqual>

<form class="form-inline" role="form" action="doBook.do?op=search" method="post" name="searchBookForm">
	<h2 class="form-signup-heading">Search Books</h2>
	<input type="text" name="title" class="form-control" placeholder="Title" value="${book.title}" autofocus>
	<input type="text" name="cover" class="form-control" placeholder="Cover" value="${book.cover}">
	<input type="text" name="genre" class="form-control" placeholder="Genre" value="${book.genre}">
	<input type="text" name="isbn" class="form-control" placeholder="ISBN" value="${book.isbnCode}">
	<select name="authors" multiple class="form-control">
		<%
			for(int i = 0; i < authors.size(); i++){
				Author author = authors.get(i);
				int id = author.getId();
				out.write("<option value=\"" + id + "\"");				
				out.write(">" + author.getName() + " " + author.getSurname() + "</option>");
			}
		%>
	</select>
	<button class="btn btn-lg btn-primary" type="submit">Search Book</button>
</form>

<hr/>

<h2>Result</h2>
<logic:equal name="search" value="true">
	<logic:empty name="books">
		<p>No books found.</p>
	</logic:empty>
</logic:equal>
	
<logic:notEmpty name="books">
	<div class="table-responsive">
		
		<table class="table table-striped">
		  <thead>
		  	<tr>
		  		<th>ID</th>
		  		<th>Title</th>
		  		<th>Authors</th>
		  		<th>Genre</th>
		  		<th>Isbn</th>
		  		<th>Number of Copies</th>
		  		<th>Operations</th>
		  	</tr>
		  </thead>
		  <tbody>
		  	<logic:iterate name="books" id="booksId">
				<tr>
					<td>${booksId.id}</td>
			  		<td>${booksId.title}</td>
			  		<td>
			  			<bean:size id="length" name="booksId" property="authors"/>
						<% int counter = 0; %>
						<logic:iterate name="booksId" property="authors" id="authorsId" >
							${authorsId.name} ${authorsId.surname}<%
								counter++;
								if(counter < length){
									out.write(", ");
								}
							%>
						</logic:iterate>
					</td>
					<td>${booksId.genre}</td>
					<td>${booksId.isbnCode}</td>
					<td>${booksId.numberOfCopies}</td>
					<td>
						<a class="btn btn-success" href="book.do?op=addCopies&id=${booksId.id}" role="button">Add copies &raquo;</a>
						<a class="btn btn-default" href="book.do?op=edit&id=${booksId.id}" role="button">Edit &raquo;</a>
						<a class="btn btn-danger" href="book.do?op=delete&id=${booksId.id}" role="button">Delete &raquo;</a>
					</td>
			  	</tr>
			</logic:iterate>
		  </tbody>
		</table>
	</div>
</logic:notEmpty>