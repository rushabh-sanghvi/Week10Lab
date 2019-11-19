<%-- 
    Document   : reset
    Created on : Nov 12, 2019, 11:38:05 AM
    Author     : 794473
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Forgot Password</title>
    </head>
    <body>
        <h1>FORGOT PASSWORD</h1>

        Please enter your email address to reset your password., A link will be sent within 24 hours if email exists.
        <br>
        <br>
        <form action="reset" method="post">
            Email Address: <input type="email" name="email">
            <br>
            <input type="submit" value="Reset">
            <input type="hidden" name="action" value="reset">
        </form>
    </body>
</html>
