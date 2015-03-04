<%@ attribute name="target" required="true" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="myClass" required="false" %>
<%@ attribute name="computerId" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<a 
<c:if test="${ not empty myClass }">class="${ myClass }"</c:if>
href="${ target }<c:if test="${ not empty computerId }">?computerId=${ computerId }</c:if>">
	${ name }
</a>
