<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New user registration form</title>
    <style>
        input, select {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type=submit] {
            width: 100%;
            background-color: #4CAF50;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type=submit]:hover {
            background-color: #45a049;
        }

        div {
            border-radius: 5px;
            background-color: #f2f2f2;
            padding: 20px;
        }

        .short-div {
            width: 300px;
        }

        .wrong-input, .error-message{
            color: red;
            display: block;
            text-align: right;
        }

    </style>
</head>
<body>
<div class="short-div">
    <form action="/controller" method="post">
        <input type="hidden" name="command" value="register">

        <label for="login">Login</label>
        <input type="text" id="login" name="login" placeholder="Your login.." value="${login}">
        <label class="wrong-input">${loginErrorMessage}</label>

        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="Your pass.." value="${password}">
        <label class="wrong-input">${passwordErrorMessage}</label>

        <label for="passwordConfirmation">Confirm password</label>
        <input type="password" id="passwordConfirmation" name="passwordConfirmation" placeholder="Your pass.." value="${passwordConfirmation}">
        <label class="wrong-input">${passwordConfirmationErrorMessage}</label>

        <label for="email">@-mail</label>
        <input type="email" id="email" name="email" placeholder="Your email.." value="${email}">
        <label class="wrong-input">${emailErrorMessage}</label>

        <label for="first-name">First name</label>
        <input type="text" id="first-name" name="firstName" placeholder="Your first name.." value="${firstName}">
        <label class="wrong-input">${firstNameErrorMessage}</label>

        <label for="last-name">Last name</label>
        <input type="text" id="last-name" name="lastName" placeholder="Your last name.." value="${lastName}">
        <label class="wrong-input">${lastNameErrorMessage}</label>

        <label for="phone">Phone</label>
        <input type="text" id="phone" name="phone" placeholder="example: +380971234567" value="${phone}">
        <label class="wrong-input">${phoneErrorMessage}</label>

        <input type="submit" value="register">
    </form>
</div>

</body>
</html>
