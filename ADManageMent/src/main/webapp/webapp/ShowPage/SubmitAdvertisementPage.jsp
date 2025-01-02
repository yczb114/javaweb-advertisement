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
    <div class="sidebar">
        <h2>选择操作</h2>
        <ul>
            <li><a href="#" onclick="showAdForm()">提交广告</a></li>
            <li><a href="#" onclick="showContent()">显示内容</a></li>
        </ul>
    </div>

    <!-- 中间和右侧显示区域 -->
    <div class="content-area">
        <!-- 动态内容部分 -->
        <div id="dynamicContent">
            <!-- 默认显示广告提交表单 -->
            <h3>广告提交</h3>
            <form action="./SubmitAd-servlet" method="POST" enctype="multipart/form-data">
                <input type="hidden" name="adName" value="${adName}">
                <label for="adTitle">广告标题：</label><br>
                <input type="text" id="adTitle" name="adTitle"><br><br>
                <label for="adContent">广告宣传语：</label><br>
                <textarea id="adContent" name="adContent"></textarea><br><br>
                <label for="adphoto">广告图片或logo</label><br>
                <input type="file" id="adphoto" name="adphoto"><br><br>
                <button type="submit">提交广告</button>
            </form>
        </div>


    </div>
</div>

<script>
    function showAdForm() {
        document.getElementById('dynamicContent').innerHTML = `
                <h3>广告提交</h3>
                <form action="./SubmitAd-servlet" method="POST" enctype="multipart/form-data">
                    <input type="hidden" name="adName" value="${adName}">
                    <label for="adTitle">广告标题：</label><br>
                    <input type="text" id="adTitle" name="adTitle"><br><br>
                    <label for="adContent">广告内容：</label><br>
                    <textarea id="adContent" name="adContent"></textarea><br><br>
                    <label for="adContent">广告图片或logo</label><br>
                    <input type="file" id="adphoto" name="adphoto"><br><br>
                    <label for="adContent">广告分类</label><br>
                    <input type="text” id="adTag" name="adTag"><br><br>
                    <button type="submit">提交广告</button>
                </form>
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
                <h3><em>你未投放任何广告</em></h3>
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
