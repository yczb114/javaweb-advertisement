<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link rel="stylesheet" type="text/css" href="style.css">

</head>
<body>
<h1><%= "欢迎来到广告投放与管理网站" %>
</h1>
<br/>

<form action="hello-servlet" method="post">
    <div>广告主登录</div>
    <input type="submit" name="action" value="Advertisers">
    <div>互联网站长登录</div>
    <input type="submit" name="action" value="InternetWebmaster">
    <div>管理员登录</div>
    <input type="submit" name="action" value="Administrator">
    <span>注册</span>
    <input type="submit" name="action" value="register">
</form>
</body>
</html>