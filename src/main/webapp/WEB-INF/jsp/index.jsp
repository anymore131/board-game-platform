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
<head> <style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        min-height: 100vh;
        position: relative;
        background-color: #f8f9fa;
    }

    .header {
        background-color: #343a40;
        color: #fff;
        padding: 15px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .avatar {
        width: 60px;
        height: 60px;
        border-radius: 50%;
        border: 2px solid #fff;
    }

    .header-out a {
        color: #fff;
        text-decoration: none;
    }

    .side {
        background-color: #444;
        padding: 20px;
        color: #fff;
    }

    .side a {
        display: block;
        margin-bottom: 10px;
        text-decoration: none;
        color: #fff;
    }

    .body {
        padding: 20px;
        padding-left: 30px;
    }

    .search-body {
        margin-bottom: 20px;
    }

    .search-body form {
        display: flex;
        align-items: center;
    }

    .search-body select,
    .search-body input[type="text"],
    .search-body input[type="submit"] {
        padding: 10px;
        font-size: 16px;
        border: 1px solid #ccc;
    }

    .search-body select {
        margin-right: 10px;
    }

    .search-body input[type="submit"] {
        background-color: #007bff;
        color: #fff;
        border: none;
        cursor: pointer;
    }

    .club-activity {
        padding: 20px;
        background-color: #fff;
        border: 1px solid #ccc;
        position: relative;
    }

    .club-activity.item {
        font-size: 18px;
        margin-bottom: 10px;
        position: absolute;
        left: 0;
        top: 0;
    }

    .activity-body {
        margin-bottom: 20px;
        padding: 15px;
        border: 1px solid #ddd;
    }

    .activity-body a {
        text-decoration: none;
        color: #007bff;
    }

    .footer {
        background-color: #343a40;
        color: #fff;
        padding: 15px;
        text-align: center;
        width: 100%;
        position: absolute;
        bottom: 0;
    }
</style>
    <title>主页面</title>
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
    <a href="/club/createClub">创建俱乐部</a>
</div>
<div class="body">
    <div class="search-body">
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
    </div>
    <div class="club-activity">

        <c:if test="${activities != null}">
            <div class="item">关注的活动</div>
            <c:forEach var="activity" items="${activities}">
                <div class="activity-body">
                    <div><a href="/activity/activityHome?activityId=${activity.id}">${activity.activityName}</a></div>
                    <div>俱乐部名：<a href="/club/clubHome?clubId=${activity.clubId}">${activity.clubName}</a></div>
                    <div>标签：
                        <c:forEach var="tag" items="${activity.tags}">
                            <a href="/user/search?search-target=2&&search-text=${tag}">${tag}</a>&nbsp;&nbsp;
                        </c:forEach>
                    </div>
                    <span>参加人数：${activity.number}</span>
                    <span>时间：
                        ${activity.startTime}——${activity.endTime}
                    </span>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${activities == null}">
            <div class="item">还没有关注的活动</div>
        </c:if>
    </div>
</div>
<div class="footer"></div>
</body>
</html>
