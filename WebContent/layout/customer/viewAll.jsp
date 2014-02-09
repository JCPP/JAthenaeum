<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<h1><tiles:getAsString name="title" /></h1>
<logic:empty name="customers">
	<p>Empty Customer list.</p>
	<p><a class="btn btn-default" href="customer.do?op=add" role="button">Add a customer &raquo;</a></p>
</logic:empty>

<logic:notEmpty name="customers">
	<div class="row">
		<logic:iterate name="customers" id="customersId">
			<div class="col-md-4">
				<h2>#${customersId.cardNumber}</h2>
				<h4>${customersId.name} ${customersId.surname}</h4>
				<h5>${customersId.email}</h5>
				<p>
					<a class="btn btn-default" href="customer.do?op=edit&id=${customersId.cardNumber}" role="button">Edit &raquo;</a>
					<a class="btn btn-danger" href="customer.do?op=delete&id=${customersId.cardNumber}" role="button">Delete &raquo;</a>
				</p>
			</div>
		</logic:iterate>
	</div>
</logic:notEmpty>