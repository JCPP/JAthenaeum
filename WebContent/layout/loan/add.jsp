<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html:errors/>
<form class="form-signup" role="form" action="doLoan.do?op=add" method="post" name="addLoanForm">
	<h2 class="form-signup-heading">Add a Loan</h2>
	<select name="customerCardNumber" class="form-control" required>
		<logic:iterate name="customers" id="customersId">
			<option value="${customersId.cardNumber}">${customersId.name} ${customersId.surname}</option>
		</logic:iterate>
	</select>
	<select name="bookId" class="form-control" required>
		<logic:iterate name="books" id="booksId">
			<option value="${booksId.id}">${booksId.title}</option>
		</logic:iterate>
	</select>
	<input type="date" name="startDate" class="form-control" placeholder="01/02/2014" value="${addLoanForm.startDate}" required>
	<input type="date" name="endDate" class="form-control" placeholder="20/02/2014" value="${addLoanForm.endDate}" required>
	<button class="btn btn-lg btn-primary btn-block" type="submit">Add Loan</button>
</form>