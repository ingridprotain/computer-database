<jsp:include page="header.jsp"></jsp:include>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="my"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<section id="main">
	<div class="container">
		<div class="row">
			<div class="col-xs-8 col-xs-offset-2 box">
				<h1 id="homeTitle">
					<spring:message code="login.title"></spring:message>
				</h1>

				<c:if test="${not empty error}">
					<div class="alert alert-danger"><spring:message
									code="${error}" /></div>
				</c:if>

				<form name='loginForm' action="<c:url value='j_spring_security_check' />" method='POST'>
					<fieldset>
						<div class="form-group">
							<label for="username"><spring:message
									code="login.username" /></label>
							<input type='text' name='username' class="form-control" />
						</div>
	
						<div class="form-group">
							<label for="password"><spring:message
									code="login.password" /></label>
							<input type='password' name='password' class="form-control" />
						</div>
					</fieldset>
					<div class="actions pull-right">
						<input type="submit" value="<spring:message code="login.submit"/>"
							class="btn btn-primary" />
					</div>
	
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
				</form>
			</div>
		</div>
	</div>
</section>
</body>
</html>