<%@ include file="/WEB-INF/jsp/header.jsp" %>
<script type="text/javascript">
	$(document).ready(function () {
	});
</script>

<h2>Entreprises notée</h2>
<p>Voici la liste de vos entreprise notées ou en cours de notation.</p>

<form class="well form-search" action="<c:url value="/rate/search"/>" method="GET">
  Search company by name <input name="companyName" type="text" class="input-medium search-query">
  identifier <input name="companyBusinessId" type="text" class="input-medium search-query">
  <button type="submit" class="btn">Search</button>
</form>

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Nom</th>
      <th style="width: 130px">Identifiant</th>
      <th style="width: 100px">Date de création</th>
      <th>Secteur</th>
      <th style="width: 100px">Noté sur</th>
      <th>Score</th>
      <th style="width: 270px">Actions</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach var="company" items="${companies}">
	    <tr>
	      <td><c:out value="${company.name}"/></td>
	      <td><c:out value="${company.businessId}"/></td>
	      <td><c:out value="${company.creationDate}"/></td>
	      <td><spring:message code="enum.sector.${company.sector.name}"/></td>
	      <td><c:out value="${company.yearNumber}"/> ans / <c:out value="${company.dayNumber}"/> days </td>
	      <td><c:out value="${company.score}"/></td>
	      <td><a class="btn btn-primary" href="<c:url value="/rate/add/${company.id}"/>"><i class="icon-pencil"></i> Edit</a> 
	      <a class="btn btn-success" href="<c:url value="/rate/show/${company.id}"/>"><i class="icon-eye-open"></i> View details</a> 
	      <a class="btn btn-danger" href="<c:url value="/rate/delete/${company.id}"/>"><i class="icon-trash"></i>Delete</a></td>
	    </tr>
	</c:forEach>
  </tbody>
</table>


<%@ include file="/WEB-INF/jsp/footer.jsp" %>