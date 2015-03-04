<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="my" %>
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
        	<my:link name="Application - Computer Database" target="dashboard" myClass="navbar-brand"/>
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
                        <my:link name="x" target="dashboard" myClass="btn btn-default"/>
                    </form>
                    
                </div>
                <div class="pull-right">
                	<my:link target="editComputer" name="Add Computer" myClass="btn btn-success"/>
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
                            <my:pagination isOrderBy="ok"></my:pagination>
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
	                        	<my:link name="${ computer.name }" target="editComputer" computerId="${ computer.id }"/>
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
        <my:pagination />
    </footer>
</body>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/dashboard.js"></script>
</html>