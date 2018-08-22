<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Header</title>
    <style>
        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }

        .topnav {
            overflow: hidden;
            background-color: #333;
        }

        .topnav a {
            float: left;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }

        .topnav a.active {
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>
<%@include file="/jsp/parametersList.jsp" %>
<br>
This is header.jsp
<hr>

<div style="padding-left:16px">
    <h2>
        <c:if test="${user == null}">
            PLEASE LOG IN
        </c:if>
        <c:if test="${user != null}">
            USER LOGGED IN
        </c:if>
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
    <a href="#XXXX">Available books</a>
    <a href="#XXXX">All taken books</a>
    <a href="#XXXX">Taken books</a>
    <a href="#XXXX">Users</a>
    <a href="#XXXX">Shelves</a>

</div>


<br>
</body>
</html>
