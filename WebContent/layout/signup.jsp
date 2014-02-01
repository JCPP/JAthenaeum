<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

	<html:form action="/doRegister" styleClass="form-signup" method="post">
	<div>
		<html:errors/>
	</div>
	<br/>	
    Email : <html:text property="email" errorKey="email"/><br/><br/>
    Password : <html:password property="password" errorKey="password"/><br/><br/>
    Password control : <html:password property="passwordControl" errorKey="passwordControl"/><br/><br/>
    Nome : <html:text property="name" errorKey="name"/><br/><br/>
    Cognome : <html:text property="surname" errorKey="surname"/><br/><br/>
    Born Date : <html:text property="bornDate" errorKey="bornDate"/><br/><br/>
    <html:submit value="Submit"/>
    
    </html:form>