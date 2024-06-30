<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2024/6/30
  Time: 下午4:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>添加图片</title>
    <script>
    <c:if test="${sessionScope.error != null}">
        alert("${sessionScope.error}")
        ${sessionScope.remove("error")}
    </c:if>
    </script>
    <style>
        .avatar{
            width: 60px;
            height: 60px;
            border-radius: 100%;
            border-color: #ffffff
        }
    </style>
</head>
<body>
<div class="header">
    <span class="avatar-body">
        <a href="/user/userHome">
            <img src="/user/showAvatar/${user.avatarFname}" alt="头像" class="avatar" title="个人中心">
        </a>
    </span>
    <span class="header-out">
        <a href="/login/login">退出</a>
    </span>
</div>
<div class="side">
    <a href="/user/index">首页</a>
    <a href="/club/clubHome?clubId=${club.id}">返回</a>
</div>
<div class="body">
    <form action="/club/upload" method="post" enctype="multipart/form-data">
        <input type="file" name="picture" multiple="multiple"><br>
        <input type="submit" value="上传">
    </form>
</div>
<div class="footer"></div>
</body>
</html>
