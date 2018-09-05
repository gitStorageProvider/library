<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<div class="short-div">
    <form action="/controller" method="post">
        <input type="hidden" name="command" value="addBook">

        <label for="bookShortTitle">Short Title</label>
        <input type="text" id="bookShortTitle" name="bookShortTitle" placeholder="Book short title.."
               value="${bookShortTitle}">
        <label class="wrong-input">${bookShortTitleErrorMessage}</label>

        <label for="bookFullTitle">Full Title</label>
        <input type="text" id="bookFullTitle" name="bookFullTitle" placeholder="Book full title.."
               value="${bookFullTitle}">
        <label class="wrong-input">${bookFullTitleErrorMessage}</label>

        <label for="bookDescription">Description</label>
        <input type="text" id="bookDescription" name="bookDescription" placeholder="Book description.."
               value="${bookDescription}">
        <label class="wrong-input">${bookDescriptionErrorMessage}</label>

        <label for="bookKeyWords">Key words</label>
        <input type="text" id="bookKeyWords" name="bookKeyWords" placeholder="Book key words.." value="${bookKeyWords}">
        <label class="wrong-input">${bookKeyWordsMessage}</label>

        <label for="authorSelection1">authorSelection1</label>
        <select id="authorSelection1" name="author1Id">
            <option value=""></option>
            <c:forEach var="author" items="${authorsList}">
                <option value="${author.id}">${author.fullName}</option>
            </c:forEach>
        </select>

        <label for="authorSelection2">authorSelection2</label>
        <select id="authorSelection2" name="author2Id">
            <option value=""></option>
            <c:forEach var="author" items="${authorsList}">
                <option value="${author.id}">${author.fullName}</option>
            </c:forEach>
        </select>

        <label for="authorSelection3">authorSelection3</label>
        <select id="authorSelection3" name="author3Id">
            <option value=""></option>
            <c:forEach var="author" items="${authorsList}">
                <option value="${author.id}">${author.fullName}</option>
            </c:forEach>
        </select>

        <label for="authorSelection4">authorSelection4</label>
        <select id="authorSelection4" name="author4Id">
            <option value=""></option>
            <c:forEach var="author" items="${authorsList}">
                <option value="${author.id}">${author.fullName}</option>
            </c:forEach>
        </select>

        <label for="shelfSelection">Shelf selection</label>
        <select id="shelfSelection" name="shelfId">
            <c:forEach var="shelf" items="${shelvesList}">
                <option value="${shelf.id}">${shelf.address}, ${shelf.name}</option>
            </c:forEach>
        </select>

        <label for="bookQuantity">Quantity</label>
        <input type="text" id="bookQuantity" name="bookQuantity" placeholder="Book quantity.."
               value="${bookDescription}">
        <label class="wrong-input">${bookQuantityErrorMessage}</label>
        <input type="submit" value="register">
    </form>
</div>
</body>
</html>
