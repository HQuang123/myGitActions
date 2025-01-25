<%-- 
    Document   : login.jsp
    Created on : Jan 7, 2025, 8:52:33 PM
    Author     : huyng
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Login Form</h1>
        <h3 style="color:red">${requestScope.error}</h3>
        <form action="login" method="get">
            <table>
                <tr>
                    <td>Enter username:</td>
                    <td><input type="text" name="user"></td>
                </tr>
                <tr>
                    <td>Enter password:</td>
                    <td><input type="text" name="password"></td>
                </tr>
                <tr>
                    <td></td>
                    <td> <input type="submit" value="Login"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
