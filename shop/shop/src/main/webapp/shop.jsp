<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: theorigenallywhite
  Date: 2024/12/24
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上理商城</title>
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
</head>
<body>
<c:set var="commodities" value="${requestScope.commodities}"/>
<div class="d1">
    <form action="shop-servlet" method="get">
        <div class="btn-group" role="group" aria-label="Basic example">
            <button type="submit" class="btn btn-light" name="tag" value="首页">首页</button>
            <button type="submit" class="btn btn-light" name="tag" value="数码">数码</button>
            <button type="button" class="btn btn-light" name="tag" value="食品">食品</button>
            <button type="submit" class="btn btn-light" name="tag" value="母婴">母婴</button>
            <button type="submit" class="btn btn-light" name="tag" value="运动">运动</button>
            <button type="submit" class="btn btn-light" name="tag" value="医药">医药</button>
            <button type="submit" class="btn btn-light" name="tag" value="家具">家具</button>
        </div>
    </form>
    <ul class="list-group list-group-flush">
        <c:forEach var="commodity" items="${commodities}">
            <c:url var="commodityUrl" value="show-servlet">
                <c:param name="Cid" value="${commodity.getCid()}"/>
            </c:url>
            <li class="list-group-item">
                <img src="${commodity.path}" class="img-fluid" alt="" width="200px" height="200px">
                <a href="${commodityUrl}">
                    ${commodity.name}|价格:${commodity.price}
                </a>
                <form action="cart-servlet" method="get">
                    <button type="submit" name="cadd">添加到购物车</button>
                </form>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
