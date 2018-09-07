<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="text"/>
<html>
<body>
<div class="short-div">
    <form action="/controller" method="post">
        <input type="hidden" name="command" value="login">

        <label for="login"><fmt:message key="label.login"/></label>
        <input type="text" id="login" name="login" placeholder="<fmt:message key="hint.yourLogin"/>">

        <label for="password"><fmt:message key="label.password"/></label>
        <input type="password" id="password" name="password" placeholder="<fmt:message key="hint.yourPassword"/>">

        <label class="wrong-input">${authorizationErrorMessage}</label>

        <input type="submit" value="<fmt:message key="button.enterInSystem"/>">
    </form>
</div>

</body>
</html>
