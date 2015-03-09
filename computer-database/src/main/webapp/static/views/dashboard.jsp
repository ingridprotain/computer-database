<jsp:include page="header.jsp"></jsp:include>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="my" %>

    <section id="main">
        <div class="container">
            <h1 id="homeTitle">
                <c:out value="${count}" /> Computers found
            </h1>
            
            <div id="actions" class="form-horizontal">
                <div class="pull-left">
                    <form id="searchForm" action="/computer-database/dashboard" method="GET" class="form-inline">
                        <input type="search" id="searchbox" name="search" class="form-control" placeholder="Search name" value="${ page.search }"/>
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
                            <my:pagination page="${ page }" isOrderBy="true"/>
                        </th>
                        <th>
                            Introduced date
                        </th>
                        <th>
                            Discontinued date
                        </th>
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
        <my:pagination page="${ page }"/>
    </footer>
</body>
<script src="static/js/jquery.min.js"></script>
<script src="static/js/bootstrap.min.js"></script>
<script src="static/js/dashboard.js"></script>
</html>