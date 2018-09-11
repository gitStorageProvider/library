<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="text"/>
<html>
<body>
<div class="short-div">
    <form action="/controller" method="post">
        <input type="hidden" name="command" value="addBook">

        <label for="bookShortTitle"><fmt:message key="label.newBook.bookShortTitle"/></label>
        <input type="text" id="bookShortTitle" name="bookShortTitle" placeholder="<fmt:message key="hint.bookShortTitle"/>"
               value="${bookShortTitle}">
        <label class="wrong-input">${bookShortTitleErrorMessage}</label>

        <label for="bookFullTitle"><fmt:message key="label.newBook.bookFullTitle"/></label>
        <input type="text" id="bookFullTitle" name="bookFullTitle" placeholder="<fmt:message key="hint.bookFullTitle"/>"
               value="${bookFullTitle}">
        <label class="wrong-input">${bookFullTitleErrorMessage}</label>

        <label for="bookDescription"><fmt:message key="label.newBook.bookDescription"/></label>
        <input type="text" id="bookDescription" name="bookDescription" placeholder="<fmt:message key="hint.bookDescription"/>"
               value="${bookDescription}">
        <label class="wrong-input">${bookDescriptionErrorMessage}</label>

        <label for="bookKeyWords"><fmt:message key="label.newBook.bookKeyWords"/></label>
        <input type="text" id="bookKeyWords" name="bookKeyWords" placeholder="<fmt:message key="hint.bookKeyWords"/>" value="${bookKeyWords}">
        <label class="wrong-input">${bookKeyWordsErrorMessage}</label>

        <label for="authorSelection1"><fmt:message key="label.newBook.authorSelection1"/></label>
        <select id="authorSelection1" name="author1Id">
            <option value=""></option>
            <c:forEach var="author" items="${authorsList}">
                <option value="${author.id}">${author.fullName}</option>
            </c:forEach>
        </select>
        <label class="wrong-input">${bookAuthorsErrorMessage}</label>

        <label for="authorSelection2"><fmt:message key="label.newBook.authorSelection2"/></label>
        <select id="authorSelection2" name="author2Id">
            <option value=""></option>
            <c:forEach var="author" items="${authorsList}">
                <option value="${author.id}">${author.fullName}</option>
            </c:forEach>
        </select>
        <label class="wrong-input">${bookAuthorsErrorMessage}</label>

        <label for="authorSelection3"><fmt:message key="label.newBook.authorSelection3"/></label>
        <select id="authorSelection3" name="author3Id">
            <option value=""></option>
            <c:forEach var="author" items="${authorsList}">
                <option value="${author.id}">${author.fullName}</option>
            </c:forEach>
        </select>
        <label class="wrong-input">${bookAuthorsErrorMessage}</label>

        <label for="authorSelection4"><fmt:message key="label.newBook.authorSelection4"/></label>
        <select id="authorSelection4" name="author4Id">
            <option value=""></option>
            <c:forEach var="author" items="${authorsList}">
                <option value="${author.id}">${author.fullName}</option>
            </c:forEach>
        </select>
        <label class="wrong-input">${bookAuthorsErrorMessage}</label>

        <label for="shelfSelection"><fmt:message key="label.newBook.Shelf"/></label>
        <select id="shelfSelection" name="shelfId">
            <c:forEach var="shelf" items="${shelvesList}">
                <option value="${shelf.id}">${shelf.address}, ${shelf.name}</option>
            </c:forEach>
        </select>

        <label for="bookQuantity"><fmt:message key="label.newBook.bookQuantity"/></label>
        <input type="text" id="bookQuantity" name="bookQuantity" placeholder="<fmt:message key="hint.bookQuantity"/>"
               value="${bookQuantity}">
        <label class="wrong-input">${bookQuantityErrorMessage}</label>
        <input type="submit" value="register">
    </form>
</div>
</body>
</html>
