<%@ include file="/WEB-INF/jsp/header.jsp" %>

<script src="<c:url value="/resources/js/jquery.zrssfeed.min.js"/>"></script>
    
<script type="text/javascript">
	$(document).ready(function () {
		$('#myCarousel').carousel();
/* 		$('#rss').rssfeed("http://www.ft.com/rss/home/uk", {
		    limit: 5
		}); */
	});
</script>

<div id="myCarousel" class="carousel slide" style="width: 970px; margin: auto;">
  <div class="carousel-inner">
    <div class="item active">
      <img style="border-radius: 10px;" src="<c:url value="/resources/img/home/galet.jpg"/>" style="width: 970px" alt="">
      <div class="carousel-caption">
        <h4>Rating Advisory</h4>
      </div>
    </div>
    <div class="item">
      <img style="border-radius: 10px;" src="<c:url value="/resources/img/home/caroussel2.jpg"/>" alt="">
      <div class="carousel-caption">
        <h4>Creation of Tailor-made rating models</h4>
      </div>
    </div>
    <div class="item">
      <img style="border-radius: 10px;" src="<c:url value="/resources/img/home/caroussel3.jpg"/>" alt="">
      <div class="carousel-caption">
        <h4>Financial training</h4>
      </div>
    </div>
    <div class="item">
      <img style="border-radius: 10px;" src="<c:url value="/resources/img/home/caroussel1.jpg"/>" alt="">
      <div class="carousel-caption">
        <h4>Efficient snapshots and business reports</h4>
      </div>
    </div>
  </div>
  <a class="left carousel-control" style="font-size: 32px" href="#myCarousel" data-slide="prev"><div style="font-weight: bold; margin-top:4px;"><</div></a>
  <a class="right carousel-control" style="font-size: 32px" href="#myCarousel" data-slide="next"><div style="font-weight: bold; margin-top:4px;">></div></a>
</div>
<br /><br />
<!-- Example row of columns -->
<div class="row">
<!--   <div class="span4">
    <h2>Last news</h2>
    <div id="rss"></div>
    <p><a class="btn" href="#">View details &raquo;</a></p>
  </div>
 -->
   <div >
    <!-- Main hero unit for a primary marketing message or call to action -->
	<div class="hero-unit" style="padding: 25px">
	<h3>Fairness rating provides a strong and flexible rating software dedicated to banks and corporations.</h3><br/>
	<img style="float: left; margin-right: 20px;" src="<c:url value="/resources/img/home/screen.jpg"/>" />
	Our company cumulates more than 20 years of experience in the financial and banking industry (Investment banking, Private equity and commercial banking, etc.). <br />
	Fairness Rating financial world class rating tool GRM 500 is currently used by a leading CAC 40 company.<br /><br /><br /><br /><br />
	<div style="float: right; margin-top: -10px;"><a href="<c:url value="/solution"/>" class="btn btn-primary ">Learn more &raquo;</a></div>
	</div>
  </div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>