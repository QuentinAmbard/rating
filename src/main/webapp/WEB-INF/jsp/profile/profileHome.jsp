<%@ include file="/WEB-INF/jsp/header.jsp" %>


<div class="row">
	<h3>Profile</h3>
	<p>Change your profile settings here</p>
	<spring:hasBindErrors name="passwordCommand">
	   <ul>
	      <c:forEach var="error" items="${errors.allErrors}">
	      	<div class="alert alert-error">
		        <button type="button" class="close" data-dismiss="alert">x</button>
	          	<spring:message code="${error.code}" arguments="${error.field}" />
	         </div>
	      </c:forEach>
	   </ul>
	</spring:hasBindErrors>
	<spring:hasBindErrors name="userCommand">
	   <ul>
	      <c:forEach var="error" items="${errors.allErrors}">
	      	<div class="alert alert-error">
		        <button type="button" class="close" data-dismiss="alert">x</button>
	          	<spring:message code="${error.code}" arguments="${error.field}" />
	         </div>
	      </c:forEach>
	   </ul>
	</spring:hasBindErrors>
	<div class="span6">
	<c:url var="url" value="/profile/edit"/>
	<form:form cssClass="well" commandName="userCommand" id="formUser" action="${url}" method="POST">
		<label>Firstname</label>
		<form:input path="firstname" cssClass="required" cssErrorClass="error" />
		<label>Lastname</label>
		<form:input path="lastname" cssClass="required" cssErrorClass="error" />
		<label>Email</label>
		<form:input path="email" cssClass="required" cssErrorClass="error" />
		<button type="submit" class="btn btn-success">Change profile</button>
	</form:form>
	</div>
	<div class="span6">
	<c:url var="url" value="/profile/password"/>
	<form:form cssClass="well" commandName="passwordCommand" id="formPassword" action="${url}" method="POST">
		<label>Old password</label>
		<form:password path="oldPassword" cssClass="required" cssErrorClass="error" />
		<label>New password </label>
		<form:password path="password" cssClass="required" cssErrorClass="error" />
		<label>Password confirmation</label>
		<form:password path="password2" cssClass="required" cssErrorClass="error" />
		<button type="submit" class="btn btn-success">Change password</button>
	</form:form>
	</div>
</div>
  
<%@ include file="/WEB-INF/jsp/footer.jsp" %>