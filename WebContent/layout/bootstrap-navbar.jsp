<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<nav class="navbar navbar-default navbar-inverse" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">JAthenaeum</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-left">
				<li class="active"><a href="#">Link</a></li>
				<li><a href="#">Link</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Dropdown <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="newAuthor.do">Add an Author</a></li>
						<li><a href="#">Another action</a></li>
						<li><a href="#">Something else here</a></li>
						<li class="divider"></li>
						<li><a href="#">Separated link</a></li>
						<li class="divider"></li>
						<li><a href="#">One more separated link</a></li>
					</ul></li>
			</ul>
			<div class="nav navbar-nav navbar-right">
				<!-- User not present -->
				<logic:notPresent name="user" scope="session">
					<a class="btn btn-default" href="signup.do">Sign up</a>
					<a class="btn btn-default navbar-btn" href="signin.do">Sign in</a>
				</logic:notPresent>
				
				<!-- User present -->
				<logic:present name="user" scope="session">
					<a class="btn btn-default navbar-btn navbar-right" href="/manageAccount.do">Manage Account</a>
					<a class="btn btn-default navbar-btn navbar-right" href="/logout.do">Logout</a>
				</logic:present>
			</div>
		</div>
		<!-- /.navbar-collapse -->
	</nav>