<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="text"/>
<html>
<body>
<table id="outTable">
    <thead>
    <th>
        <fmt:message key="label.tableHead.bookavailable"/>
    </th>
    <th>
        <fmt:message key="label.tableHead.author"/>
    </th>
    <c:if test="${isAdmin}">
        <th><fmt:message key="label.tableHead.option"/></th>
    </c:if>
    </thead>
    <tbody>
    <c:forEach var="oneMapEntry" items="${allBooksAuthorsMap}">
        <c:set var="book" value="${oneMapEntry.key}"/>
        <c:set var="authorsList" value="${oneMapEntry.value}"/>
        <c:url value="?command=deleteBook" var="deleteLink">
            <c:param name="id" value="${book.id}"/>
        </c:url>
        <tr>
            <td>${book.shortTitle}</td>
            <td>
                <c:forEach var="oneAuthor" items="${authorsList}">
                    ${oneAuthor.fullName}<br>
                </c:forEach>
            </td>
            <c:if test="${isAdmin}">
            <td><a href="${deleteLink}"><fmt:message key="option.delete"/></a></td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form action="/controller" method="get">
    <input type="hidden" name="command" value="addBook">
    <input type="submit" value="<fmt:message key="button.addBook"/>">
</form>
</body>
</html>
