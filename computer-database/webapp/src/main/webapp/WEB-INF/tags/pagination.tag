<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute type="com.excilys.computerdatabase.utils.Pages"
	name="pagination" required="true"%>
<%@ attribute name="orderByColumn" required="false"%>
<%@ attribute name="isOrderBy" required="false"%>

<c:if test="${ empty isOrderBy }">
	<div class="container text-center">
		<ul class="pagination">
			<li><a
				href="/webapp/dashboard?page=1<c:if test="${ not empty page.orderBy }">&orderBy=${ page.orderBy }</c:if><c:if test="${ not empty page.search }">&search=${ page.search }</c:if><c:if test="${ not empty page.limit }">&limit=${ page.limit }</c:if>">
					&laquo; </a></li>

			<c:choose>
				<c:when test="${ page.totalPages > 20 }">
					<c:forEach var="i" begin="1" step="5" end="${page.totalPages +1}">
						<li <c:if test="${ page.page == i }">class="active"</c:if>>
						<a href="/webapp/dashboard?page=${i}<c:if test="${ not empty page.orderBy }">&orderBy=${ page.orderBy }</c:if><c:if test="${ not empty page.search }">&search=${ page.search }</c:if><c:if test="${ not empty page.limit }">&limit=${ page.limit }</c:if>">
								${ i } </a></li>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<c:forEach var="i" begin="1" step="1" end="${page.totalPages +1}">
						<li <c:if test="${ page.page == i}">class="active"</c:if>><a
							href="/webapp/dashboard?page=${i}<c:if test="${ not empty page.orderBy }">&orderBy=${ page.orderBy }</c:if><c:if test="${ not empty page.search }">&search=${ page.search }</c:if><c:if test="${ not empty page.limit }">&limit=${ page.limit }</c:if>">
								${ i } </a></li>
					</c:forEach>
				</c:otherwise>
			</c:choose>

			<li><a
				href="/webapp/dashboard?page=${ page.totalPages + 1 }<c:if test="${ not empty page.orderBy }">&orderBy=${ page.orderBy }</c:if><c:if test="${ not empty page.search }">&search=${ page.search }</c:if><c:if test="${ not empty page.limit }">&limit=${ page.limit }</c:if>">
					&raquo; </a></li>
		</ul>



		<div class="btn-group btn-group-sm pull-right" role="group">
			<a class="btn btn-default"
				href="/webapp/dashboard?limit=10<c:if test="${ not empty page.orderBy }">&orderBy=${ page.orderBy }</c:if><c:if test="${ not empty page.search }">&search=${ page.search }</c:if>">10</a>
			<a class="btn btn-default"
				href="/webapp/dashboard?limit=50<c:if test="${ not empty page.orderBy }">&orderBy=${ page.orderBy }</c:if><c:if test="${ not empty page.search }">&search=${ page.search }</c:if>">50</a>
			<a class="btn btn-default"
				href="/webapp/dashboard?limit=100<c:if test="${ not empty page.orderBy }">&orderBy=${ page.orderBy }</c:if><c:if test="${ not empty page.search }">&search=${ page.search }</c:if>">100</a>
		</div>
	</div>
</c:if>

<c:if test="${ not empty isOrderBy }">
	<a
		href="/webapp/dashboard?orderBy=ASC&orderByColumn=
		<c:choose>
			<c:when test="${ empty page.orderByColumn }">
				${ page.orderByColumn }
			</c:when>
			<c:otherwise>
				${ orderByColumn }
			</c:otherwise>
		</c:choose>
		<c:if test="${ not empty page.search }">&search=${ page.search }</c:if><c:if test="${ not empty page.limit }">&limit=${ page.limit }</c:if>">
		&uarr; </a>
	<a
		href="/webapp/dashboard?orderBy=DESC&orderByColumn=
		<c:choose>
			<c:when test="${ empty page.orderByColumn }">
				${ page.orderByColumn }
			</c:when>
			<c:otherwise>
				${ orderByColumn }
			</c:otherwise>
		</c:choose>
		<c:if test="${ not empty page.search }">&search=${ page.search }</c:if><c:if test="${ not empty page.limit }">&limit=${ page.limit }</c:if>">
		&darr; </a>
</c:if>