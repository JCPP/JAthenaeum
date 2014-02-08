<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<h1><tiles:getAsString name="title" /></h1>
<logic:notEqual name="search" value="true">
	<logic:empty name="authors">
		<p>Empty authors list.</p>
		<p><a class="btn btn-default" href="author.do?op=add" role="button">Add an author &raquo;</a></p>
	</logic:empty>
</logic:notEqual>

<form class="form-inline" role="form" action="doAuthor.do?op=search" method="post" name="searchAuthorForm">
	<h2 class="form-signup-heading">Search Authors</h2>
	<input type="text" name="name" class="form-control" placeholder="Name" value="${author.name}" autofocus>
	<input type="text" name="surname" class="form-control" placeholder="Surname" value="${author.surname}">
	<input type="text" name="photo" class="form-control" placeholder="Photo URL" value="${author.photo}">
	<input type="date" name="bornDate" class="form-control" placeholder="01/02/1970"
	<logic:empty name="author" property="bornDate">
		value=""
	</logic:empty>
	<logic:notEmpty name="author" property="bornDate">
		value="${author.bornDate.date}/${author.bornDate.month+1}/${author.bornDate.year+1900}"
	</logic:notEmpty>
	>
	<button class="btn btn-lg btn-primary" type="submit">Search Authors</button>
</form>

<hr/>

<h2>Result</h2>
<logic:equal name="search" value="true">
	<logic:empty name="authors">
		<p>No authors found.</p>
	</logic:empty>
</logic:equal>
	
<logic:notEmpty name="authors">
	<div class="table-responsive">
		
		<table class="table table-striped">
		  <thead>
		  	<tr>
		  		<th>ID</th>
		  		<th>Name</th>
		  		<th>Surname</th>
		  		<th>Born Date</th>
		  		<th>Operations</th>
		  	</tr>
		  </thead>
		  <tbody>
		  	<logic:iterate name="authors" id="authorsId">
				<tr>
					<td>${authorsId.id}</td>
			  		<td>${authorsId.name}</td>
			  		<td>${authorsId.surname}</td>
			  		<logic:notEmpty name="authorsId" property="bornDate">
			  			<td>${authorsId.bornDate.date}/${authorsId.bornDate.month+1}/${authorsId.bornDate.year+1900}</td>
			  		</logic:notEmpty>
			  		<logic:empty name="authorsId" property="bornDate">
						<td></td>
					</logic:empty>
					<td>
						<a class="btn btn-default" href="author.do?op=edit&id=${authorsId.id}" role="button">Edit &raquo;</a>
						<a class="btn btn-danger" href="author.do?op=delete&id=${authorsId.id}" role="button">Delete &raquo;</a>
					</td>
			  	</tr>
			</logic:iterate>
		  </tbody>
		</table>
	</div>
</logic:notEmpty>