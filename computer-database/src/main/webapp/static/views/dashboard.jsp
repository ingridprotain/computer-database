<!DOCTYPE html>
<%@taglib uri="/static/tag/paginationTag.tld" prefix="p" %>
<%@taglib uri="/static/tag/linkTag.tld" prefix="l" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
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
            <h1 id="homeTitle">
                <c:out value="${totalComputers}" /> Computers found
            </h1>
             <c:if test="${ not empty message }">
			            <div class="alert alert-success">
			            	<strong>${ message }</strong>
			            </div>
			        </c:if>
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="/computer-database/dashboard" method="GET" class="form-inline">
                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" value="${ search }"/>
                        <input type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary" />
                    </form>
                </div>
                <div class="pull-right">
                	<l:link target="editComputer" name="Add Computer" myClass="btn btn-success"/>
                    <a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();">Edit</a>
                </div>
            </div>
        </div>

        <form id="deleteForm" action="deleteComputers" method="POST">
            <input type="hidden" name="selection" value="${ computer.id }">
        </form>

        <div class="container" style="margin-top: 10px;">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <!-- Variable declarations for passing labels as parameters -->
                        <!-- Table header for Computer Name -->

                        <th class="editMode" style="width: 60px; height: 22px;">
                            <input type="checkbox" id="selectall" /> 
                            <span style="vertical-align: top;">
                                 -  <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                                        <i class="fa fa-trash-o fa-lg"></i>
                                    </a>
                            </span>
                        </th>
                        <th>
                            Computer name
                            <p:pagination name="&uarr;" target="dashboard" page="${ actualPage }" search="${ search }" orderBy="ASC"/>
                            <p:pagination name="&darr;" target="dashboard" page="${ actualPage }" search="${ search }" orderBy="DESC"/>
                        </th>
                        <th>
                            Introduced date
                        </th>
                        <!-- Table header for Discontinued Date -->
                        <th>
                            Discontinued date
                        </th>
                        <!-- Table header for Company -->
                        <th>
                            Company
                        </th>

                    </tr>
                </thead>
                <!-- Browse attribute computers -->
                <tbody id="results">
	                <c:forEach items="${ computers }" var="computer">
	                	<tr>
	                		<td class="editMode">
	                            <input type="checkbox" name="cb" class="cb" value="${ computer.id }">
	                        </td>
	                        <td>
	                        	<l:link name="${ computer.name }" target="editComputer" computerId="${ computer.id }"/>
	                        </td>
	                        <td>${ computer.introduced }</td>
	                        <td>${ computer.discontinued }</td>
	                        <td>${ computer.companyName }</td>
	                	</tr>
	                </c:forEach>
                </tbody>
            </table>
        </div>
    </section>

    <footer class="navbar-fixed-bottom">
        <div class="container text-center">
            <ul class="pagination">
          		<li><p:pagination name="&laquo;" target="dashboard" page="first" search="${ search }"/></li>
          		<li><c:if test="${ (actualPage -2) > 0 }"><p:pagination name="${ actualPage -2 }" target="dashboard" page="${ actualPage -2 }" search="${ search }"/></c:if></li>
          		<li><c:if test="${ (actualPage -1) > 0 }"><p:pagination name="${ actualPage -1 }" target="dashboard" page="prev" search="${ search }"/></c:if></li>
              	<li><p:pagination name="${ actualPage }" target="dashboard" page="${ actualPage }" search="${ search }"/></li>
              	<li><c:if test="${ (actualPage + 1) < totalPages }"><p:pagination name="${ actualPage + 1 }" target="dashboard" page="next" search="${ search }"/></c:if></li>
             	<li><c:if test="${ (actualPage + 2) < totalPages }"><p:pagination name="${ actualPage + 2 }" target="dashboard" page="${ actualPage + 2 }" search="${ search }"/></c:if></li>
             	<li><p:pagination name="&raquo;" target="dashboard" page="last" search="${ search }"/></li>
           	</ul>

	        <div class="btn-group btn-group-sm pull-right" role="group" >
	        	<p:pagination name="10" target="dashboard" page="${ actualPage }" search="${ search }" limit="10" myClass="btn btn-default"/>
	        	<p:pagination name="50" target="dashboard" page="${ actualPage }" search="${ search }" limit="50" myClass="btn btn-default"/>
	        	<p:pagination name="100" target="dashboard" page="${ actualPage }" search="${ search }" limit="100" myClass="btn btn-default"/>
	        </div>
        </div>
    </footer>
</body>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/dashboard.js"></script>
</html>