<jsp:include page="header.jsp"></jsp:include>
<%@taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                	<div class="label label-default pull-right">
                        id : ${ computerDTO.id }
                    </div>
                    <h1>
	                    <c:choose>
	                    	<c:when test="${ computerDTO.id == 0 }">
	                    		<spring:message code="computer.add"/>
	                    	</c:when>
	                    	<c:otherwise>
	                    		<spring:message code="computer.edit"/>
	                    	</c:otherwise>
                    	</c:choose>
                    </h1>

                    <form:form id="addComputer" action="editComputer" method="POST" modelAttribute="computerDTO">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName"><spring:message code="computer.name"/></label>
                                <form:input type="text" path="name" value="${ computerDTO.name }" class="form-control" id="name" name="name" />
                                <form:errors path="name" element="label" cssClass="error"/>
                            </div>
                            <div class="form-group">
                                <label for="introduced"><spring:message code="computer.introduced"/></label>
                                <form:input type="date" path="introduced" value="${ computerDTO.introduced }" class="form-control" id="introduced" name="introduced" />
                                <form:errors path="introduced" element="label" cssClass="error"/>
                            </div>
                            <div class="form-group">
                                <label for="discontinued"><spring:message code="computer.discontinued"/></label>
                                <form:input type="date" path="discontinued" value="${ computerDTO.discontinued }" class="form-control" id="discontinued" name="discontinued" />
                                <form:errors path="discontinued" element="label" cssClass="error"/>
                            </div>
                            <form:input type="hidden" path="id" value="${ computerDTO.id }" name="computerId" />
                            <div class="form-group">
                                <label for="companyId"><spring:message code="company.name"/></label>
                                <form:select path="companyId" class="form-control" id="companyId" name="companyId" >
								<form:option value="0" label="--"/>
									<form:options items="${ companiesDTO }" itemLabel="name" itemValue="id"></form:options>
								</form:select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="<spring:message code="form.addEdit"/>" class="btn btn-primary">
                            <spring:message code="or" />
                            <a href="/webapp/dashboard" class="btn btn-default"><spring:message code="form.cancel"/></a>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </section>
</body>
<script type="text/javascript">
	function getCookie(sName) {
		var cookContent = document.cookie, cookEnd, i, j;
		var sName = sName + "=";
		for (i=0, c=cookContent.length; i<c; i++) {
			j = i + sName.length;
			if (cookContent.substring(i, j) == sName) {
				cookEnd = cookContent.indexOf(";", j);
				if (cookEnd == -1) {
					cookEnd = cookContent.length;
				}
				return decodeURIComponent(cookContent.substring(j, cookEnd));
			}
		}       
		return null;
	}

  	var strings = new Array();
  	strings['form.check.name'] = '<spring:message code="NotEmpty.Computer.name" javaScriptEscape="true" />';
  	strings['form.check.date'] = '<spring:message code="DateTimeFormat.Computer.date" javaScriptEscape="true" />';
	if (getCookie("org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE") == "fr") {
		strings['form.regex'] = /^(0[1-9]|1\d|2\d|3[01])\/(0[1-9]|1[0-2])\/(19|20)\d{2}$/;
	} else {
		strings['form.regex'] = /^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/;
	}
</script>
<script src="${pageContext.request.contextPath}/static/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js/addComputerValidate.js"></script>
</html>