<%--
  Created by IntelliJ IDEA.
  User: neo
  Date: 03.09.2018
  Time: 16:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Books</title>
</head>
<body>
<table id="outTable">
    <thead>
    <th>
        Book, Author
    </th>
    <th>Quantity</th>
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
