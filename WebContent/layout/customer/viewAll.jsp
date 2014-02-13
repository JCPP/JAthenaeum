<%@ page import="com.github.jcpp.jathenaeum.Customer" %>

<%@ page import="java.util.ArrayList" %>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<h1><tiles:getAsString name="title" /></h1>
<logic:empty name="customers">
	<p>Empty Customer list.</p>
	<p><a class="btn btn-default" href="customer.do?op=add" role="button">Add a customer &raquo;</a></p>
</logic:empty>

<logic:notEmpty name="customers">
	<%
		int counter = 0;
		ArrayList<Customer> customers = (ArrayList<Customer>) request.getAttribute("customers");
		int customersSize = customers.size();
	%>
	<logic:iterate name="customers" id="customersId">
		<%
			//Open the tag if this is the third element
			if(counter % 3 == 0){
				out.write("<div class=\"row\">");
			}
		%>
		<div class="col-md-4">
			<h2>#${customersId.cardNumber}</h2>
			<h4>${customersId.name} ${customersId.surname}</h4>
			<h5>${customersId.email}</h5>
			<p>
				<a class="btn btn-default" href="customer.do?op=edit&id=${customersId.cardNumber}" role="button">Edit &raquo;</a>
				<a class="btn btn-danger" href="customer.do?op=delete&id=${customersId.cardNumber}" role="button">Delete &raquo;</a>
			</p>
		</div>
		<%
			//Close the tag if this is the last one or if this is the third element
			if((counter + 1) % 3 == 0 || (counter + 1) == customersSize){
				out.write("</div>");
			}
			counter++;
		%>
	</logic:iterate>
</logic:notEmpty>