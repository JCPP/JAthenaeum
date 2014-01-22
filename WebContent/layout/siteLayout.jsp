<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html>
<html:html>
<head>
<title><tiles:getAsString name="title" /></title>
</head>
<body>
	<table border="1" width="600" cellspacing="5">
		<tbody>
			<tr>
				<td colspan="2"><tiles:insert attribute="header" /></td>
			</tr>
			<tr>
				<td width="200"><tiles:insert attribute="navigation" /></td>
				<td width="400"><tiles:insert attribute="body" /></td>
			</tr>
			<tr>
				<td colspan="2"><tiles:insert attribute="footer" /></td>
			</tr>
		</tbody>
	</table>
</body>
</html:html>