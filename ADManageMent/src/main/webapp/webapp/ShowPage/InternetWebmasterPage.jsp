<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><%--
  Created by IntelliJ IDEA.
  User: 33395
  Date: 2025/1/1
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>互联网站主</title>
</head>
<div class="container">
    <!-- 左侧选择栏 -->
    <div class="sidebar">
        <h1>互联网站长</h1>
        <h2>选择操作</h2>
        <ul>
            <li><a href="#" onclick="showAdForm()">广告接口</a></li>
            <li><a href="#" onclick="showContent()">显示内容</a></li>
        </ul>
    </div>

    <!-- 中间和右侧显示区域 -->
    <div class="content-area">
        <!-- 动态内容部分 -->
        <div id="dynamicContent">
            <!-- 默认显示接口 -->
            <h3>广告接口</h3>
            <div class="justify-content-center">
                                       <pre><code>
                                fetch('https://api.example.com/data')
                                  .then(response => response.json())
                                  .then(data => {
                                    console.log(data);
                                  })
                                  .catch(error => {
                                    console.error('Error:', error);
                                  });
                                    </code></pre>
                <div>fetch内的网址为：http://116.62.49.213:8080/ADManageMent-1.0-SNAPSHOT/AdResponse-servlet</div>
                <div>传入参数tag，获取参数广告图片和链接请使用fetch请求上述网址</div>
                <div>注：网站返回形式为Json数据</div>
                <div>包含:
                    <div>base64图片数据，参数名称为base64photo,转换后的图片类型为jpeg</div>
                    <div>广告编号，参数名称adid</div>
                    <div>广告链接，参数名称adurl</div>
                </div>

                <div>当用户点击广告时，需要使用fetch向http://116.62.49.213:8080/ADManageMent-1.0-SNAPSHOT/ClickRate-servlet</div>
                <div>传递adid和username(您注册的用户名）</div>
        </div>

        </div>
    </div>
</div>

<script>
    function showAdForm() {
        document.getElementById('dynamicContent').innerHTML = `
                <h3>广告接口</h3>
                <div class="justify-content-center">
               <pre><code>
        fetch('https://api.example.com/data')
          .then(response => response.json())
          .then(data => {
            console.log(data);
          })
          .catch(error => {
            console.error('Error:', error);
          });
            </code></pre>
            <div>fetch内的网址为：http://116.62.49.213:8080/ADManageMent-1.0-SNAPSHOT/AdResponse-servlet</div>
            <div>传入参数tag，获取参数广告图片和链接请使用fetch请求上述网址</div>
            <div>注：网站返回形式为Json数据</div>
            <div>包含:
                <div>base64图片数据，参数名称为base64photo,转换后的图片类型为jpeg</div>
                <div>广告编号，参数名称adid</div>
                <div>广告链接，参数名称adurl</div>
            </div>

            <div>当用户点击广告时，需要使用fetch向http://116.62.49.213:8080/ADManageMent-1.0-SNAPSHOT/ClickRate-servlet</div>
            <div>传递adid和username(您注册的用户名）</div>
            `;
    }

    function showContent() {
        document.getElementById('dynamicContent').innerHTML = `
            <c:if test="${not empty ads}">
                <h3><em>已经投放以下广告</em></h3>
                 <c:if test="${income!=null}">
                            <p>收益：${income}</p>
                 </c:if>
                <c:forEach var="item" items="${ads}">
                    <div class="ad-container">
                        <h4>${item.adTitle}</h4>
                        <p>${item.adContent}</p>
                        <c:if test="${not empty item.base64Photo}">
                            <img src="data:image/jpeg;base64,${item.base64Photo}" alt="+广告图片" style="max-width: 100%;"/>
                        </c:if>
                        <c:if test="${item.adsend==0}">
                        <p>点击率：0</p>
                        </c:if>
                        <c:if test="${item.adsend!=0}">
                        <p>点击率：${(item.adclick*100.0)/item.adsend}%</p>
                        </c:if>

                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${empty ads}">
                <h3><em>目前未投放任何广告</em></h3>
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
</html>
