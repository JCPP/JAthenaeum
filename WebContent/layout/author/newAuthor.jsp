<%@ page contentType="text/html; charset=iso-8859-1" language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<html:form action="/doNewAuthor" styleClass="form-signup" method="post">
	<div>
		<html:errors/>
	</div>
	<br/>	
    Nome : <html:text property="nome" errorKey="name"/><br/><br/>
    Cognome : <html:text property="cognome" errorKey="surname"/><br/><br/>
    Foto : <html:text property="foto" errorKey="foto"/><br/><br/>
    Data nascita : <html:text property="data" errorKey="data"/><br/><br/>
    Biografia : <html:text property="biografia" errorKey="biografia"/><br/><br/>
    <html:submit value="Submit"/>
    
    </html:form>

</body>
</html>