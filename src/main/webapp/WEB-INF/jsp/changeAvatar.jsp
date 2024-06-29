<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2024/6/29
  Time: 下午3:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="header">
    <span class="avatar-body">
        <a href="/user/userHome"><img src="/user/showAvatar/${user.avatarFname}" alt="头像" class="avatar"></a>
    </span>
    <span class="header-out">
        <a href="/login/login">退出</a>
    </span>
</div>
<div class="body">
    <form action="/user/changeAvatar" method="post" enctype="multipart/form-data">
        <input type="file" name="newAvatar" value="选择头像"><br>
        <input type="submit" value="更换头像">
    </form>
</div>
<div class="footer">

</div>
</body>
</html>
