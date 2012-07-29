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

<form id="editForm" method="POST">
  <input id="next" name="next" type="hidden" value="true"> 
  <fieldset>
    <legend><spring:message code="enum.step.${step}"/></legend>
    
    <table class="table table-bordered">
	  <thead>
	    <tr>
	      <th>Nom</th>
	      <c:forEach var="year" items="${years}" varStatus="statusYears">
	      	<th style="width: 170px"><c:out value="${year}"/></th>
	      </c:forEach>
	    </tr>
	  </thead>
	  <tbody>
		  <c:forEach var="item" items="${data.properties}">
			  	<c:if test="${!empty item.key.subMenu}">
				  	<tr>
				  		<td colspan="<c:out value="${fn:length(years)+1}"/>"><h4><spring:message code="submenu.${item.key.subMenu}"/></h4></td>
				    <tr>
			    </c:if>
		    	<td><spring:message code="rating.${item.key.name}.name"/> <c:if test="${item.key.type.name == 'TIME'}">(dd/mm/yyyy)</c:if> &nbsp;<em><spring:message code="rating.${item.key.name}.description"/></em></td>
		 		  <c:forEach var="year" items="${years}" varStatus="statusYears">
		 		  	<td>
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
							<input id="<c:out value="${item.key.name}_${year}" />" data-name="<c:out value="${item.key.name}" />" <c:if test="${!empty item.key.sum}">data-sum="<c:out value="${item.key.sum}" />" disabled="disabled"</c:if>type="text" data-year="<c:out value="${year}" />" data-validation="<c:out value="${item.key.validation}" />" value="<c:out value="${item.value.values[year].value}" />" name="<c:out value="properties['${item.key.name}'][${year}]" />" <c:if test="${item.key.type.name == 'TIME'}">class="datePicker"</c:if>/>
						  </c:otherwise>
					</c:choose>
					</td>
				</c:forEach>
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
</form> 

<%@ include file="/WEB-INF/jsp/footer.jsp" %>