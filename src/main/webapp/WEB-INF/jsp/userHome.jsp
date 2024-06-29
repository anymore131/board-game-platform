<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2024/6/29
  Time: 下午3:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>用户中心</title>
    <script>
        <c:if test="${sessionScope.error != null}">
        alert("${sessionScope.error}")
        ${sessionScope.remove("error")}
        </c:if>
    </script>
    <style>
        .user-avatar{
            height: 120px;
            width: 120px;
            border-radius: 100%;
            border-color: #ffffff
        }
    </style>
</head>
<body>
<div class="header">
    <span class="header-out">
        <a href="/login/login">退出</a>
    </span>
</div>
<div class="body">
    <span class="user-message" >
        <a href="/user/changeAvatar">
            <img src="/user/showAvatar/${user.avatarFname}" alt="头像" class="user-avatar" title="修改头像">
        </a>
    </span>
    <span>
        <table>
            <tr>
                <td>${user.userName}</td>
                <td><a href="">修改个人信息</a></td>
            </tr>
            <tr>
                <td>${user.age}</td>
                <td>${user.gender}</td>
            </tr>
            <tr>
                <td>所在城市：&nbsp;&nbsp;${user.province}&nbsp;${user.city}</td>
                <td></td>
            </tr>
            <tr>
                <td colspan="2">简介：${user.introduction}</td>
            </tr>
            <tr>
                <td>手机号:${user.mobile}</td>
                <td>邮箱：${user.email}</td>
            </tr>
            <tr>
                <td>QQ:${user.QQ}</td>
                <td>微信：${user.weixin}</td>
            </tr>
        </table>
    </span>
    <div class="user-club">
        我加入的俱乐部
        <c:forEach var="club" items="${clubs}">
            <div class="club">
                <span class="club-name">
                    <a href="/club/clubHome?clubId=${club.id}">${club.clubName}</a>
                </span>
                <div class="club-tags">
                    标签：
                    <c:forEach var="tag" items="${club.tags}">
                        <a href="/user/serarch?search_target=2&&search-text=${tag}">${tag}</a>&nbsp;&nbsp;
                    </c:forEach>
                </div>
                <div class="club-join-time">
                    加入时间：${club.joinTime}
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="user-manage">
        我管理的俱乐部
        <c:forEach var="club" items="${manageClubs}">
            <div class="club">
                <span class="club-name">
                    <a href="/club/clubHome?clubId=${club.id}">${club.clubName}</a>
                </span>
                <div class="club-tags">
                    标签：
                    <c:forEach var="tag" items="${club.tags}">
                        <a href="/user/serarch?search_target=2&&search-text=${tag}">${tag}</a>&nbsp;&nbsp;
                    </c:forEach>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="user-activity">
        所有活动
    </div>
</div>
<div class="footer"></div>
</body>
</html>
