<%-- 
    Document   : MyEcart
    Created on : Jan 5, 2025, 6:29:39 PM
    Author     : huyng
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table{
                border-collapse: collapse;
            }
        </style>

    </head>
    <body >
        <c:set var="cart" value="${requestScope.cart}"/>
        <c:set var="t" value="0"/>
        <h1>Shopping cart online</h1>
        <table border="1px" style="collapse" width="25%">
            <tr>
                <th>No</th>
                <th>Name</th>
                <th>Quantity</th>
                <th>Money</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${cart.items}" var="i">
                <tr>
                    <c:set var="t" value="${t+1}"/>
                    <td>${t}</td>
                    <td>${i.product.name}</td>
                    <td>
                        <button><a href="process?num=-1&id=${i.product.id}">
                                -</a></button>
                        <input type="text" readonly value="${i.quantity}"/>
                        <button><a href="process?num=1&id=${i.product.id}">
                                +</a></button>
                    </td>
                    <td><fmt:formatNumber pattern="##.#" value="${i.price}"/></td>
                    <td>
                        <form action="process" method="post">
                            <input type="hidden" name="id" value="${i.product.id}">
                            <input type="submit" value="Return Item">
                        </form>
                    </td>
                    </tr>
            </c:forEach>
        </table>
        <h3>Total: <fmt:formatNumber pattern="##.###" value="${cart.totalMoney()}" /></h3>
        <form action="checkout" method="post">
            <input type="submit" value="Check out">
        </form>
        <hr/>
        <h2 style="color:chocolate"><a href="MyEShop.jsp">CLICK ME TO CONTINUE BUYING</a></h2>
    </body>
</html>
