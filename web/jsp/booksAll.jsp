<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<table id="outTable">
    <thead>
    <th>
        Books Available
    </th>
    <th>Authors</th>
    </thead>
    <tbody>
    <c:forEach var="oneMapEntry" items="${allBooksAuthorsMap}">
        <c:set var="book" value="${oneMapEntry.key}"/>
        <c:set var="authorsList" value="${oneMapEntry.value}"/>
        <tr>
            <td>${book.shortTitle}</td>
            <td><c:forEach var="oneAuthor" items="${authorsList}">
                ${oneAuthor.fullName}<br>
            </c:forEach> </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form action="/controller" method="get">
    <input type="hidden" name="command" value="addBook">
    <input type="submit" value="Add new book">
</form>
</body>
</html>
