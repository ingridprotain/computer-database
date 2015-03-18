<jsp:include page="header.jsp"></jsp:include>
<%@taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
                    
                    <c:forEach items="${ errors }" var="error">
	                    <div class="alert alert-danger">
						    <strong>${ error }</strong>
						</div>
                    </c:forEach>

                    <form id="addComputer" action="editComputer" method="POST">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName"><spring:message code="computer.name"/></label>
                                <input type="text" value="${ computerDTO.name }" class="form-control" id="computerName" name="computerName" placeholder="<spring:message code="computer.name"/>">
                            </div>
                            <div class="form-group">
                                <label for="introduced"><spring:message code="computer.introduced"/></label>
                                <input type="date" value="${ computerDTO.introduced }" class="form-control" id="introduced" name="introduced" placeholder="<spring:message code="computer.introduced"/>">
                            </div>
                            <div class="form-group">
                                <label for="discontinued"><spring:message code="computer.discontinued"/></label>
                                <input type="date" value="${ computerDTO.discontinued }" class="form-control" id="discontinued" name="discontinued" placeholder="<spring:message code="computer.discontinued"/>">
                            </div>
                            <input type="hidden" value="${ computerDTO.id }" name="computerId"/>
                            <div class="form-group">
                                <label for="companyId"><spring:message code="company.name"/></label>
                                <select class="form-control" id="companyId" name="companyId" >
                                	<option value="0">--</option>
                                	<c:forEach items="${ companiesDTO }" var="companyDTO">
                                    	<option value="${ companyDTO.id }" <c:if test="${companyDTO.id == computerDTO.companyId}">selected</c:if>>${ companyDTO.name }</option>
                                   	</c:forEach>
                                </select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="<spring:message code="form.addEdit"/>" class="btn btn-primary">
                            or
                            <a href="/computer-database/dashboard" class="btn btn-default"><spring:message code="form.cancel"/></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
<script type="text/javascript">
  var strings = new Array();
  strings['form.check.name'] = '<spring:message code="form.check.name" javaScriptEscape="true" />';
  strings['form.check.date'] = '<spring:message code="form.check.date" javaScriptEscape="true" />';
</script>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/jquery.validate.min.js"></script>
<script src="static/js/addComputerValidate.js"></script>
</html>