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
    <div class="avatar-body">
        <img src="/user/showAvatar/${user.avatarFname}" alt="头像" class="avatar">
        <a href="/user/userHome">个人主页</a>
    </div>
</div>
<div class="body">
    <div class="search-body">
        <form action="/user/search" method="post">
            <select id="search_target" name="search_target" >
                <option value="0">俱乐部</option>
                <option value="1">活动</option>
                <option value="2">游戏标签</option>
                <option value="3">用户</option>
            </select>
            <input type="text" name="search-text">
            <input type="text" value="提交" name="action">
        </form>
    </div>
    <div class="club-activity">
        <c:if test="${activities != null}">
            <c:forEach var="activity" items="${activities}">
                <div class="activity">
                    <span><a href="">${activity.name}</a></span>
                    <span><a href="">${activity.clubName}</a></span>
                    <span>
                        <c:forEach var="tag" items="${activity.tags}">
                            <a href="/user/serarch?search_target=2&&search-text=${tag}">${tag}&nbsp;&nbsp;</a>
                        </c:forEach>
                    </span>
                    <span>${activity.number}</span>
                    <span>
                        ${activity.startTime}——${activity.endTime}
                    </span>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${activities == null}">
            还没有活动！
        </c:if>
    </div>
</div>
<div class="footer"></div>
</body>
</html>
