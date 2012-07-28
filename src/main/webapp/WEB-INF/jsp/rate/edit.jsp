<%@ include file="/WEB-INF/jsp/header.jsp" %>
<script type="text/javascript">
	$(document).ready(function () {
		$('.datePicker').datepicker({dateFormat: "dd/mm/yy"});
	});
</script>
	
<%-- <spring:bind path="command.*">
    <c:forEach items="${status.errorMessages}" var="error">
        <font color="red">Error code: <c:out value="${error}"/></font>
        <br><br>
    </c:forEach>
</spring:bind> --%>

    
<form method="POST">
  <fieldset>
    <legend><spring:message code="enum.step.${step}"/></legend>
    <c:forEach var="item" items="${data.properties}">
	    <div class="control-group">
	      <label class="control-label" for="companyName"><spring:message code="rating.${item.key.name}.name"/> <c:if test="${item.key.type.name == 'TIME'}">(dd/mm/yyyy)</c:if></label>
	      <div class="controls">
	 		  <c:forEach var="year" items="${years}" varStatus="statusYears">
				 <c:out value="${year}" /> :
			     <c:choose>
					 <c:when test="${item.key.type.name == 'DROP_DOWN'}">
					    <select id="sector" path="sector" name="<c:out value="properties['${item.key.name}'][${year}]" />">
				 		  <c:forEach var="lang" items="${item.key.dropDownValues}" varStatus="status">
						    <option value="${status.index}" <c:if test="${item.value.values[year].value == status.index}">selected="selected"</c:if>>
	 							<spring:message code="rating.${item.key.name}.${status.index}"/>
						    </option>
						  </c:forEach>
						</select>
					  </c:when>
					  <c:otherwise>
						<input type="text" value="<c:out value="${item.value.values[year].value}" />" name="<c:out value="properties['${item.key.name}'][${year}]" />" <c:if test="${item.key.type.name == 'TIME'}">class="datePicker"</c:if>/>
					  </c:otherwise>
					</c:choose>
				</c:forEach>
				<p class="help-block"><spring:message code="rating.${item.key.name}.description"/></p>
		    </div>
	    </div>
 	</c:forEach>
 	 <div class="form-actions">
		<button type="submit" class="btn">Cancel</button>
        <button type="submit" class="btn btn-primary">Save work in progress</button>
        <button type="submit" class="btn btn-success">Save and Go to the next step</button>
     </div>
  </fieldset>
</form> 

<%@ include file="/WEB-INF/jsp/footer.jsp" %>