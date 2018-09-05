<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shelves</title>
</head>
<body>
<h1>${shelvesOperationMessage}</h1>
<h1>${shelvesErrorMessage}</h1>
<c:choose>
    <c:when test="${empty shelvesList}"/>
    <c:otherwise>
        <table id="outTable">
            <thead>
            <th>Name</th>
            <th>Address</th>
            <th>Description</th>
                <c:if test="${isAdmin}">
                    <th>Option</th>
                    <th>Option</th>
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
                                <a href="${deleteLink}"><c:out value="delete"/></a>
                            </td>
                            <td>
                                <a href="${editLink}"><c:out value="edit"/></a>
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
    <input type="submit" value="Add new shelf">
</form>
</body>
</html>
