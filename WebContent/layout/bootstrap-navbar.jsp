<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<tiles:useAttribute name="title" />
<nav class="navbar navbar-default navbar-inverse" role="navigation">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.do">JAthenaeum</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-left">
				<li><a href="#">Link</a></li>
				<li><a href="#">Link</a></li>
				<li class="dropdown
				<% if(((String)title).contains("Book")){
					out.write("active");
				}%>
				"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Book <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="book.do?op=add">Add</a></li>
						<li><a href="book.do?op=viewAll">View All</a></li>
						<li><a href="book.do?op=search">Search</a></li>
					</ul></li>
				
				<li class="dropdown
				<% if(((String)title).contains("Author")){
					out.write("active");
				}%>
				"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Author <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="author.do?op=add">Add</a></li>
						<li><a href="author.do?op=viewAll">View All</a></li>
						<li><a href="author.do?op=search">Search</a></li>
					</ul></li>
					
				<li class="dropdown
				<% if(((String)title).contains("Customer")){
					out.write("active");
				}%>
				"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Customer <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="customer.do?op=add">Add</a></li>
						<li><a href="customer.do?op=viewAll">View All</a></li>
					</ul></li>
					
				<li class="dropdown
				<% if(((String)title).contains("Loan")){
					out.write("active");
				}%>
				"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown">Loan <b class="caret"></b></a>
					<ul class="dropdown-menu">
						<li><a href="loan.do?op=add">Add</a></li>
						<li><a href="loan.do?op=viewAll">View All</a></li>
						<li><a href="loan.do?op=viewAllExpired">View All Expired</a></li>
						<li><a href="loan.do?op=search">Search</a></li>
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
					<a class="btn btn-default navbar-btn" href="/manageAccount.do">Manage Account</a>
					<a class="btn btn-default navbar-btn" href="logout.do">Logout</a>
				</logic:present>
			</div>
		</div>
		<!-- /.navbar-collapse -->
	</nav>