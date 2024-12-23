<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
  <title>登录</title>
  <link rel="stylesheet" href="css/index.css" type="text/css">
  <c:if test="${not empty requestScope.warning}">
    <script type="text/javascript">
      alert("${requestScope.warning}");
    </script>
  </c:if>
</head>
<body>
<form action="hello-servlet" method="get" id="form">
  <table class="center">
    <tr>
      <td>
        <label for="username">请输入用户名:</label>
        <input type="text" id="username" name="username" value=${cookie.username.value}>
      </td>
    </tr>
    <tr>
      <td>
        <label for="password">请输入密码:</label>
        <input type="password" id="password" name="password" value=${cookie.password.value}>
      </td>
    </tr>
    <tr>
      <td>
        <label for="remember">记住我
          <input type="checkbox" name="remember" id="remember" value="remember">
        </label>

      </td>
    </tr>
    <tr>
      <td>
        <button type="submit" name="button" id="login" value="login">登录</button>
        <button type="submit" name="button" value="register">注册</button>
      </td>
    </tr>
  </table>
</form>
<script type="text/javascript" src="js/encrypt.js"></script>
</body>
</html>