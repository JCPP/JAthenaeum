<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<logic:notEqual name="redirect" value="">
	<meta http-equiv="refresh" content="3;url=<tiles:getAsString name="redirect" />" />
</logic:notEqual>
<!-- <link rel="shortcut icon" href="../../docs-assets/ico/favicon.png"> -->

<title><tiles:getAsString name="title" /></title>

<!-- Bootstrap core CSS -->
<link href="bootstrap-3.0.3/dist/css/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="<tiles:getAsString name="style" />" rel="stylesheet">

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->