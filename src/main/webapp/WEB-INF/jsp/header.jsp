<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Fairness rating</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    
    <link href="<c:url value="/resources/css/rating.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/bootstrap.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/jquery-ui.css"/>" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 110px;
        padding-bottom: 40px;
      }
    </style>

    <!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- Le fav and touch icons -->
    <link rel="shortcut icon" href="./resources/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="./resources/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="./resources/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="./resources/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="./resources/ico/apple-touch-icon-57-precomposed.png">
 	<script src="<c:url value="/resources/js/jquery.js"/>"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="/resources/js/jquery-ui.js"/>"></script>
    <script src="<c:url value="/resources/js/validate.js"/>"></script>
    <script src="<c:url value="/resources/js/rate-validation.js"/>"></script>
 	
 	
  </head>
  <body>

    <div class="navbar navbar-fixed-top" >
      <div class="container navbar-inner" style="background-color: white; background-image: none; border: 0px; -webkit-box-shadow: none; -moz-box-shadow: none; box-shadow: none;">
    	<a class="brand" href="<c:url value="/"/>" style="margin: 0px; padding: 5px 5px 5px 5px"><img src="<c:url value="/resources/img/home/brand.png"/>" height="50px" /></a>
          <div class="btn-group pull-right" style="margin: 10px 0px 0px 0px">
	          <sec:authorize ifAllGranted="ROLE_USER">
		            <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
		              <i class="icon-user"></i> <c:out value="${user.firstname} ${user.lastname}" />
		              <span class="caret"></span>
		            </a>
		            <ul class="dropdown-menu">
		              <li><a href="<c:url value="/profile/"/>">Profile</a></li>
		              <li class="divider"></li>
		              <li><a href="<c:url value="/logout"/>">Sign Out</a></li>
		            </ul>
	          </sec:authorize>
	          <sec:authorize ifNotGranted="ROLE_USER">
		          <form style="margin: 0px;" action="<c:url value="/j_spring_security_check"/>" method="post">
					<input type="text" name="j_username" value="demo@gmail.com" style="width: 140px"  />
					<input type="password" name="j_password" value="demo" style="width: 80px" />
					<input style="margin-top: -9px;" class="btn" type="submit" value="connection" />
				</form>
			  </sec:authorize>          
		  </div>
	  </div>
      <div class="navbar-inner" style="background-image: -webkit-linear-gradient(top, #325a8e, #183e70);">
        <div class="container">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          
          <div class="nav-collapse">
            <ul class="nav">
              <sec:authorize ifAllGranted="ROLE_USER">
	              <li><a href="<c:url value="/rate/home"/>">Dashboard</a></li>
	              <li><a href="<c:url value="/rate/add"/>">New company</a></li>
	              <li><a href="<c:url value="/rate/view"/>">View companies</a></li>
	          </sec:authorize>
              <li><a href="<c:url value="/"/>">Home</a></li>
              <li><a href="<c:url value="/about"/>">About us</a></li>
              <li><a href="<c:url value="/service"/>">Services</a></li>
              <li><a href="<c:url value="/solution"/>">Solution</a></li>
              <li><a href="<c:url value="/career"/>">Careers</a></li>
              <li><a href="<c:url value="/contact"/>">Contact us</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container">