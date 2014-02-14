<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<h1><tiles:getAsString name="title" /></h1>
<logic:empty name="loans">
	<div class="alert alert-success"><b>Empty Expired Loan list.</b> You have good customers.</div>
</logic:empty>

<logic:notEmpty name="loans">
	<div class="table-responsive">
		
		<table class="table table-striped">
		  <thead>
		  	<tr>
		  		<th>ID</th>
		  		<th>Customer Card Number</th>
		  		<th>Copy ID</th>
		  		<th>Start Date</th>
		  		<th>End Date</th>
		  		<th>Returned</th>
		  		<th>Operations</th>
		  	</tr>
		  </thead>
		  <tbody>
		  	<logic:iterate name="loans" id="loansId">
				<tr>
					<td>${loansId.id}</td>
			  		<td>${loansId.customerCardNumber}</td>
			  		<td>${loansId.copyId}</td>
		  			<td>${loansId.startDate.date}/${loansId.startDate.month+1}/${loansId.startDate.year+1900}</td>
					<td>${loansId.endDate.date}/${loansId.endDate.month+1}/${loansId.endDate.year+1900}</td>
					<td>${loansId.returned}</td>
					<td>
						<a class="btn btn-default" href="loan.do?op=edit&id=${loansId.id}" role="button">Edit &raquo;</a>
						<a class="btn btn-danger" href="loan.do?op=delete&id=${loansId.id}" role="button">Delete &raquo;</a>
					</td>
			  	</tr>
			</logic:iterate>
		  </tbody>
		</table>
	</div>
</logic:notEmpty>