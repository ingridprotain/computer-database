<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ attribute name="isOrderBy" required="false" %>
<%@ attribute name="orderByColumn" required="false" %>
<c:if test="${ empty isOrderBy }">
	<div class="container text-center">
		<ul class="pagination">
			<li><a href="/computer-database/dashboard?page=first<c:if test="${ not empty search }">&search=${ search }</c:if>">&laquo;</a></li>
			<c:if test="${ (actualPage -2) > 0 }">
				<li><a href="/computer-database/dashboard?page=${ actualPage -2 }<c:if test="${ not empty search }">&search=${ search }</c:if>">${ actualPage -2 }</a></li>
			</c:if>
			<c:if test="${ (actualPage -1) > 0 }">
				<li><a href="/computer-database/dashboard?page=${ actualPage -1 }<c:if test="${ not empty search }">&search=${ search }</c:if>">${ actualPage -1 }</a></li>
			</c:if>
			<li><a href="/computer-database/dashboard?page=${ actualPage }<c:if test="${ not empty search }">&search=${ search }</c:if>">${ actualPage }</a></li>
			<c:if test="${ (actualPage + 1) < totalPages }">
				<li><a href="/computer-database/dashboard?page=${ actualPage +1 }<c:if test="${ not empty search }">&search=${ search }</c:if>">${ actualPage +1 }</a></li>
			</c:if>
			<c:if test="${ (actualPage + 2) < totalPages }">
				<li><a href="/computer-database/dashboard?page=${ actualPage +2 }<c:if test="${ not empty search }">&search=${ search }</c:if>">${ actualPage +2 }</a></li>
			</c:if>
			<li><a href="/computer-database/dashboard?page=last<c:if test="${ not empty search }">&search=${ search }</c:if>">&raquo;</a></li>
		</ul>
	
		<div class="btn-group btn-group-sm pull-right" role="group" >
			<a class="btn btn-default" href="/computer-database/dashboard?limit=10<c:if test="${ not empty search }">&search=${ search }</c:if>">10</a>
			<a class="btn btn-default" href="/computer-database/dashboard?limit=50<c:if test="${ not empty search }">&search=${ search }</c:if>">50</a>
			<a class="btn btn-default" href="/computer-database/dashboard?limit=100<c:if test="${ not empty search }">&search=${ search }</c:if>">100</a>
	 	</div>
	</div>
</c:if>

<c:if test="${ not empty isOrderBy }">
	<a title="Croissant" href="/computer-database/dashboard?page=${ actualPage }<c:if test="${ not empty search }">&search=${ search }</c:if>&orderBy=ASC<c:if test="${ not empty orderByColumn }">&orderByColumn=${ orderByColumn }</c:if>">&uarr;</a>
	<a title="D�croissant" href="/computer-database/dashboard?page=${ actualPage }<c:if test="${ not empty search }">&search=${ search }</c:if>&orderBy=DESC<c:if test="${ not empty orderByColumn }">&orderByColumn=${ orderByColumn }</c:if>">&darr;</a>
</c:if>