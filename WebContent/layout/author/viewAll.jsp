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
				<h2>${authorsId.name} ${authorsId.surname}</h2>
				<h5>${authorsId.bornDate}</h5>
				
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
		</logic:iterate>
	</div>
</logic:notEmpty>