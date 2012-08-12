<%@ include file="/WEB-INF/jsp/header.jsp" %>
<script type="text/javascript">
	$(document).ready(function () {
		$('._deleteCompany').click(function () {
			$('#myModal').modal('show');
			$('#deleteButton').attr("href", $(this).attr("data-href"));
		})
		$('._validate').change(function () {
			$.ajax({
			  url: '<c:url value="/rate/view/activation/"/>'+$(this).attr("data-id"),
			  type: "POST"
			});
			$("#edit"+$(this).attr("data-id")).toggle();
		});
	});
</script>

<h2>Registred companies</h2>
<p>Here is the list of your registred companies.</p>

<form class="well form-search" action="<c:url value="/rate/search"/>" method="GET">
  Search company by name <input name="companyName" type="text" class="input-medium search-query">
  identifier <input name="companyBusinessId" type="text" class="input-medium search-query">
  <button type="submit" class="btn">Search</button>
</form>

<table class="table table-bordered">
  <thead>
    <tr>
      <th>Nom</th>
      <th style="width: 130px">Identifier</th>
      <th style="width: 100px">Registration date</th>
      <th>Secteur</th>
      <th style="width: 100px">Validation</th>
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
	      <td><spring:message code="sector.${company.sector.name}"/></td>
	      <td>
		      <c:if test="${company.validated}">
			   	<input class="_validate" data-id="<c:out value="${company.id}"/>" id="validate<c:out value="${company.id}"/>" type="checkbox" checked="checked" /> <label style="cursor: pointer; display: inline" for="validate<c:out value="${company.id}"/>">Validated</label>
		      </c:if>
		      <c:if test="${!company.validated}">
			   	<input class="_validate" data-id="<c:out value="${company.id}"/>" id="validate<c:out value="${company.id}"/>" type="checkbox" /> <label style="cursor: pointer; display: inline" for="validate<c:out value="${company.id}"/>">Validate</label>
		      </c:if>
	      </td>
	      <td>
	       <c:choose>
			<c:when test="${empty company.score}">
				-
			</c:when>
			<c:otherwise>
				<spring:message code="note.NOTE_FINALE_TXT.${company.score}"/>
			</c:otherwise>
		    </c:choose>
		  </td>
	      <td>
			  <a class="btn btn-primary" id="edit<c:out value="${company.id}"/>" <c:if test="${company.validated}">style="display: none"</c:if>href="<c:url value="/rate/add/${company.id}"/>"><i class="icon-pencil"></i> Edit</a> 
		      <a class="btn btn-success" href="<c:url value="/rate/show/${company.id}"/>"><i class="icon-eye-open"></i> View details</a> 
	 	      <span data-href="<c:url value="/rate/delete/${company.id}"/>" class="_deleteCompany btn btn-danger"><i class="icon-trash"></i>Delete</span>
 	      </td> 
	    </tr>
	</c:forEach>
  </tbody>
</table>


<div class="modal hide" id="myModal">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal">×</button>
    <h3>Company suppression</h3>
  </div>
  <div class="modal-body">
    <p>Do you really want to delete this company ? All data will be lost.</p>
  </div>
  <div class="modal-footer">
    <a href="#" class="btn" data-dismiss="modal">Cancel</a>
    <a id="deleteButton" class="btn btn-danger" href="">Delete</a>
  </div>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp" %>