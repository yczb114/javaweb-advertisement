<%--
  Created by IntelliJ IDEA.
  User: theorigenallywhite
  Date: 2024/12/24
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>${requestScope.commodity.name}</title>
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="css/common.css" type="text/css">
</head>
<h3>具体商品页</h3>
<div class="user">
    <strong>当前登录的用户为：${sessionScope.username}</strong>
    <form action="show-servlet" method="get">
        <button type="submit" name="button" value="showCart" class="green">查看购物车</button>
    </form>
    <form action="hello-servlet" method="get">
        <button type="submit" name="button" value="out" id="out">退出登录</button>
    </form>
</div>
<hr>
<body>
    <div>
        <img src="${requestScope.commodity.path}" alt="" height="500px" width="500px">
        <h1>${requestScope.commodity.name}</h1>
        <h2>价格:${requestScope.commodity.price}元</h2>
        <form action="cart-servlet" method="get">
            <button type="submit" name="button" value="cadd">添加到购物车</button>
            <input type="hidden" name="Cid" value="${requestScope.commodity.getCid()}">
        </form>
        <form action="show-servlet" method="get">
            <button type="submit" name="button" value="back">返回</button>
        </form>
    </div>
</body>
</html>
