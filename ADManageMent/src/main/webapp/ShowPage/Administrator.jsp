<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>广告管理系统</title>

</head>
<body>


<div class="container">
    <!-- 左侧选择栏 -->
    <p>你好管理员</p>
    <div class="sidebar">
        <h2>选择操作</h2>
        <ul>
            <li><a href="#" onclick="showinsForm()">显示广告主情况</a></li>
            <li><a href="#" onclick="showContent()">显示内容</a></li>
        </ul>
    </div>

    <!-- 中间和右侧显示区域 -->
    <div class="content-area">
        <!-- 动态内容部分 -->
        <div id="dynamicContent">
            <!-- 默认显示广告提交表单 -->

            <h3>目前有以下广告主</h3>
            <c:forEach var="item" items="${ins}">
                <div class="ad-container">
                    <h4>广告主：${item.getInternetWebmasterName()}</h4>
                    <p>收益：${item.adclick*0.5}</p>
                    <p>联系方式：${item.getInternetWebmasterEmail()}</p>

                </div>
                <br><br>
            </c:forEach>
        </div>


    </div>
</div>

<script>
    function showinsForm() {
        document.getElementById('dynamicContent').innerHTML = `
        <div id="dynamicContent">
                <h3>目前有以下广告主</h3>
                <c:forEach var="item" items="${ins}">
                    <div class="ad-container">
                      <h4>广告主：${item.getInternetWebmasterName()}</h4>
                    <p>收益：${item.getAdclick()*0.5}</p>
                    <p>联系方式：${item.getInternetWebmasterEmail()}</p>
                    </div>
                    <br><br>
                </c:forEach>
        </div>
            `;
    }

    function showContent() {
        document.getElementById('dynamicContent').innerHTML = `
            <c:if test="${not empty ads}">
                <h3><em>你已经投放以下广告</em></h3>
                <c:forEach var="item" items="${ads}">
                    <div class="ad-container">
                        <h4>${item.adTitle}</h4>
                        <p>${item.adContent}</p>
                        <c:if test="${not empty item.base64Photo}">
                            <img src="data:image/jpeg;base64,${item.base64Photo}" alt="+广告图片" style="max-width: 100%;"/>
                        </c:if>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${empty ads}">
                <h3><em>投放任何广告</em></h3>
            </c:if>
        `;
    }

</script>

<style>
    /* 基本布局 */
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    .container {
        display: flex;
    }

    .sidebar {
        width: 250px;
        background-color: #f4f4f4;
        padding: 20px;
        height: 100vh;
    }

    .sidebar h2 {
        margin-top: 0;
    }

    .sidebar ul {
        list-style-type: none;
        padding: 0;
    }

    .sidebar ul li {
        margin: 10px 0;
    }

    .sidebar ul li a {
        text-decoration: none;
        color: #333;
        font-size: 16px;
    }

    .content-area {
        flex: 1;
        padding: 20px;
        background-color: #fff;
    }

    #dynamicContent {
        padding: 20px;
        background-color: #f9f9f9;
        border: 1px solid #ccc;
        border-radius: 8px;
    }

    form {
        max-width: 500px;
        margin: 0 auto;
    }

    input[type="text"], textarea {
        width: 100%;
        padding: 10px;
        margin: 10px 0;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    button {
        padding: 10px 20px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    button:hover {
        background-color: #0056b3;
    }

</style>
</body>
</html>
