<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header</title>
    <style>
        <%@include file="/css/styles.css" %>
    </style>
</head>
<body>
<%@include file="/jsp/parametersList.jsp" %>
<br>
This is header.jsp
<hr>

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
        <a href="?command=register">Register</a>
    </c:if>
    <c:if test="${user == null}">
        <a class="active" href="?command=login">Log in</a>
    </c:if>
    <c:if test="${user != null}">
        <a  href="?command=logout">Log out</a>
    </c:if>

    <a href="?command=showAvailableBooks">Available books</a>
    <a href="?command=showAllAuthors">Authors</a>
    <a href="?command=showAllShelves">Shelves</a>

    <c:if test="${isReader}">
        <a href="?command=showTakenBooks">Taken books</a>
    </c:if>
    <c:if test="${isAdmin}">
        <a href="?command=showAllBooks">Books</a>
        <a href="?command=showAllTakenBooks">All taken books</a>
        <a href="?command=showAllUsers">Users</a>
    </c:if>

</div>
<br>
</body>
</html>
