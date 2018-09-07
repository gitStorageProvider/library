<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="text"/>
<html>
<body>
<div class="short-div">
    <form action="/controller" method="post">
        <input type="hidden" name="command" value="register">

        <label for="login"><fmt:message key="label.newUser.login"/></label>
        <input type="text" id="login" name="login" placeholder="<fmt:message key="hint.yourLogin"/>" value="${login}">
        <label class="wrong-input">${loginErrorMessage}</label>

        <label for="password"><fmt:message key="label.newUser.password"/></label>
        <input type="password" id="password" name="password" placeholder="<fmt:message key="hint.yourPassword"/>" value="${password}">
        <label class="wrong-input">${passwordErrorMessage}</label>

        <label for="passwordConfirmation"><fmt:message key="label.newUser.passwordConfirmation"/></label>
        <input type="password" id="passwordConfirmation" name="passwordConfirmation" placeholder="<fmt:message key="hint.yourPassword"/>" value="${passwordConfirmation}">
        <label class="wrong-input">${passwordConfirmationErrorMessage}</label>

        <label for="email"><fmt:message key="label.newUser.email"/></label>
        <input type="email" id="email" name="email" placeholder="<fmt:message key="hint.email"/>" value="${email}">
        <label class="wrong-input">${emailErrorMessage}</label>

        <label for="first-name"><fmt:message key="label.newUser.firstName"/></label>
        <input type="text" id="first-name" name="firstName" placeholder="<fmt:message key="hint.firstName"/>" value="${firstName}">
        <label class="wrong-input">${firstNameErrorMessage}</label>

        <label for="last-name"><fmt:message key="label.newUser.lastName"/></label>
        <input type="text" id="last-name" name="lastName" placeholder="<fmt:message key="hint.lastName"/>" value="${lastName}">
        <label class="wrong-input">${lastNameErrorMessage}</label>

        <label for="phone"><fmt:message key="label.newUser.phone"/></label>
        <input type="text" id="phone" name="phone" placeholder="<fmt:message key="hint.phoneExample"/>" value="${phone}">
        <label class="wrong-input">${phoneErrorMessage}</label>

        <input type="submit" value="<fmt:message key="button.register"/>">
    </form>
</div>
</body>
</html>
