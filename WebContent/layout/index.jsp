<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<!-- User not present -->
<logic:notPresent name="user" scope="session">
	<div class="row">
		<div class="col-md-4">
			<h2>What are you waiting for?</h2>
			<p>Login using<br/>
				<b>test@test.it</b><br/>
				<b>test</b>
			</p>
			<p>
				<a class="btn btn-default" href="user.do?op=login" role="button">Login	&raquo;</a>
			</p>
		</div>
	</div>
</logic:notPresent>
<logic:present name="user" scope="session">
	<div class="row">
		<div class="col-md-4">
			<h2>Book</h2>
			<p>
				Manage the Books
			</p>
			<p>
				<a class="btn btn-success" href="book.do?op=add" role="button">Add &raquo;</a>
				<a class="btn btn-default" href="book.do?op=viewAll" role="button">View All &raquo;</a>
				<a class="btn btn-default" href="book.do?op=search" role="button">Search &raquo;</a>
			</p>
		</div>
		<div class="col-md-4">
			<h2>Author</h2>
			<p>
				Manage the Authors
			</p>
			<p>
				<a class="btn btn-success" href="author.do?op=add" role="button">Add &raquo;</a>
				<a class="btn btn-default" href="author.do?op=viewAll" role="button">View All &raquo;</a>
				<a class="btn btn-default" href="author.do?op=search" role="button">Search &raquo;</a>
			</p>
		</div>
		<div class="col-md-4">
			<h2>Customer</h2>
			<p>
				Manage the Customers
			</p>
			<p>
				<a class="btn btn-success" href="customer.do?op=add" role="button">Add &raquo;</a>
				<a class="btn btn-default" href="customer.do?op=viewAll" role="button">View All &raquo;</a>
			</p>
		</div>
		<div class="col-md-4">
			<h2>Loan</h2>
			<p>
				Manage the Loan
			</p>
			<p>
				<a class="btn btn-success" href="loan.do?op=add" role="button">Add &raquo;</a>
				<a class="btn btn-default" href="loan.do?op=viewAll" role="button">View All &raquo;</a>
				<a class="btn btn-danger" href="loan.do?op=viewAllExpired" role="button">View All Expired &raquo;</a>
				<a class="btn btn-default" href="loan.do?op=search" role="button">Search &raquo;</a>
			</p>
		</div>
	</div>
</logic:present>