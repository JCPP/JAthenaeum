<%@ page import="com.github.jcpp.jathenaeum.Author" %>

<%@ page import="java.util.ArrayList" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>


<h1><tiles:getAsString name="title" /></h1>
<logic:empty name="authors">
	<p>Empty authors list.</p>
	<p><a class="btn btn-default" href="author.do?op=add" role="button">Add an author &raquo;</a></p>
</logic:empty>

<logic:notEmpty name="authors">
	<%
		int counter = 0;
		ArrayList<Author> authors = (ArrayList<Author>) request.getAttribute("authors");
		int authorsSize = authors.size();
	%>
	<logic:iterate name="authors" id="authorsId">
		<%
			//Open the tag if this is the third element
			if(counter % 3 == 0){
				out.write("<div class=\"row\">");
			}
		%>
		
		<div class="col-md-4">
			<h2>${authorsId.name} ${authorsId.surname}</h2>
			
			<logic:notEmpty name="authorsId" property="bornDate">
	  			<h5>${authorsId.bornDate.date}/${authorsId.bornDate.month+1}/${authorsId.bornDate.year+1900}</h5>
	  		</logic:notEmpty>
	  		<logic:empty name="authorsId" property="bornDate">
				<h5></h5>
			</logic:empty>
			
			<div class="media">
				<logic:notEmpty name="authorsId" property="photo">
					<a class="pull-left" href="${authorsId.photo}" target="_blank">
					    <img src="${authorsId.photo}" class="img" style="min-height:50px; height:150px;" />
				  	</a>
				</logic:notEmpty>
				<div class="media-body">
					${authorsId.biography}
					<p>
						<a class="btn btn-default" href="author.do?op=edit&id=${authorsId.id}" role="button">Edit &raquo;</a>
						<a class="btn btn-danger" href="author.do?op=delete&id=${authorsId.id}" role="button">Delete &raquo;</a>
					</p>
				</div>
			</div>
		</div>
		<%
			//Close the tag if this is the last one or if this is the third element
			if((counter + 1) % 3 == 0 || (counter + 1) == authorsSize){
				out.write("</div>");
			}
			counter++;
		%>
	</logic:iterate>
</logic:notEmpty>