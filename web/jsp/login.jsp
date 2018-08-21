<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<br>
<br>
<form name="LoginForm" method="post">
    <input type="hidden" name="command" value="login"/>
    <h4><fmt:message key="label.Login"/></h4>
    <input type="text" name="login" size="36" class="input" required/>
    <h4><fmt:message key="label.Password"/></h4>
    <input type="password" name="password" size="36" class="input" required/>
    <input type="submit" value="<fmt:message key="label.Login"/>"/>
</form>
<br>

</body>
</html>
