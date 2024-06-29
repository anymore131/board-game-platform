<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2024/6/29
  Time: 下午11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>${club.clubName}</title>
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
<div class="body">
    <div class="club-img">
        <c:forEach var="picture" items="${pictures}">
            <img src="/user/showPic/${picture.fname}" alt="俱乐部图片">
        </c:forEach>
    </div>
    <div class="club-body">
        <div class="club-name">${club.clubName}</div>
        <div class="club-item">地址：${club.province}&nbsp;&nbsp;${club.city}</div>
        <div class="club-item">创建时间:${club.createTime}</div>
        <div class="club-item">
            标签：
            <c:forEach var="tag" items="${club.tags}">
                <a href="/user/search?search-text=${tag}&&search-target=2">${tag}</a>&nbsp;&nbsp;
            </c:forEach>
        </div>
        <div class="club-item">人数：${club.number}</div>
        <div class="club-item">简介：${club.introduction}</div>
        <c:if test="${club.clubType == 1}">
            <a href="/club/changeClub">修改俱乐部信息</a><br>
            成员：<br>
            <c:forEach var="cj" items="${userJoins}">
                <span>${cj.userName}</span>
                <span>${cj.joinTime}</span>
                <c:if test="${cj.clubType == 1}"><span>管理成员</span></c:if>
                <c:if test="${cj.clubType == 0}"><span>普通成员</span></c:if>
            </c:forEach>
        </c:if>
    </div>
</div>
<div class="footer"></div>
</body>
</html>
