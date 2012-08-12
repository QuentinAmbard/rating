<%@ include file="/WEB-INF/jsp/header.jsp" %>
<script type="text/javascript">
	$(document).ready(function () {
		$("#formAdd").validate();
		$( "#creationDate" ).datepicker({
			dateFormat: "dd/mm/yy",
			changeMonth: true,
			changeYear: true
		});
		$('#save').click(function () {
			$('#next').val("false");
			$('#formAdd').submit();
		});
		$('._popover').popover();
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
	      <label class="control-label" for="annalistName">Analyst name *</label>
	      <div class="controls">
			<form:input path="annalistName" id="annalistName" cssClass="required"  cssErrorClass="error" />
	      </div>
	    </div>
	    <div class="control-group">
	      <label class="control-label" for="companyName">Company name *</label>
	      <div class="controls">
			<form:input path="name" id="companyName" cssClass="required"  cssErrorClass="error" />
	      </div>
	    </div>
	    <div class="control-group">
	      <label class="control-label" for="businessId">
	      	<span class="_popover" rel="popover" data-content="Enter the company identifier (rcs, siren etc.)" data-original-title="Identifier">
	      		Identifier <i class="icon-question-sign"></i>
	      	</span>
	      </label>
	      <div class="controls">
			<form:input path="businessId" id="businessId" cssErrorClass="error" />
	      </div>
	    </div>
	    <div class="control-group">
	      <label class="control-label" for="creationDate">Registration date (dd/mm/yyyy)</label>
	      <div class="controls">
			<form:input cssClass="required" path="creationDate" id="creationDate" cssErrorClass="error" />
	      </div>
	    </div>
	    <div class="control-group">
	      <label class="control-label" for="description">Company description</label>
	      <div class="controls">
			<form:textarea rows="7" cssStyle="width: 500px" path="description" id="description" cssErrorClass="error" ></form:textarea>
	      </div>
	    </div>
	  </div>
	  	  
	  <div class="span6">
	    <div class="control-group">
	      <label class="control-label" for="sector">
	      	<span class="_popover" rel="popover" data-content="Select the sector of the company you want to rate" data-original-title="Sector">
	      		Sector <i class="icon-question-sign"></i>
	      	</span>
	      </label>
		   <div class="controls">
		   <form:select id="sector" path="sectorId">
			  <c:forEach var="sector" items="${sectors}">
			    <form:option value="${sector.id}">
					<spring:message code="sector.${sector.name}"/>
			    </form:option>
			  </c:forEach>
			</form:select>
		   </div>
		 </div>
	  	 <div class="control-group">
	      <label class="control-label" for="dayNumber">
	      	<span class="_popover" rel="popover" data-content="The number of days in the year that will be used to rate the company." data-original-title="Numer of days">
	      		Number of days in the year <i class="icon-question-sign"></i>
	      	</span>
	      </label>
		   <div class="controls">
			<form:input path="dayNumber" id="dayNumber" cssErrorClass="error" />
	        <p class="help-block"></p>
		   </div>
		 </div>
	    <div class="control-group">
	      <label class="control-label" for="dayNumber">
	      	<span class="_popover" rel="popover" data-content="The number of years that will be used to rate the company." data-original-title="Number of years">
	      		Number of years <i class="icon-question-sign"></i>
	      	</span>
	      </label>
		   <div class="controls">
		   <form:select id="yearNumber" path="yearNumber">
			    <form:option value="1">1</form:option>
			    <form:option value="2">2</form:option>
			    <form:option value="3">3</form:option>
			    <form:option value="4">4</form:option>
			    <form:option value="5">5</form:option>
			</form:select>
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