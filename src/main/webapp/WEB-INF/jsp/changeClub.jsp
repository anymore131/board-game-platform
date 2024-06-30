<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2024/6/30
  Time: 下午3:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>修改俱乐部</title>
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
    <form action="/club/changeClub" method="post">
        <table>
            <tr>
                <td>俱乐部名：</td>
                <td colspan="2"><input type="text" name="clubName" value="${club.clubName}"></td>
            </tr>
            <tr>
                <td>简介：</td>
                <td colspan="2"><textarea name="introduction" value="${club.introduction}"></textarea></td>
            </tr>
            <tr>
                <td>位置：</td>
                <td><input type="text" name="province" value="${club.province}"></td>
                <td><input type="text" name="city" value="${club.city}"></td>
            </tr>
            <tr>
                <td>标签</td>
            </tr>
            <tr>
                <td colspan="2">
                    <c:forEach var="game" items="${games}">
                        <c:set var="gameCheck" scope="session" value="0"/>
                        <c:forEach var="tag" items="${club.tags}">
                            <c:if test="${game == tag}">
                                <c:set var="gameCheck" scope="session" value="1"/>
                            </c:if>
                        </c:forEach>
                        <c:if test="${gameCheck == 1}">
                            <input type="checkbox" name="tags" value="${game}" checked>${game}
                        </c:if>
                        <c:if test="${gameCheck == 0||gameCheck == null}">
                            <input type="checkbox" name="tags" value="${game}">${game}
                        </c:if>
                        <c:set var="gameCheck" scope="session" value="0"/>
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
