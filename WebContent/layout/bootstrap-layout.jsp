<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<tiles:useAttribute name="jumbotronEnabled" />
<tiles:useAttribute name="navbarEnabled" />
<tiles:useAttribute name="footerEnabled" />
<!DOCTYPE html>
<html lang="en">
<head>
<tiles:insert attribute="head">
	<tiles:put name="title" beanName="title" />
	<tiles:put name="style" beanName="style" />
</tiles:insert>
</head>

<body>
	<logic:equal name="navbarEnabled" value="true">
		<tiles:insert attribute="navbar" />
	</logic:equal>

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<logic:equal name="jumbotronEnabled" value="true">
		<div class="jumbotron">
			<tiles:insert attribute="jumbotron" />
		</div>
	</logic:equal>

	<div class="container">
		<tiles:insert attribute="body" />

		<logic:equal name="footerEnabled" value="true">
			<hr>
			<footer>
				<tiles:insert attribute="footer" />
			</footer>
		</logic:equal>

	</div>
	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
	<script src="bootstrap-3.0.3/dist/js/bootstrap.min.js"></script>
</body>
</html>
