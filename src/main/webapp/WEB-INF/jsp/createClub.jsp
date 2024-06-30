<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2024/6/30
  Time: 上午1:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>创建俱乐部</title>
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
</div>
<div class="body">
    <form action="/club/createClub" method="post">
        <table>
            <tr>
                <td>俱乐部名：</td>
                <td colspan="2"><input type="text" name="clubName"></td>
            </tr>
            <tr>
                <td>简介：</td>
                <td colspan="2"><textarea name="introduction"></textarea></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="text" name="province"></td>
                <td><input type="text" name="city"></td>
            </tr>
            <tr>
                <td>标签</td>
            </tr>
            <tr>
                <td colspan="2">
                    <c:forEach var="game" items="${games}">
                        <input type="checkbox" name="tags" value="${game}">${game}
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td><input type="submit" value="提交"></td>
            </tr>
        </table>
    </form>
</div>
<div class="footer"></div>
</body>
</html>
