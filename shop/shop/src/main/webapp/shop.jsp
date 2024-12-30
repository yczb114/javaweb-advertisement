<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: theorigenallywhite
  Date: 2024/12/24
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>上理商城</title>
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="css/shop.css" type="text/css">
    <link rel="stylesheet" href="css/common.css" type="text/css">
</head>
<h3>商品</h3>
<div class="user">
    <strong>当前登录的用户为：${sessionScope.username}</strong>
    <form action="cart-servlet" method="get">
        <button type="submit" name="button" value="showCart" class="green">查看购物车</button>
    </form>
    <form action="hello-servlet" method="get">
        <button type="submit" name="button" value="out" id="out">退出登录</button>
    </form>
</div>
<body>
<hr>
<c:set var="commodities" value="${requestScope.commodities}"/>
<div class="d1">
    <form action="shop-servlet" method="get">
        <div class="btn-group" role="group" aria-label="Basic example">
            <button type="submit" class="btn btn-light" name="tag" value="首页">首页</button>
            <button type="submit" class="btn btn-light" name="tag" value="数码">数码</button>
            <button type="submit" class="btn btn-light" name="tag" value="食品">食品</button>
            <button type="submit" class="btn btn-light" name="tag" value="母婴">母婴</button>
            <button type="submit" class="btn btn-light" name="tag" value="运动">运动</button>
            <button type="submit" class="btn btn-light" name="tag" value="医药">医药</button>
            <button type="submit" class="btn btn-light" name="tag" value="家具">家具</button>
        </div>
        <label for="searchText"></label>
        <input type="text" name="searchText" id="searchText">
        <button type="submit" class="btn btn-primary" name="button" value="search">搜索</button>
    </form>
    <ul>
        <c:forEach var="commodity" items="${commodities}">
            <c:url var="commodityUrl" value="show-servlet">
                <c:param name="Cid" value="${commodity.getCid()}"/>
            </c:url>
            <li>
                <div class="commodity">
                    <img src="${commodity.path}" class="img-fluid" alt="" width="200" height="200"><br>
                    <a href="${commodityUrl}">
                        ${commodity.name}
                    </a><br>
                    <strong class="price">￥${commodity.price}</strong>
                    <form action="shop-servlet" method="get">
                        <button type="submit" name="button" class="btn btn-primary" value="cadd">添加到购物车</button>
                        <input type="hidden" name="Cid" value="${commodity.getCid()}">
                    </form>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
