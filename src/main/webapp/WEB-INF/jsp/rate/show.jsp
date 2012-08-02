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
		$('#editNote').click(function () {
			$('#noteEdition').show();
			$('#noteDisplay').hide();
		});
		$('#editNoteCancel').click(function () {
			$('#noteEdition').hide();
			$('#noteDisplay').show();
		});
		$(".slider").each(function () {
			var el = $(this); 
			el.slider({ value:el.attr("data-score"),
				min: 0,
				max: el.attr("data-max-value"),
				disabled: true });
		});
	});
</script>

<div class="row show-grid">
	
    <div class="span6">
    
	<h2>Entreprise ${report.company.name} : <spring:message code="note.NOTE_FINALE_TXT.${report.company.score}"/></h2>
	<p>Identifiant : ${report.company.businessId}</p>
        <div data-max-value="19" data-score="<c:out value="${report.company.score}"/>" style="width: 400px" class="slider"></div>
    
	    <h3>Description</h3>
	    <c:choose>
			<c:when test="${empty report.company.description}">
				<em>Description not available</em>
			</c:when>
			<c:otherwise>
				<c:out value="${report.company.description}" />    
			</c:otherwise>
	    </c:choose>
		<br />
	    <h3>Shareholders</h3>
	    <c:choose>
			<c:when test="${empty report.company.shareholders}">
				<em>Data not available</em>
			</c:when>
			<c:otherwise>
			    <table class="smallPadding table table-striped">
				  <thead>
				    <tr>
				      <th>Firstname</th>
				      <th>Lastname</th>
				      <th>Percent</th>
				    </tr>
				  </thead>
				  <tbody>
				    <c:forEach var="sh" items="${report.company.shareholders}" varStatus="statusResultTypes">
				    <tr>
				      <td><c:out value="${sh.firstname}"/></td>
				      <td><c:out value="${sh.lastname}"/></td>
				      <td><c:out value="${sh.percent}"/> %</td>
				    </tr>
				    </c:forEach>
				  </tbody>
				</table>
			</c:otherwise>
		</c:choose>
		<br />
	    <h3>Managers</h3>
	    <c:choose>
			<c:when test="${empty report.company.managers}">
				<em>Data not available</em>
			</c:when>
			<c:otherwise>    
			    <table class="smallPadding table table-striped">
				  <thead>
				    <tr>
				      <th>Firstname</th>
				      <th>Lastname</th>
				      <th>Function</th>
				    </tr>
				  </thead>
				  <tbody>
				    <c:forEach var="manager" items="${report.company.managers}" varStatus="statusResultTypes">
				    <tr>
				      <td><c:out value="${manager.firstname}"/></td>
				      <td><c:out value="${manager.lastname}"/></td>
				      <td><c:out value="${manager.function}"/></td>
				    </tr>
				    </c:forEach>
				  </tbody>
				</table>
			</c:otherwise>
		</c:choose>    
		<br />
		<h3>Notes / conclusion</h3>
		<div id="noteEdition" style="display: none">
			<form method="post">
			    <textarea name="note" style="width: 550px" rows="10"><c:out value="${report.company.note}"/></textarea>
				<span id="editNoteCancel" class="btn"><i class="icon-remove"></i>Cancel</span>
				<button type="submit" class="btn btn-success"><i class="icon-ok"></i> Save notes</button>
			</form>
		</div>
		<div id="noteDisplay">
			<c:out value="${report.company.note}"/><br />
			<button id="editNote" type="submit" class="btn btn-primary"><i class="icon-pencil"></i> Edit notes</button>
		</div>
		<br />
		
		<h3>Factors</h3>
	    <c:forEach var="factor" items="${report.factors}" >
	       <h4 style="margin: 20px 0px 9px 0px"><spring:message code="factor.${factor.name}"/></h4>
		    <div data-max-value="7" data-score="<c:out value="${report.results[factor.name]}"/>" style="width: 400px" class="slider"></div>
	    </c:forEach>
		<br /><br />
		<h3>Informations</h3>
		<c:if test="${report.company.validated}">
			<div class="alert alert-success">
		        <button type="button" class="close" data-dismiss="alert">×</button>
		        This company has been validated.
	        </div>
		</c:if>
		<c:if test="${!report.company.validated}">
			<div class="alert">
		        <button type="button" class="close" data-dismiss="alert">×</button>
		        This company has not been validated yet.
	        </div>
        </c:if>
		Rated on <c:out value="${report.company.yearNumber}"/> years / <c:out value="${report.company.dayNumber}"/> days<br />
		
    </div>
    
    <div class="span6">
      <c:forEach var="reportMenu" items="${report.resultTypes}" varStatus="statusResultMenu">
	    <h3><spring:message code="menu.${reportMenu.key}"/></h3>
	    <table class="smallPadding table table-striped">
		  <thead>
		    <tr>
		      <th style="width:200px">US $m</th>
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
		      	<c:if test="${reportType.type.name == 'PERCENT'}">%</c:if>
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