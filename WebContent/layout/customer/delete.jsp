<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html:errors/>
<form class="form-signup" role="form" action="doCustomer.do?op=delete&id=${customer.cardNumber}" method="post" name="deleteCustomerForm">
	<button class="btn btn-lg btn-danger btn-block" type="submit">Delete Customer "${customer.name} ${customer.surname}"</button>
</form>