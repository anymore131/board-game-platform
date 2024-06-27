<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2024/6/27
  Time: 下午6:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>主页面</title>
    <script>
        <c:if test="${sessionScope.error != null}">
        alert("${sessionScope.error}")
        ${sessionScope.remove("error")}
        </c:if>
    </script>
</head>
<body>
<div class="header">
    <div class="avatar">
        <img src="/user/showAvatar/${user.avatarFname}" alt="头像" class="avatar-body">
    </div>
</div>
<div class="body">
    <div></div>
</div>
<div class="footer"></div>
</body>
</html>
