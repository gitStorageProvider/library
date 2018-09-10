<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<br>
<h3>request parameters:</h3>
<c:forEach items="${param}" var="par">
    Parameter Name/Value : <c:out value="${par.key} - ${par.value}"/><br>
</c:forEach>
<br>
<h3>session objects:</h3>
<c:forEach items="${sessionScope}" var="par">
    Session object name/value : <c:out value="${par.key} - ${par.value}"/><br>
</c:forEach>
<h3>request objects:</h3>
<c:forEach items="${requestScope}" var="par">
    Request object name/value : <c:out value="${par.key} - ${par.value}"/><br>
</c:forEach>
<hr>
</body>
</html>