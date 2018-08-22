<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%--<fmt:setLocale value="ru_RU" scope="session"/>--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Parameters List</title>
</head>
<body>
<body>
This is parametersList.jsp



<%
    // add parameter to session
    String name = request.getParameter("name");
    String value = request.getParameter("value");
    if (name!=null && value!=null && name.length()>0) {
        session.setAttribute(name,value);
    }
%>




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
</body>
</html>