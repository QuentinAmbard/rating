<%@ include file="/WEB-INF/jsp/header.jsp" %>
<script type="text/javascript">
	$(document).ready(function () {
		$("#formAdd").validate();
		$( "#creationDate" ).datepicker({dateFormat: "dd/mm/yy"});
		$('#save').click(function () {
			$('#next').val("false");
			$('#formAdd').submit();
		});
	});
</script>
	
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


<c:url var="url" value="/rate/add"/>
<form:form commandName="command" id="formAdd" action="${url}" method="POST">
  <input id="next" name="next" type="hidden" value="true"> 
  <form:hidden path="id" />
  <fieldset>
    <legend>Company informations</legend>
    <div class="row show-grid">
	  <div class="span6">
	    <div class="control-group">
	      <label class="control-label" for="companyName">Company name *</label>
	      <div class="controls">
			<form:input path="name" cssClass="required"  cssErrorClass="error" />
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
	  </div>
	  	  
	  <div class="span6">
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
	  	 <div class="control-group">
		   <label class="control-label" for="dayNumber">Number of days in the year</label>
		   <div class="controls">
			<form:input path="dayNumber" id="dayNumber" cssErrorClass="error" />
	        <p class="help-block">The number of days in the year</p>
		   </div>
		 </div>
	    <div class="control-group">
		   <label class="control-label" for="yearNumber">Number of years</label>
		   <div class="controls">
		   <form:select id="yearNumber" path="yearNumber">
			    <form:option value="1">1</form:option>
			    <form:option value="2">2</form:option>
			    <form:option value="3">3</form:option>
			    <form:option value="4">4</form:option>
			    <form:option value="5">5</form:option>
			</form:select>
	        <p class="help-block">The number of years you have data</p>
		   </div>
		 </div>
	  </div>
	</div>
   
	 <div class="form-actions">
		<a type="submit" class="btn" href="<c:url value="/rate/home"/>"><i class="icon-remove"></i>Cancel</a>
        <span id="save" class="btn btn-primary"><i class="icon-hdd"></i> Save work in progress</span>
        <button type="submit" class="btn btn-success"><i class="icon-ok"></i> Save and Go to the next step</button>
     </div>
  </fieldset>
</form:form> 

<%@ include file="/WEB-INF/jsp/footer.jsp" %>