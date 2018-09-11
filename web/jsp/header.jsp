<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--If the language was supplied as request parameter (by controllerCommand), then it will be set--%>
<%--Else if the language was already previously set in the session, then stick it instead--%>
<%--Else use the user supplied locale in the request header.--%>
<c:set var="language" value="${not empty param.language ? param.language :
    not empty language  ?  language :
     pageContext.request.locale}" scope="session" />
<c:choose>
    <c:when test = "${language == 'ru_RU'}">
        <fmt:setLocale value="ru_RU" scope="session" />
    </c:when>
    <c:when test = "${language == 'uk_UA'}">
        <fmt:setLocale value="uk_UA" scope="session" />
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="en_US" scope="session" />
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="text"/>

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
                <fmt:message key="welcome.pleaseLogin"/>
            </c:when>
            <c:otherwise>
                <fmt:message key="welcome.youEntered"/> ${user.login}
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
    <a href="?command=findBooks"><fmt:message key="navigation.findBook"/></a>

    <c:if test="${isReader}">
        <a href="?command=showTakenBooks"><fmt:message key="navigation.takenBooks"/></a>
    </c:if>

    <c:if test="${isAdmin}">
        <a href="?command=showAllBooks"><fmt:message key="navigation.allBooks"/></a>
        <a href="?command=showAllTakenBooks"><fmt:message key="navigation.takenBooksAll"/></a>
        <a href="?command=showAllUsers"><fmt:message key="navigation.users"/></a>
    </c:if>

    <a href="?command=changeLanguage&locale=uk_UA" style="float: right;">Uk</a>
    <a href="?command=changeLanguage&locale=ru_RU" style="float: right;">Ru</a>
    <a href="?command=changeLanguage&locale=en_US" style="float: right;">En</a>

</div>
</body>
</html>
