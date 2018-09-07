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
    </thead>
    <tbody>
    <c:forEach var="oneBook" items="${allAvailableBooksMap}">
        <c:set var="book" value="${oneBook.key}"/>
        <c:set var="quantity" value="${oneBook.value}"/>
        <tr>
            <td>${book.shortTitle}</td>
            <td>${quantity}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
