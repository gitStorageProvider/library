<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="text"/>
<html>
<body>
<c:choose>
    <c:when test="${empty shelvesList}"/>
    <c:otherwise>
        <table id="outTable">
            <thead>
            <th><fmt:message key="label.tableHead.name"/></th>
            <th><fmt:message key="label.tableHead.address"/></th>
            <th><fmt:message key="label.tableHead.description"/></th>
                <c:if test="${isAdmin}">
                    <th><fmt:message key="label.tableHead.option"/></th>
                    <th><fmt:message key="label.tableHead.option"/></th>
                </c:if>
            </thead>
            <tbody>
                <c:forEach var="oneShelf" items="${shelvesList}">
                    <tr>
                        <c:url value="?command=deleteShelf" var="deleteLink">
                            <c:param name="shelfId" value="${oneShelf.id}"/>
                        </c:url>

                        <c:url value="?command=editShelf" var="editLink">
                            <c:param name="shelfId" value="${oneShelf.id}"/>
                        </c:url>

                        <td>${oneShelf.name}</td>
                        <td>${oneShelf.address}</td>
                        <td>${oneShelf.description}</td>
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
            </tbody>
        </table>
    </c:otherwise>
</c:choose>
<form action="/controller" method="get">
    <input type="hidden" name="command" value="addShelf">
    <input type="submit" value="<fmt:message key="button.addShelf"/>">
</form>
</body>
</html>
