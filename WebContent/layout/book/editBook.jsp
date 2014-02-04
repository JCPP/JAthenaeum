<%@ page import="com.github.jcpp.jathenaeum.Author" %>

<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%
	ArrayList<Author> authors = (ArrayList<Author>) request.getAttribute("authors");
	ArrayList<Author> bookAuthors = (ArrayList<Author>) request.getAttribute("bookAuthors");
%>

<html:errors/>
<form class="form-signup" role="form" action="doBook.do?op=edit&id=${book.id}" method="post" name="addBookForm">
	<h2 class="form-signup-heading">Edit a Book</h2>
	<input type="text" name="title" class="form-control" placeholder="Title" value="${book.title}" required autofocus>
	<input type="text" name="cover" class="form-control" placeholder="Cover" value="${book.cover}">
	<input type="text" name="genre" class="form-control" placeholder="Genre" value="${book.genre}">
	<input type="text" name="isbn" class="form-control" placeholder="ISBN" value="${book.isbnCode}">
	<textarea name="description" class="form-control" rows="3" placeholder="Description">${book.description}</textarea>
	<select name="authors" multiple class="form-control">
		<%
			for(int i = 0; i < authors.size(); i++){
				for(int j = 0; j < bookAuthors.size(); j++){
					Author author = authors.get(i);
					int id = author.getId();
					
					out.write("<option value=\"" + id + "\"");
					if(author.getId() == bookAuthors.get(j).getId()){
						out.write(" selected");
					}
					out.write(">" + author.getName() + " " + author.getSurname() + "</option>");
				}
			}
		%>
	</select>
	<button class="btn btn-lg btn-primary btn-block" type="submit">Edit Book</button>
</form>