<%-- 
    Document   : MyEShop
    Created on : Jan 3, 2025, 9:41:53 PM
    Author     : huyng
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            #bag{
                text-align: right;
                margin-right: 30px;
                margin-top:30px;

            }
        </style>
        <script type="text/javascript">
            function buy(id){
                document.f.action="buy?id="+id;
                document.f.submit();
            }
        </script>
            
    </head>
    <body>
        <c:set var="size" value="${requestScope.size}"/> 
        Hello ${sessionScope.account.name}
        <p id="bag">
            <img src="images/cart.png" alt="alt" width="50" height="50"/>
            <a href="shop"> My cart (${size}) items</a>
        </p>
        <h1>The List of Products</h1>
        <form name="f" action="" method="post">
            Enter number of items to buy:
            <input type="number" name="num" value="1">
            <hr/>
            <table border="1px" width="40%">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
                <jsp:useBean id="db" class="dal.ProductDAO"/>
                <c:forEach items="${db.all}" var="p">
                    <tr>
                        <td>${p.id}</td>
                        <td>${p.name}</td>
                        <td>${p.quantity}</td>
                        <td>
                            <input type="submit" 
                                   onclick="buy('${p.id}')" 
                                   value="Buy item" name="name">
                        </td>
                    </tr>

                </c:forEach>
            </table>
        </form>
                <h2 style="color:blue"> <a href="login.jsp">Logout</a> </h2>
    </body>
</html>
