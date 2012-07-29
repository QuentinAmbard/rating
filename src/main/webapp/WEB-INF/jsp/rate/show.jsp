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
    <div class="span6">
    
    </div>
    <div class="span6">
    	
      <c:forEach var="reportMenu" items="${report.resultTypes}" varStatus="statusResultMenu">
	    <spring:message code="menu.${reportMenu.key}"/>
	    <table class="table table-striped">
		  <thead>
		    <tr>
		      <th style="width:150px">#</th>
		      <c:forEach var="year" items="${years}" varStatus="statusYears"><th><c:out value="${year}"/></th></c:forEach>
		    </tr>
		  </thead>
		  <tbody>
		    <c:forEach var="reportType" items="${reportMenu.value}" varStatus="statusResultTypes">
		    <tr>
		      <td><spring:message code="result.${reportType.name}"/></td>
		      <c:forEach var="year" items="${years}" varStatus="statusYears">
		      <th>
		      	<c:out value="${report.results[reportType.name].values[year]}"/>
		      </th>
		      </c:forEach>
		    </tr>
		    </c:forEach>
		  </tbody>
		</table>
	  </c:forEach>
	</div>
</div>

      
<%@ include file="/WEB-INF/jsp/footer.jsp" %>