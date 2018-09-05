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
    <a class="active"
            <c:if test="${user == null}">
                href="?command=register">Register
            </c:if>
            <c:if test="${user != null}">
                href="?command=logout">Log out
            </c:if>
    </a>
    <a href="?command=showAllBooks">Books</a>
    <a href="?command=showAvailableBooks">Available books</a>
    <a href="#XXXX">All taken books</a>
    <a href="#XXXX">Taken books</a>
    <a href="?command=showAllUsers">Users</a>
    <a href="?command=showAllAuthors">Authors</a>
    <a href="?command=showAllShelves">Shelves</a>
</div>
<br>
</body>
</html>
