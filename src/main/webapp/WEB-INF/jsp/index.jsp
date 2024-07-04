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
    <style>
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        min-height: 100vh;
        position: relative;
        background-color: #f8f9fa;
    }

    .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 10px;
        background-color: #f2f2f2; /* 浅灰色背景 */
        border-bottom: 1px solid #ddd; /* 底部边框 */
    }

    .header-out a {
        color: #333; /* 退出链接文字颜色 */
        text-decoration: none; /* 去掉下划线 */
        font-weight: bold; /* 加粗 */
    }

    /* 退出链接的hover样式 */
    .header-out a:hover {
        color: #007BFF; /* 蓝色文字 */
        text-decoration: underline; /* 鼠标悬停时下划线 */
    }

    .avatar-body {
        /* 不需要指定宽度，因为我们想要它根据内容自适应大小 */
        display: flex; /* 使用flex布局使头像和链接并排显示 */
        align-items: center; /* 垂直居中 */
        margin-right: 10px; /* 与搜索表单之间留一些空间 */
    }

    .avatar {
        width: 50px; /* 头像宽度 */
        height: 50px; /* 头像高度 */
        border-radius: 50%; /* 圆形头像 */
        /* margin-left: 1200px; 这个设置是错误的，应该去掉 */
        margin-right: 10px; /* 与链接之间留一些空间 */
        vertical-align: middle; /* 垂直居中（在这个上下文中可能不需要，因为已经使用了align-items: center） */
        border: 2px solid #ffffff; /* 为头像添加一个边框，如果需要的话 */
    }

    .header-out a {
        color: black;
        text-decoration: none;
    }

    .side {
        margin-left: 0;
        width: 140px;
        float: left;
        padding: 10px;
        text-align: center;
    }

    .side a {
        display: block;
        padding: 10px 0;
        text-decoration: none;
        color: black;
    }

    .side a p{
        margin: 0;
    }

    .side a:hover {
        background-color: #e6e6e6;
        color: #333333;
    }

    .side a:hover p{
        color: #007bff;
    }

    .body {
        margin-left: 150px;
        margin-right: 150px;
        padding: 20px;
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
        margin-bottom: 20px;
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
    <a href="/club/createClub"><p>创建俱乐部</p></a>
    <c:if test="${user.type==1}">
        <a href=""><p>管理</p></a>
    </c:if>
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
        <c:if test="${tjClubs != null}">
            <div class="item">推荐俱乐部</div>
            <c:forEach items="${tjClubs}" var="tjClub">
                <div class="club-body">
                    <div><a href="/club/clubHome?clubId=${tjClub.id}">${tjClub.clubName}</a></div>
                    <div>标签：
                        <c:forEach var="tag" items="${tjClub.tags}">
                            <a href="/user/search?search-target=2&&search-text=${tag}">${tag}</a>&nbsp;&nbsp;
                        </c:forEach>
                    </div>
                    <span>俱乐部人数：${tjClub.number}</span>
                    <span>位置：
                        ${tjClub.province}&nbsp;&nbsp;${tjClub.city}
                    </span>
                    <div>
                        <c:if test="${tjClub.joined == 0}">
                            <a href="/user/userJoin?clubId=${tjClub.id}">加入</a>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${tjClubs == null}">
            <div class="item"><p>暂无可推荐的俱乐部</p></div>
        </c:if>
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
