<jsp:include page="header.jsp"></jsp:include>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="my"%>

<section id="main">
	<div class="container">
		<div class="alert alert-danger">
			Error 401: Full authentication is required to access this resource <br />
		</div>
	</div>
</section>
</body>
</html>