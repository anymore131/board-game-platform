<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2024/6/30
  Time: 下午8:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>创建活动</title>
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
    <a href="/club/clubHome?clubId=${clubId}">返回</a>
</div>
<div class="body">
    <form action="/activity/createActivity" method="post">
        <table>
            <tr>
                <td>活动名：</td>
                <td colspan="2"><input type="text" name="activityName"></td>
            </tr>
            <tr>
                <td>活动介绍：</td>
                <td colspan="2"><textarea name="introduction"></textarea></td>
            </tr>
            <tr>
                <td>位置：</td>
                <td colspan="2"><input type="text" name="address"></td>
            </tr>
            <tr>
                <td>标签</td>
                <td></td>
                <td></td>
            </tr>
            <tr>
                <td colspan="3">
                    <c:forEach var="game" items="${games}">
                        <input type="checkbox" name="tags" value="${game}">${game}
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td>开始时间：</td>
                <td colspan="w"><input type="date" name="startTime"></td>
            </tr>
            <tr>
                <td>结束时间：</td>
                <td colspan="2"><input type="date" name="endTime"></td>
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
