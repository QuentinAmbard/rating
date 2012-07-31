<%@ include file="/WEB-INF/jsp/header.jsp" %>
  <script type="text/javascript">
	$(document).ready(function () {
		new RATING.validation();
		$("#formAdd").validate();
		$('.datePicker').datepicker({dateFormat: "dd/mm/yy"});
		$('#save').click(function () {
			$('#next').val("false");
			$('#editForm').submit();
		});
	});
</script>

<div class="row show-grid">
	<h2>Entreprise ${report.company.name} : ${report.company.score}</h2>
	<p>Identifiant : ${report.company.businessId}</p>
	
    <div class="span6">
    blabla share holder<br />
    blabla manager<br />
    blabla blabla<br />
     &nbsp;
    </div>
    <div class="span6">
    	
      <c:forEach var="reportMenu" items="${report.resultTypes}" varStatus="statusResultMenu">
	    <h3><spring:message code="menu.${reportMenu.key}"/></h3>
	    <table class="table table-striped">
		  <thead>
		    <tr>
		      <th style="width:200px"></th>
		      <c:forEach var="year" items="${years}" varStatus="statusYears"><th><c:out value="${year}"/></th></c:forEach>
		    </tr>
		  </thead>
		  <tbody>
		    <c:forEach var="reportType" items="${reportMenu.value}" varStatus="statusResultTypes">
		    <tr>
		      <td><spring:message code="result.${reportType.name}"/></td>
		      <c:forEach var="year" items="${years}" varStatus="statusYears">
		      <th>
			    <fmt:formatNumber var="val" maxFractionDigits="2"><c:out value="${report.results[reportType.name].values[year]}"/></fmt:formatNumber>
		      	<c:out value="${val}"/>
		      </th>
		      </c:forEach>
		    </tr>
		    </c:forEach>
		  </tbody>
		</table>
	  </c:forEach>
	</div>
</div>

<sec:authorize ifAllGranted="ROLE_SUPER_ADMIN">
<c:forEach var="resutValue" items="${report.results}">
	<c:out value="${resutValue.key}"/> => <c:out value="${resutValue.value}"/><br />
</c:forEach>
</sec:authorize>
      
<%@ include file="/WEB-INF/jsp/footer.jsp" %>