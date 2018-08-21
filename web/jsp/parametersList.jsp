<%--
  Created by IntelliJ IDEA.
  User: neo
  Date: 21.08.2018
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
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
<h1>request parameters:</h1><br>
<c:forEach items="${param}" var="par">
    Parameter Name/Value : <c:out value="${par.key} - ${par.value}"/><br>
</c:forEach>
<br>
<h1>session objects:</h1><br>
<c:forEach items="${sessionScope}" var="par">
    Session object name/value : <c:out value="${par.key} - ${par.value}"/><br>
</c:forEach>
<h1>request objects:</h1><br>
<c:forEach items="${requestScope}" var="par">
    Request object name/value : <c:out value="${par.key} - ${par.value}"/><br>
</c:forEach>
<hr>
</body>
</body>
</html>