<!DOCTYPE html>
<%@taglib uri="/static/tag/linkTag.tld" prefix="l" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="static/css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="static/css/font-awesome.css" rel="stylesheet" media="screen">
<link href="static/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <l:link name="Application - Computer Database" target="dashboard" myClass="navbar-brand"/>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                	<div class="label label-default pull-right">
                        id : ${ computerDTO.id }
                    </div>
                    <h1>${ title }</h1>
                    
                    <c:forEach items="${ errors }" var="error">
	                    <div class="alert alert-danger">
						    <strong>${ error }</strong>
						</div>
                    </c:forEach>
                    
                   
                   	
                    <form id="addComputer" action="editComputer" method="POST">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" value="${ computerDTO.name }" class="form-control" id="computerName" name="computerName" placeholder="Computer name">
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" value="${ computerDTO.introduced }" class="form-control" id="introduced" name="introduced" placeholder="Introduced date">
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" value="${ computerDTO.discontinued }" class="form-control" id="discontinued" name="discontinued" placeholder="Discontinued date">
                            </div>
                            <input type="hidden" value="${ computerDTO.id }" name="computerId"/>
                            <div class="form-group">
                                <label for="companyId">Company</label>
                                <select class="form-control" id="companyId" name="companyId" >
                                	<option value="0">--</option>
                                	<c:forEach items="${ companiesDTO }" var="companyDTO">
                                    	<option value="${ companyDTO.id }" <c:if test="${companyDTO.id == computerDTO.companyId}">selected</c:if>>${ companyDTO.name }</option>
                                   	</c:forEach>
                                </select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Add/Edit" class="btn btn-primary">
                            or
                            <l:link name="Cancel" target="dashboard" myClass="btn btn-default"/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/jquery.validate.min.js"></script>
<script src="static/js/addComputerValidate.js"></script>
</html>