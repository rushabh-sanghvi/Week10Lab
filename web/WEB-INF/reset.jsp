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
        <title>Reset Password</title>
    </head>
    <body>
        <h1>Enter Your New Password</h1>
        <form action="reset" method="post">
        Password: <input type="password" name="respassword">
        <br>
        <input type="submit" value="Reset">
        <input type ="hidden" name="action" value="resp">
        <input type ="hidden" name="uuid" value='${uuid}'>
        </form>
</html>
