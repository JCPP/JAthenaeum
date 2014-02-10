<%@ page import="com.github.jcpp.jathenaeum.Book" %>
<%@ page import="com.github.jcpp.jathenaeum.Customer" %>
<%@ page import="com.github.jcpp.jathenaeum.Loan" %>

<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%
	Loan loan = (Loan) request.getAttribute("loan");
%>

<html:errors/>
<form class="form-signup" role="form" action="doLoan.do?op=edit&id=${loan.id}" method="post" name="editLoanForm">
	<h2 class="form-signup-heading">Edit Loan</h2>
	<select name="customerCardNumber" class="form-control" required>
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
	<select name="bookId" class="form-control" required>
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
	<input type="date" name="startDate" class="form-control" placeholder="01/02/2014" value="${loan.startDate.date}/${loan.startDate.month+1}/${loan.startDate.year+1900}" required>
	<input type="date" name="endDate" class="form-control" placeholder="20/02/2014" value="${loan.endDate.date}/${loan.endDate.month+1}/${loan.endDate.year+1900}" required>
	<label class="checkbox"> <input type="checkbox" value="returned" name="returned"
	<logic:equal name="loan" property="returned" value="true">
		checked
	</logic:equal>
	>Returned</label>
	<button class="btn btn-lg btn-primary btn-block" type="submit">Edit Loan</button>
</form>