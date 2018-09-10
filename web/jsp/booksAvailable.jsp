<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="text"/>
<html>
<body>
<table id="outTable">
    <thead>
    <th>
        <fmt:message key="label.tableHead.book"/>, <fmt:message key="label.tableHead.author"/>
    </th>
    <th><fmt:message key="label.tableHead.quantity"/></th>
    <c:if test="${isReader == true}">
        <th><fmt:message key="label.tableHead.option"/></th>
    </c:if>
    </thead>
    <tbody>
    <c:forEach var="oneBook" items="${allAvailableBooksMap}">
        <c:set var="book" value="${oneBook.key}"/>
        <c:set var="quantity" value="${oneBook.value}"/>
        <tr>
            <td>${book.shortTitle}</td>
            <td>${quantity}</td>
            <c:if test="${isReader == true}">
                <c:url value="?command=takeBook" var="takeLink">
                    <c:param name="bookId" value="${oneBook.key.id}"/>
                </c:url>
                <td>
                    <a href="${takeLink}"><fmt:message key="option.takeBook"/></a>
                </td>>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
