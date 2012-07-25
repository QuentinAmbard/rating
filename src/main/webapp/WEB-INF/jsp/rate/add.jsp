<%@ include file="/WEB-INF/jsp/header.jsp" %>
<script type="text/javascript">
	$(document).ready(function () {
		$( "#creationDate" ).datepicker({dateFormat: "dd/mm/yy"});
	});
</script>
	
<spring:bind path="command.*">
    <c:forEach items="${status.errorMessages}" var="error">
        <font color="red">Error code: <c:out value="${error}"/></font>
        <br><br>
    </c:forEach>
</spring:bind>

<form:form commandName="command"  method="POST">
  <fieldset>
    <legend>Company informations</legend>
    <div class="control-group">
      <label class="control-label" for="companyName">Company name *</label>
      <div class="controls">
		<form:input path="name" cssErrorClass="error" />
        <p class="help-block">enter the company name</p>
      </div>
    </div>
    <div class="control-group">
      <label class="control-label" for="businessId">Identifier</label>
      <div class="controls">
		<form:input path="businessId" id="businessId" cssErrorClass="error" />
        <p class="help-block">enter the company identifier (rcs, siren etc.)</p>
      </div>
    </div>
    <div class="control-group">
      <label class="control-label" for="creationDate">Creation date (dd/mm/yyyy)</label>
      <div class="controls">
		<form:input path="creationDate" id="creationDate" cssErrorClass="error" />
        <p class="help-block">enter the company registration date</p>
      </div>
    </div>
    <div class="control-group">
      <label class="control-label" for="leader">Leader</label>
      <div class="controls">
		<form:input path="leaderName" id="leader" cssErrorClass="error" />
        <p class="help-block">enter the leader name</p>
      </div>
    </div>
    <div class="control-group">
	   <label class="control-label" for="sector">Select a sector</label>
	   <div class="controls">
	   
	   <form:select id="sector" path="sector">
		  <c:forEach var="lang" items="${sectors}">
		    <form:option value="${lang}">
				<spring:message code="enum.sector.${lang}"/>
		    </form:option>
		  </c:forEach>
		</form:select>
		
	   </div>
	 </div>
	 <div class="form-actions">
		<button type="submit" class="btn">Cancel</button>
        <button type="submit" class="btn btn-primary">Save work in progress</button>
        <button type="submit" class="btn btn-success">Save and Go to the next step</button>
     </div>
  </fieldset>
</form:form> 

<%@ include file="/WEB-INF/jsp/footer.jsp" %>