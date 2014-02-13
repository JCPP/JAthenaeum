<%@ page import="com.github.jcpp.jathenaeum.Book" %>
<%@ page import="com.github.jcpp.jathenaeum.Customer" %>
<%@ page import="com.github.jcpp.jathenaeum.Loan" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%
	Loan loan = (Loan) request.getAttribute("loan");
%>

<h1><tiles:getAsString name="title" /></h1>
<logic:notEqual name="search" value="true">
	<logic:empty name="loans">
		<p>Empty loans list.</p>
		<p><a class="btn btn-default" href="loan.do?op=add" role="button">Add a loan &raquo;</a></p>
	</logic:empty>
</logic:notEqual>

<form class="form-inline" role="form" action="doLoan.do?op=search" method="post" name="addLoanForm">
	<select name="customerCardNumber" class="form-control">
		<option value=""></option>
		<logic:iterate name="customers" id="customersId">
			<option value="${customersId.cardNumber}"
			<%
				if( loan.getCustomerCardNumber() == ((Customer)customersId).getCardNumber()){
					out.write("selected");
				}
			%>
			>${customersId.name} ${customersId.surname}</option>
		</logic:iterate>
	</select>
	<select name="bookId" class="form-control">
		<option value=""></option>
		<logic:iterate name="books" id="booksId">
			<option value="${booksId.id}"
			<%
				if( loan.getCopyId() == ((Book)booksId).getId()){
					out.write("selected");
				}
			%>
			>${booksId.title}</option>
		</logic:iterate>
	</select>
	<input type="date" name="startDate" class="form-control" placeholder="01/02/2014"
		<logic:empty name="loan" property="startDate">
			value=""
		</logic:empty>
		<logic:notEmpty name="loan" property="startDate">
			value="${loan.startDate.date}/${loan.startDate.month+1}/${loan.startDate.year+1900}"
		</logic:notEmpty>
	>
	<input type="date" name="endDate" class="form-control" placeholder="20/02/2014"
		<logic:empty name="loan" property="endDate">
			value=""
		</logic:empty>
		<logic:notEmpty name="loan" property="endDate">
			value="${loan.endDate.date}/${loan.endDate.month+1}/${loan.endDate.year+1900}"
		</logic:notEmpty>
	>
	<label class="checkbox"> <input type="checkbox" value="returned" name="returned"
	<logic:equal name="loan" property="returned" value="true">
		checked
	</logic:equal>
	>Returned</label>
	<button class="btn btn-lg btn-primary" type="submit">Search Loans</button>
</form>

<hr/>

<h2>Result</h2>
<logic:equal name="search" value="true">
	<logic:empty name="loans">
		<p>No loans found.</p>
	</logic:empty>
</logic:equal>


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