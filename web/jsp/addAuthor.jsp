<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="text"/>
<html>
<body>
<div class="medium-div">
    <form action="/controller" method="post">
        <input type="hidden" name="command" value="addAuthor">

        <label for="authorName"><fmt:message key="label.newAuthor.authorName"/></label>
        <input type="text" id="authorName" name="authorName" placeholder="<fmt:message key="label.newAuthor.authorName"/>.." value="${authorName}">
        <label class="wrong-input">${authorNameErrorMessage}</label>

        <label for="authorDetails"><fmt:message key="label.newAuthor.authorDetails"/></label>
        <input type="text" id="authorDetails" name="authorDetails" placeholder="<fmt:message key="label.newAuthor.authorDetails"/>.." value="${authorDetails}">
        <label class="wrong-input">${authorDetailsErrorMessage}</label>

        <input type="submit" value="<fmt:message key="button.addAuthor"/>">
    </form>
</div>
</body>
</html>
