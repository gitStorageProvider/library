<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="text"/>
<fmt:setLocale value="ru_RU" scope="session"/>
<html>
<head>
    <style>
        <%@include file="/css/styles.css" %>
    </style>
</head>
<body>
<%@include file="/jsp/parametersList.jsp" %>
<div style="padding-left:16px">
    <h2>
        <c:choose>
            <c:when test="${empty user}">
                PLEASE LOG IN
            </c:when>
            <c:otherwise>
                YOU LOGGED IN AS: ${user.login}
            </c:otherwise>
        </c:choose>
    </h2>
</div>
<div class="topnav">
    <c:if test="${user == null}">
        <a href="?command=register"><fmt:message key="navigation.register"/></a>
    </c:if>
    <c:if test="${user == null}">
        <a class="active" href="?command=login"><fmt:message key="navigation.login"/></a>
    </c:if>
    <c:if test="${user != null}">
        <a  href="?command=logout"><fmt:message key="navigation.logout"/></a>
    </c:if>

    <a href="?command=showAvailableBooks"><fmt:message key="navigation.availableBooks"/></a>
    <a href="?command=showAllAuthors"><fmt:message key="navigation.authors"/></a>
    <a href="?command=showAllShelves"><fmt:message key="navigation.shelves"/></a>

    <c:if test="${isReader}">
        <a href="?command=showTakenBooks"><fmt:message key="navigation.takenBooks"/></a>
    </c:if>

    <c:if test="${isAdmin}">
        <a href="?command=showAllBooks"><fmt:message key="navigation.allBooks"/></a>
        <a href="?command=showAllTakenBooks"><fmt:message key="navigation.takenBooksAll"/></a>
        <a href="?command=showAllUsers"><fmt:message key="navigation.users"/></a>
    </c:if>

    <a href="?command=changeLocale&locale=ruRU" style="float: right;">Ru</a>
</div>
</body>
</html>
