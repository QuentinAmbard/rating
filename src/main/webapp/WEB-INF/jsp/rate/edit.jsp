<%@ include file="/WEB-INF/jsp/header.jsp" %>
  <script type="text/javascript">
	$(document).ready(function () {
		new RATING.validation();
		$("#editForm").validate();
		$('.datePicker').datepicker({dateFormat: "dd/mm/yy"});
		$('#save').click(function () {
			$('#next').val("false");
			$("#editForm").validate().cancelSubmit = true;
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
	      <th style="width: 250px">Nom</th>
	      <c:choose>
	      	<c:when test="${data.displayYear}">
		      <c:forEach var="year" items="${years}" varStatus="statusYears">
		      	<th ><c:out value="${year}"/></th>
		      </c:forEach>
	      	</c:when>
	      	<c:otherwise>
			    <th colspan="<c:out value="${fn:length(years)+1}"/>">
	      	</c:otherwise>
	      </c:choose>
	    </tr>
	  </thead>
	  <tbody>
		  <c:forEach var="item" items="${data.properties}">
		  	<c:if test="${!empty item.key.subMenu}">
			  	<tr>
			  		<td colspan="<c:out value="${fn:length(years)+1}"/>"><h4><spring:message code="submenu.${item.key.subMenu}"/></h4></td>
			    </tr>
		    </c:if>
		  	<tr <c:if test="${item.key.hidden}">style="display: none"</c:if>>
		    	<td>
		    	  <c:if test="${!empty item.key.sum}"><h5></c:if><spring:message code="rating.${item.key.name}.name"/> <c:if test="${item.key.type.name == 'TIME'}">(dd/mm/yyyy)</c:if> &nbsp;<em><spring:message code="rating.${item.key.name}.description"/></em><c:if test="${!empty item.key.sum}"></h5></c:if>
		    	</td>
		    	<c:choose>
			    	<c:when test="${item.key.global or item.key.type.name == 'RADIO'}">
			    	<td colspan="<c:out value="${fn:length(years)+1}"/>">
					    <c:choose>
		    	  			<c:when test="${item.key.type.name == 'RADIO'}">
					 		  <c:forEach var="val" items="${item.key.dropDownValues}" varStatus="status">
					 		     <input value="${status.index}" id="<c:out value="properties_${item.key.name}_${status.index}" />" type="radio" <c:if test="${(item.value == null and status.index==0 ) or item.value.globalValue == status.index}">checked="checked"</c:if> name="<c:out value="properties['${item.key.name}'].globalValue" />" >
					 		     <label style="cursor:pointer; display:inline" for="<c:out value="properties_${item.key.name}_${status.index}" />"><spring:message code="rating.${item.key.name}.${status.index}"/></label><br />
							  </c:forEach>
							</c:when>
							<c:otherwise>
								TODO
							</c:otherwise>
						</c:choose>
			    	</td>
			    	</c:when>
			    	<c:otherwise>
				 		<c:forEach var="year" items="${years}" varStatus="statusYears">
				 		  	<td>
						      <c:choose>
								 <c:when test="${item.key.type.name == 'DROP_DOWN'}">
								    <select id="sector" path="sector" name="<c:out value="properties['${item.key.name}'][${year}]" />">
							 		  <c:forEach var="val" items="${item.key.dropDownValues}" varStatus="status">
									    <option value="${status.index}" <c:if test="${item.value.values[year].value == status.index}">selected="selected"</c:if>>
				 							<spring:message code="rating.${item.key.name}.${status.index}"/>
									    </option>
									  </c:forEach>
									</select>
								  </c:when>
								 <c:otherwise>
									<input id="<c:out value="${item.key.name}_${year}" />" data-name="<c:out value="${item.key.name}" />" <c:if test="${!empty item.key.sum}">data-sum="<c:out value="${item.key.sum}" />" readonly="readonly"</c:if>type="text" data-year="<c:out value="${year}" />" data-validation="<c:out value="${item.key.validation}" />" value="<c:out value="${item.value.values[year].value}" />" name="<c:out value="properties['${item.key.name}'][${year}]" />" <c:if test="${item.key.type.name == 'TIME'}">class="datePicker"</c:if>/>
								 </c:otherwise>
							  </c:choose>
							</td>
						</c:forEach>
					</c:otherwise>
				</c:choose>
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