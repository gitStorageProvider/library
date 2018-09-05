<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h1>${shelvesOperationMessage}</h1>
<h1>${shelvesErrorMessage}</h1>
<div class="medium-div">
    <form action="/controller" method="post">
        <input type="hidden" name="command" value="addShelf">

        <label for="shelfName">Name</label>
        <input type="text" id="shelfName" name="shelfName" placeholder="Shelf name.." value="${shelfName}">
        <label class="wrong-input">${shelfNameErrorMessage}</label>

        <label for="shelfAddress">Address</label>
        <input type="text" id="shelfAddress" name="shelfAddress" placeholder="Shelf address.." value="${shelfAddress}">
        <label class="wrong-input">${shelfAddressErrorMessage}</label>

        <label for="shelfDescription">Description</label>
        <input class="medium-input" type="text" id="shelfDescription" name="shelfDescription" placeholder="Shelf description.."
               value="${shelfDescription}">
        <label class="wrong-input">${shelfDescriptionErrorMessage}</label>

        <input type="submit" value="register">
    </form>
</div>
</body>
</html>

