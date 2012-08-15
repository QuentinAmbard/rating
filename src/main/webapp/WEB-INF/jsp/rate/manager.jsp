<%@ include file="/WEB-INF/jsp/header.jsp" %>
<script type="text/javascript">
	$(document).ready(function () {
		$('#save').click(function () {
			$('#next').val("false");
			$('#formAdd').submit();
		});
		$('._popover').popover();
	});
</script>
<div class="subnav">
  <ul class="nav nav-pills">
    <li class=""><a href="<c:url value="/rate/add/${companyId}"/>">Creation</a></li>
    <li class=""><a href="<c:url value="/rate/shareholder/${companyId}"/>">Shareholders</a></li>
    <li class=""><a href="<c:url value="/rate/manager/${companyId}"/>">Managers</a></li>
    <c:forEach var="step" items="${steps}" varStatus="status" >
	    <li class=""><a href="<c:url value="/rate/edit/${companyId}/${step.id}"/>">Step <c:out value="${status.index+1}" /></a></li>
    </c:forEach>
  </ul>
</div>
<spring:hasBindErrors name="command">
   <ul>
      <c:forEach var="error" items="${errors.allErrors}">
      	<div class="alert alert-error">
	        <button type="button" class="close" data-dismiss="alert">x</button>
          	<spring:message code="${error.code}" arguments="${error.field}" />
         </div>
      </c:forEach>
   </ul>
</spring:hasBindErrors>


<c:url var="url" value="/rate/manager/${companyId}"/>
<form:form commandName="command" id="formAdd" action="${url}" method="POST">
  <form:hidden id="next" path="next" value="true" /> 
  <fieldset>
    <legend>Company managers</legend>
    <table class="table table-bordered">
	  <thead>
	    <tr>
	      <th>Manager firstname</th>
	      <th>Manager lastname</th>
	      <th>Manager function</th>
	    </tr>
	  </thead>
	  <tbody>
	  <c:forEach var="shareholder" items="${command.managers}" varStatus="status">
	    <tr>
			<td><form:input cssStyle="width: 300px" path="managers[${status.index}].firstname" cssErrorClass="error" /></td>
			<td><form:input cssStyle="width: 300px" path="managers[${status.index}].lastname" cssErrorClass="error" /></td>
			<td><form:input cssStyle="width: 300px" path="managers[${status.index}].function" cssErrorClass="error" /></td>
   		</tr>
   	  </c:forEach>
   	 </tbody>
   	</table>
	 <div class="form-actions">
		<a type="submit" class="btn" href="<c:url value="/rate/home"/>"><i class="icon-remove"></i>Cancel</a>
        <span id="save" class="btn btn-primary"><i class="icon-hdd"></i> Save work in progress</span>
        <button type="submit" class="btn btn-success"><i class="icon-ok"></i> Save and Go to the next step</button>
     </div>
  </fieldset>
</form:form> 

<%@ include file="/WEB-INF/jsp/footer.jsp" %>