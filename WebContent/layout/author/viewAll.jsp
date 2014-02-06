<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<h1><tiles:getAsString name="title" /></h1>
<logic:empty name="authors">
	<p>Empty authors list.</p>
	<p><a class="btn btn-default" href="author.do?op=add" role="button">Add an author &raquo;</a></p>
</logic:empty>

<logic:notEmpty name="authors">
	<div class="row">
		<logic:iterate name="authors" id="authorsId">
			<div class="col-md-4">
				<h2>${authorsId.title}</h2>
				<h4>
					<bean:size id="length" name="authorsId" property="authors"/>
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
				<h5>${authorsId.genre}</h5>
				<h6>${authorsId.isbnCode}</h6>
				
				<div class="media">
					<logic:notEmpty name="authorsId" property="cover">
						<a class="pull-left" href="${authorsId.cover}" target="_blank">
						    <img src="${authorsId.cover}" class="img" style="min-height:50px; height:150px;" />
					  	</a>
					</logic:notEmpty>
					<div class="media-body">
						${authorsId.description}
						<p>
							<a class="btn btn-default" href="author.do?op=edit&id=${authorsId.id}" role="button">Edit &raquo;</a>
							<a class="btn btn-danger" href="author.do?op=delete&id=${authorsId.id}" role="button">Delete &raquo;</a>
						</p>
					</div>
				</div>
			</div>
		</logic:iterate>
	</div>
</logic:notEmpty>