<%@ page import="com.github.jcpp.jathenaeum.Author" %>

<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%
	ArrayList<Author> authors = (ArrayList<Author>) request.getAttribute("authors");
	ArrayList<Author> selectedAuthors = (ArrayList<Author>) request.getAttribute("selectedAuthors");
%>
<html:errors/>
<form class="form-signup" role="form" action="doBook.do?op=add" method="post" name="addBookForm">
	<h2 class="form-signup-heading">Add a Book</h2>
	<input type="text" name="title" class="form-control" placeholder="Title" value="${addBookForm.title}" required autofocus>
	<input type="text" name="cover" class="form-control" placeholder="Cover" value="${addBookForm.cover}">
	<input type="text" name="genre" class="form-control" placeholder="Genre" value="${addBookForm.genre}">
	<input type="text" name="isbn" class="form-control" placeholder="ISBN" value="${addBookForm.isbn}">
	<textarea name="description" class="form-control" rows="3" placeholder="Description">${addBookForm.description}</textarea>
	<select name="authors" multiple class="form-control">
		<%
			for(int i = 0; i < authors.size(); i++){
				Author author = authors.get(i);
				int id = author.getId();
				out.write("<option value=\"" + id + "\"");
				
				//Set the selected books
				for(int j = 0; j < selectedAuthors.size(); j++){
					if(author.getId() == selectedAuthors.get(j).getId()){
						out.write(" selected");
					}
				}
				
				out.write(">" + author.getName() + " " + author.getSurname() + "</option>");
			}
		%>
	</select>
	<button class="btn btn-lg btn-primary btn-block" type="submit">Add Book</button>
</form>