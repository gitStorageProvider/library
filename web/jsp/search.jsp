<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<div class="medium-div">
    <form action="/controller" method="post">

        <input type="hidden" name="command" value="findBooks">

        <input type="radio" class="radioButtorn" name="searchCriteria" value="bookTitle"><fmt:message key="radiobutton.bookTitle"/><br>
        <input type="radio" class="radioButtorn" name="searchCriteria" value="bookKeyWords"><fmt:message key="radiobutton.bookKeyWords"/><br>
        <input type="radio" class="radioButtorn" name="searchCriteria" value="authorName"><fmt:message key="radiobutton.authorName"/><br>

        <label for="searchKeyWords"><fmt:message key="label.search.searchKeyWords"/></label>
        <input type="text" id="searchKeyWords" name="searchKeyWords" placeholder="<fmt:message key="hint.search.searchKeyWords"/>"
               value="${searchKeyWords}">
        <label class="wrong-input">${searchKeyWordsErrorMessage}</label>

        <input type="submit" value="<fmt:message key="button.find"/>">
    </form>
</div>

<c:choose>
    <c:when test="${empty booksAuthorsMap}">
    </c:when>
    <c:otherwise>
        <table id="outTable">
        <thead>
            <th>
            <fmt:message key="label.tableHead.book"/>
            </th>
            <th>
            <fmt:message key="label.tableHead.author"/>
            </th>
            <th>
                <fmt:message key="label.newBook.bookKeyWords"/>
            </th>
        </thead>
        <tbody>
        <c:forEach var="oneMapEntry" items="${booksAuthorsMap}">
            <c:set var="book" value="${oneMapEntry.key}"/>
            <c:set var="authorsList" value="${oneMapEntry.value}"/>
            <tr>
                <td>${book.fullTitle}</td>
                <td>${book.keyWords}</td>
                <td>
                    <c:forEach var="oneAuthor" items="${authorsList}">
                        ${oneAuthor.fullName}<br>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
        </tbody>
        </table>
    </c:otherwise>
</c:choose>

</body>
</html>
