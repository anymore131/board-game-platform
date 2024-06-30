<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2024/6/30
  Time: 下午5:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${otherUser.userName}的空间</title>
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
    <span class="search-body">
        <form action="/user/search" method="post">
            <select id="search-target" name="search-target" >
                <option value="0">俱乐部</option>
                <option value="1">活动</option>
                <option value="2">游戏标签</option>
                <option value="3">用户</option>
            </select>
            <input type="text" name="search-text">
            <input type="submit" value="提交" name="action">
        </form>
    </span>
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
    <span class="user-message">
        <img src="/user/showAvatar/${otherUser.avatarFname}" alt="头像" class="user-avatar" title="修改头像">
    </span>
    <span>
        <table>
            <tr>
                <td>${otherUser.userName}</td>
            </tr>
            <tr>
                <td>${otherUser.age}</td>
                <td>${otherUser.gender}</td>
            </tr>
            <tr>
                <td colspan="2">所在城市：&nbsp;&nbsp;${otherUser.province}&nbsp;${otherUser.city}</td>
            </tr>
            <tr>
                <td colspan="2">简介：${otherUser.introduction}</td>
            </tr>
            <tr>
                <td>手机号:${otherUser.mobile}</td>
                <td>邮箱：${otherUser.email}</td>
            </tr>
            <tr>
                <td>QQ:${otherUser.QQ}</td>
                <td>微信：${otherUser.weixin}</td>
            </tr>
        </table>
    </span>
</div>
<div class="footer"></div>
</body>
</html>
