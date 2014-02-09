<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<html:errors/>
<form class="form-signup" role="form" action="doCustomer.do?op=edit&id=${id}" method="post" name="editCustomerForm">
	<h2 class="form-signup-heading">Edit Customer</h2>
	<input type="text" name="name" class="form-control" placeholder="Name" value="${editCustomerForm.name}" required autofocus>
	<input type="text" name="surname" class="form-control" placeholder="Surname" value="${editCustomerForm.surname}" required>
	<input type="email" name="email" class="form-control" placeholder="Email Address" value="${editCustomerForm.email}" required>
	<button class="btn btn-lg btn-primary btn-block" type="submit">Edit Customer</button>
</form>