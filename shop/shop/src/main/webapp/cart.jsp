<%--
  Created by IntelliJ IDEA.
  User: theorigenallywhite
  Date: 2024/12/24
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>购物车</title>
    <link rel="stylesheet" href="css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="css/common.css" type="text/css">
    <link rel="stylesheet" href="css/cart.css" type="text/css">
    <c:if test="${not empty requestScope.message}">
        <script type="text/javascript">
            alert("${requestScope.message}");
        </script>
    </c:if>
</head>
<h3>商品</h3>
<div class="user">
    <strong>当前登录的用户为：${sessionScope.username}</strong>
    <form action="hello-servlet" method="get">
        <button type="submit" name="button" value="out" id="out">退出登录</button>
    </form>
    <form action="show-servlet" method="get">
        <button type="submit" name="button" value="back" class="green">返回</button>
    </form>
</div>
<body>
<table class="table table-striped">
    <tr>
        <th>商品图片</th>
        <th>商品名</th>
        <th>商品价格</th>
        <th>操作</th>
    </tr>
    <c:set var="now" value="0"/>
    <c:set var="sum" value="0"/>
    <c:set var="commodities" value="${requestScope.commodities}"/>
    <c:forEach var="commodity" items="${commodities}">
        <tr>
            <th><img src="${commodity.path}" class="img-fluid" alt="" width="200" height="200"></th>
            <th>${commodity.name}</th>
            <th>¥${commodity.price}</th>
            <th>
                <form action="cart-servlet" method="get">
                    <button type="submit" name="button" class="square" value="sub">-</button>
                    <label for="num"></label>
                    <input type="text" name="num" id="num" class="show" value="${requestScope.num[now]}" readonly>
                    <button type="submit" name="button" class="square" value="plus">+</button>
                    <input type="hidden" name="cid" id="cid" value="${commodity.getCid()}">
                </form>
            </th>
        </tr>
        <c:set var="sum" value="${sum+commodity.price*requestScope.num[now]}"/>
        <c:set var="now" value="${now+1}"/>
    </c:forEach>
</table>
<form class="pay" action="cart-servlet" method="get">
    <label for="money">总计金额为:</label>
    <input type="text" name="money" id="money" class="show" value="${sum}元" readonly><br>
    <button type="submit" class="btn btn-success" name="button" value="pay">确认订单</button>
</form>
</body>
</html>
