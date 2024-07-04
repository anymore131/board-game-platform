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
        body {
            width: 100%;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
            background-color: #f2f2f2; /* 浅灰色背景 */
            border-bottom: 1px solid #ddd; /* 底部边框 */
        }
        .search-body form {
            display: flex;
            align-items: center;
            margin-right: 20px;
            margin-top: 10px;
        }

        input[type="text"] {
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        input[type="submit"] {
            padding: 5px 10px;
            background-color: #4CAF50; /* 绿色背景 */
            color: white; /* 白色文字 */
            border: none;
            border-radius: 3px;
            cursor: pointer;
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
            color: #333; /* 退出链接文字颜色 */
            text-decoration: none; /* 去掉下划线 */
            font-weight: bold; /* 加粗 */
        }

        /* 退出链接的hover样式 */
        .header-out a:hover {
            color: #007BFF; /* 蓝色文字 */
            text-decoration: underline; /* 鼠标悬停时下划线 */
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

        /* 主体内容样式 */
        .body {
            margin-left: 150px;
            margin-right: 150px;
            padding: 20px;
        }

        .club-img{
            width: 700px;
            height: 320px;
            margin: 0 auto;
            position: relative;
            text-align:center;
            overflow: hidden;
        }

        .club-img img {
            width: 700px;
            height: 320px;
            margin: 0 auto;
        }

        .activity-body {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .activity-describe-item {
            margin: 10px 0;
        }

        .activity-item {
            font-weight: bold;
            margin-top: 20px;
        }

        .activity-body {
            background-color: #f8f9fa;
            padding: 15px;
            border-radius: 8px;
            margin-bottom: 15px;
        }

        .input-comments textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            resize: vertical;
        }

        .comment-body {
            background-color: #fff;
            padding: 15px;
            border-radius: 8px;
            margin: 15px 0;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
        }

        .page a {
            padding: 8px 15px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            margin: 0 5px;
        }

        .page a:hover {
            background-color: #0056b3;
        }

        .footer {
            background-color: #333;
            color: #fff;
            text-align: center;
            padding: 20px;
            position: fixed;
            left: 0;
            bottom: 0;
            width: 100%;
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
    <a href="/activity/changeActivity">修改活动</a>
</div>
<div class="body">
    <c:if test="${pictures != null}">
        <div class="club-img">
            <ul class="box">
                <c:set var="state" scope="session" value="0"/>
                <c:forEach var="picture" items="${pictures}" varStatus="status">
                    <c:set var="state"  value="${state+1}" scope="session"/>
                    <li> <img class="img-slide img${state}" src="/user/showPic/${picture.fname}" alt="${state}"></li>
                </c:forEach>
            </ul>
            <ul id="circlebutton">
                <c:set var="state" scope="session" value="0"/>
                <c:forEach var="picture" items="${pictures}" varStatus="status">
                    <li>
                        <c:set var="state"  value="${state+1}" scope="session"/>
                        <div class="divEle" style="background: #FF0000;">${state}</div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <div class="activity-body">
        <div class="activity-name">${activity.activityName}</div>
        <div class="activity-attend">
            <c:if test="${activity.attended == 1}">
                <div class="activity-attend"><p>已参加</p></div>
                <div class="activity-attend">
                    <a href="/activity/userQuit?activityId=${activity.id}">退出</a>
                </div>
            </c:if>
            <c:if test="${activity.attended == 0}">
                <div class="activity-attend">
                    <a href="/activity/userAttend?activityId=${activity.id}">加入</a>
                </div>
            </c:if>
        </div>
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
                <form action="/activity/insertActivityComments" method="post">
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
