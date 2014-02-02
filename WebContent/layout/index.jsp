<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<div class="row">
	<logic:iterate name="books" id="booksId">
		<div class="col-md-4">
			<h2>${booksId.title}</h2>
			<p>${booksId.description}</p>
		</div>
	</logic:iterate>
</div>

<!-- Example row of columns -->
<!--
<div class="row">
	<div class="col-md-4">
		<h2>Heading</h2>
		<p>Donec id elit non mi porta gravida at eget metus. Fusce
			dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut
			fermentum massa justo sit amet risus. Etiam porta sem malesuada magna
			mollis euismod. Donec sed odio dui.</p>
		<p>
			<a class="btn btn-default" href="#" role="button">View details
				&raquo;</a>
		</p>
	</div>
	<div class="col-md-4">
		<h2>Heading</h2>
		<p>Donec id elit non mi porta gravida at eget metus. Fusce
			dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut
			fermentum massa justo sit amet risus. Etiam porta sem malesuada magna
			mollis euismod. Donec sed odio dui.</p>
		<p>
			<a class="btn btn-default" href="#" role="button">View details
				&raquo;</a>
		</p>
	</div>
	<div class="col-md-4">
		<h2>Heading</h2>
		<p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in,
			egestas eget quam. Vestibulum id ligula porta felis euismod semper.
			Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum
			nibh, ut fermentum massa justo sit amet risus.</p>
		<p>
			<a class="btn btn-default" href="#" role="button">View details
				&raquo;</a>
		</p>
	</div>
</div>
-->