<%--
  Created by IntelliJ IDEA.
  User: neo
  Date: 02.09.2018
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users list</title>
</head>
<body>
<h1>${usersOperationMessage}</h1>
<h1>${usersErrorMessage}</h1>
<c:choose>
    <c:when test="${empty usersList}">
        Empty.
    </c:when>
    <c:otherwise>
        <table id="outTable">
            <thead>
                <th>Login</th>
                <th>Email</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Phone</th>
                <c:if test="${isAdmin}">
                    <th>Option</th>
                    <th>Option</th>
                </c:if>
            </thead>
            <c:forEach var="oneUser" items="${usersList}">
                <tr>
                    <c:url value="?command=deleteUser" var="deleteLink">
                        <c:param name="userId" value="${oneUser.id}"/>
                    </c:url>

                    <c:url value="?command=editUser" var="editLink">
                        <c:param name="userId" value="${oneUser.id}"/>
                    </c:url>

                    <td>${oneUser.login}</td>
                    <td>${oneUser.email}</td>
                    <td>${oneUser.firstName}</td>
                    <td>${oneUser.lastName}</td>
                    <td>${oneUser.phone}</td>
                    <c:if test="${isAdmin == true}">
                        <td>
                            <a href="${deleteLink}"><c:out value="delete"/></a>
                        </td>
                        <td>
                            <a href="${editLink}"><c:out value="edit"/></a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
<form action="/controller" method="get">
    <input type="hidden" name="command" value="register">
    <input type="submit" value="Add new user">
</form>
</body>
</html>
