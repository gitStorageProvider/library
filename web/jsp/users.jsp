<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="text"/>
<html>
<body>
<h1>${usersOperationMessage}</h1>
<h1>${usersErrorMessage}</h1>
<c:choose>
    <c:when test="${empty usersList}">
    </c:when>
    <c:otherwise>
        <table id="outTable">
            <thead>
                <th><fmt:message key="label.tableHead.login"/></th>
                <th><fmt:message key="label.tableHead.email"/></th>
                <th><fmt:message key="label.tableHead.firstName"/></th>
                <th><fmt:message key="label.tableHead.lastName"/></th>
                <th><fmt:message key="label.tableHead.phone"/></th>
                <c:if test="${isAdmin}">
                    <th><fmt:message key="label.tableHead.option"/></th>
                    <th><fmt:message key="label.tableHead.option"/></th>
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
                            <a href="${deleteLink}"><fmt:message key="option.delete"/></a>
                        </td>
                        <td>
                            <a href="${editLink}"><fmt:message key="option.edit"/></a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
<form action="/controller" method="get">
    <input type="hidden" name="command" value="register">
    <input type="submit" value="<fmt:message key="button.addUser"/>">
</form>
</body>
</html>