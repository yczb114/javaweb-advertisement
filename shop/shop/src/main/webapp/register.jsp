<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" href="css/register.css" type="text/css">
    <c:if test="${not empty requestScope.warning}">
        <script type="text/javascript">
            alert("${requestScope.warning}");
        </script>
    </c:if>
</head>
<body>
<form action="register-servlet" method="get">
    <table class="center">
        <tr>
            <td>
                <label for="username">请输入用户名:</label>
                <input type="text" id="username" name="username">
            </td>
        </tr>
        <tr>
            <td>
                <label for="password">请输入密码:</label>
                <input type="password" id="password" name="password">
            </td>
        </tr>
        <tr>
            <td>
                <label for="repassword">请再次输入密码:</label>
                <input type="password" id="repassword" name="repassword">
            </td>
        </tr>
        <tr>
            <td>
                <button type="submit" name="button" value="register">注册</button>
                <button type="submit" name="button" value="back">返回</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
