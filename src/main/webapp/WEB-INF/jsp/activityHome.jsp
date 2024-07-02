<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2024/6/30
  Time: 下午10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>${activity.activityName}</title>
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
    <div class="activity-img">
        <c:forEach var="picture" items="${pictures}">
            <img src="/user/showPic/${picture.fname}" alt="俱乐部图片">
        </c:forEach>
    </div>
    <div class="activity-body">
        <div class="activity-name">${activity.activityName}</div>
        <div class="activity-describe">
            <div class="activity-item">详细情况</div>
            <div class="activity-describe-item">俱乐部：<a href="/club/clubHome?clubId=${activity.clubId}">${activity.clubName}</a></div>
            <div class="activity-describe-item">地址：${club.province}&nbsp;&nbsp;${club.city}&nbsp;&nbsp;${activity.address}</div>
            <div class="activity-describe-item">
                标签：
                <c:forEach var="tag" items="${activity.tags}">
                    <a href="/user/search?search-text=${tag}&&search-target=2">${tag}</a>&nbsp;&nbsp;
                </c:forEach>
            </div>
            <div class="activity-describe-item">人数：${activity.number}</div>
            <div class="activity-describe-item">简介：${activity.introduction}</div>
            <div class="activity-describe-item">时间：
                ${activity.startTime}——${activity.endTime}
            </div>
        </div>
    </div>
    <div class="activity-comments">
        <div class="activity-item">评论</div>
        <c:if test="${activity.clubType != 1}">
            <div class="input-comments">
                <form action="/activity/insertComments" method="post">
                    <textarea name="comments-text"></textarea>
                    <input type="submit" value="提交">
                </form>
            </div>
        </c:if>
        <c:forEach var="comment" items="${comments}">
            <div class="comment-body">
                <div><a href="/user/otherHome?userId=${comment.userId}">${comment.userName}</a></div>
                <div>${comment.comments}</div>
                <div>${comment.commentsTime}</div>
            </div>
        </c:forEach>
        <div class="page">
            <c:if test="${commentsPageNo > 1}">
                <a href="/activity/activityHome?CommentsPage=${commentsPageNo - 1}&&activityId=${activity.id}">上一页</a>
            </c:if>
            <c:if test="${commentsPageNo < maxCommentsPage}">
                <a href="/activity/activityHome?CommentsPage=${commentsPageNo - 1}&&activityId=${activity.id}">下一页</a>
            </c:if>
        </div>
    </div>

</div>
<div class="footer"></div>
</body>
</html>
