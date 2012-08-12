<%@ include file="/WEB-INF/jsp/header.jsp" %>

<div class="span12 rateHome">
  <h2>Dashboard</h2>
  <p>Choose one of the three following actions.</p>
  <ul class="thumbnails">
    <li class="span3">
      <div class="thumbnail">
        <a href="<c:url value="./add"/>"><img src="<c:url value="/resources/img/rate/edit.png"/>" alt=""></a>
        <div class="caption">
          <h5>Rate a new company</h5>
          <p class="desc">Create a new company. You will be able to save the progression any time you need, and go back to change data.</p>
          <p><a href="<c:url value="./add"/>" class="btn btn-primary">Create a new company</a></p>
        </div>
      </div>
    </li>
    <li class="span3">
      <div class="thumbnail">
        <a href="<c:url value="./view"/>"><img src="<c:url value="/resources/img/rate/list.png"/>" alt=""></a>
        <div class="caption"">
          <h5>Registred companies</h5>
          <p class="desc">Use this menu to visualize your ratings, or to modify and validate already created companies..</p>
          <p><a href="<c:url value="./view"/>" class="btn btn-primary">See registred companies</a></p>
        </div>
      </div>
    </li>
    <li class="span3">
      <div class="thumbnail">
        <a href="#"><img src="<c:url value="/resources/img/rate/stat.png"/>" alt=""></a>
        <div class="caption">
          <h5>Statistics</h5>
          <p class="desc">Compare parameters and stats bewteen registred companies.</p>
          <p><a href="#" class="btn">Display statistics</a></p>
        </div>
      </div>
    </li>
  </ul>
</div>
    
<%@ include file="/WEB-INF/jsp/footer.jsp" %>