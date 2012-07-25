<%@ include file="/WEB-INF/jsp/header.jsp" %>

<!-- Main hero unit for a primary marketing message or call to action -->
<div class="hero-unit">
  <h1>Hello, world!</h1>
  <p>This is a template for a simple marketing or informational website. It includes a large callout called the hero unit and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
  <p><a class="btn btn-primary btn-large">Learn more &raquo;</a></p>
</div>
<form action="<c:url value="/j_spring_security_check"/>" method="post">
	<input type="text" name="j_username" value="demo@gmail.com" />
	<input type="password" name="j_password" value="demo" />
	<input type="submit" value="connexion" />
</form>
<!-- Example row of columns -->
<div class="row">
  <div class="span4">
    <h2>Heading</h2>
     <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
    <p><a class="btn" href="#">View details &raquo;</a></p>
  </div>
  <div class="span4">
    <h2>Heading</h2>
     <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
    <p><a class="btn" href="#">View details &raquo;</a></p>
 </div>
  <div class="span4">
    <h2>Heading</h2>
    <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
    <p><a class="btn" href="#">View details &raquo;</a></p>
  </div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>