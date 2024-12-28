<%--
  Created by IntelliJ IDEA.
  User: theorigenallywhite
  Date: 2024/12/24
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${requestScope.commodity.name}</title>
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
</head>
<body>
    <div>
        <img src="${requestScope.commodity.path}" alt="" height="500px" width="500px">
        <h1>${requestScope.commodity.name}</h1>
        <h2>价格:${requestScope.commodity.price}元</h2>
        <form action="cart-servlet" method="get">
            <button type="submit" name="button" value="cadd">添加到购物车</button>
            <input type="hidden" name="Cid" value="${requestScope.commodity.getCid()}">
            <input type="hidden" name="jsp" value="show">
        </form>
        <form action="show-servlet" method="get">
            <button type="submit" name="button" value="back">返回</button>
        </form>
    </div>
</body>
</html>
