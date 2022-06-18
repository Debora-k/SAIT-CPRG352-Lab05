<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form method="POST" action="login">
            <label>Username: </label>
            <input type="text" name="user" value="${userid}">
            <br>
            <label>Password: </label>
            <input type="password" name="password" value="${userpassword}">
            <input type="submit" value="Log in">
        </form>
        <h3>${message}</h3>
    </body>
</html>
