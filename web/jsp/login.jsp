<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Login</title>
</head>
<style>
    /*input, select {*/
        /*width: 100%;*/
        /*padding: 12px 20px;*/
        /*margin: 8px 0;*/
        /*display: inline-block;*/
        /*border: 1px solid #ccc;*/
        /*border-radius: 4px;*/
        /*box-sizing: border-box;*/
    /*}*/

    /*input[type=submit] {*/
        /*width: 100%;*/
        /*background-color: #4CAF50;*/
        /*color: white;*/
        /*padding: 14px 20px;*/
        /*margin: 8px 0;*/
        /*border: none;*/
        /*border-radius: 4px;*/
        /*cursor: pointer;*/
    /*}*/

    /*input[type=submit]:hover {*/
        /*background-color: #45a049;*/
    /*}*/

    /*div {*/
        /*border-radius: 5px;*/
        /*background-color: #f2f2f2;*/
        /*padding: 20px;*/
    /*}*/

    /*.short-div {*/
        /*width: 300px;*/
    /*}*/

    /*.wrong-input, .error-message{*/
        /*color: red;*/
        /*display: block;*/
    /*}*/
    <%@include file="/css/styles.css" %>
</style>
<body>
<br>
<br>
<div class="short-div">
    <form action="/controller" method="post">
        <input type="hidden" name="command" value="login">

        <label for="login">Login</label>
        <input type="text" id="login" name="login" placeholder="Your login..">

        <label for="password">Password</label>
        <input type="password" id="password" name="password" placeholder="Your pass..">

        <label class="wrong-input">${authorizationErrorMessage}</label>

        <input type="submit" value="Enter in system">
    </form>
</div>

</body>
</html>
