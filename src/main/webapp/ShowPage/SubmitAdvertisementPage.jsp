<%@ page contentType="text/html; charset=UTF-8" language="java" %>
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
            <form action="SubmitAd-servlet" method="POST">
                <label for="adTitle">广告标题：</label><br>
                <input type="text" id="adTitle" name="adTitle"><br><br>
                <label for="adContent">广告宣传语：</label><br>
                <textarea id="adContent" name="adContent"></textarea><br><br>
                <label for="adContent">广告图片或logo</label><br>
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
                <form action="submitAd.jsp" method="POST">
                    <label for="adTitle">广告标题：</label><br>
                    <input type="text" id="adTitle" name="adTitle"><br><br>
                    <label for="adContent">广告内容：</label><br>
                    <textarea id="adContent" name="adContent"></textarea><br><br>
                    <label for="adContent">广告图片或logo</label><br>
                    <input type="file" id="adphoto" name="adphoto"><br><br>
                    <button type="submit">提交广告</button>
                </form>
            `;
    }

    function showContent() {
        document.getElementById('dynamicContent').innerHTML = `
                <h3>显示内容</h3>
                <p>这里显示一些内容，例如最新的广告或其他信息。</p>
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
