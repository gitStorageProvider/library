<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<div class="medium-div">
    <form action="/controller" method="post">
        <input type="hidden" name="command" value="addAuthor">

        <label for="authorName">Author Full Name</label>
        <input type="text" id="authorName" name="authorName" placeholder="Author name.." value="${authorfName}">
        <label class="wrong-input">${authorNameErrorMessage}</label>

        <label for="authorDetails">Details</label>
        <input type="text" id="authorDetails" name="authorDetails" placeholder="Author details.." value="${authorDetails}">
        <label class="wrong-input">${authorDetailsErrorMessage}</label>

        <input type="submit" value="Add">
    </form>
</div>
</body>
</html>

