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
        body {
            width: 100%;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 15px;
            background-color: #343a40;
            color: #fff;
        }

        .search-body form {
            display: flex;
            align-items: center;
            margin-right: 20px;
            float: right;
        }

        .search-body input[type="text"] {
            padding: 8px;
            border: 1px solid #444;
            border-radius: 5px;
            background-color: #444;
            color: #fff;
        }

        .search-body input[type="submit"] {
            padding: 8px 15px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .header-out a {
            color: #fff;
            text-decoration: none;
            font-weight: bold;
        }

        .header-out a:hover {
            text-decoration: underline;
        }

        .side {
            margin-left: 0;
            width: 140px;
            float: left;
            background-color: #f1f1f1;
            padding: 10px;
            text-align: center;
        }

        .side a {
            display: block;
            padding: 10px 0;
            text-decoration: none;
            color: black;
        }

        .side a:hover {
            background-color: #e6e6e6;
            color: #333333;
        }

        .body {
            margin-left: 150px;
            padding: 20px;
        }

        .avatar {
            width: 60px;
            height: 60px;
            border-radius: 50%;
            border: 2px solid #fff;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.3);
        }

        .club-img img {
            max-width: 100%;
            height: auto;
            border-radius: 8px;
            margin-bottom: 15px;
        }

        .club-body {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .club-describe-item {
            margin: 10px 0;
        }

        .club-item {
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
            padding: 15px;
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
    <c:if test="${club.clubType == 1}">
        <a href="/activity/createActivity">新建活动</a>
    </c:if>
</div>
<div class="body">
    <div class="club-img">
        <c:forEach var="picture" items="${pictures}">
            <img src="/user/showPic/${picture.fname}" alt="俱乐部图片">
        </c:forEach>
    </div>
    <div class="club-body">
        <div class="club-name">${club.clubName}</div>
        <div class="club-describe">
            <div class="club-item">详细情况</div>
            <div class="club-describe-item">地址：${club.province}&nbsp;&nbsp;${club.city}</div>
            <div class="club-describe-item">创建时间:${club.createTime}</div>
            <div class="club-describe-item">
                标签：
                <c:forEach var="tag" items="${club.tags}">
                    <a href="/user/search?search-text=${tag}&&search-target=2">${tag}</a>&nbsp;&nbsp;
                </c:forEach>
            </div>
            <div class="club-describe-item">人数：${club.number}</div>
            <div class="club-describe-item">简介：${club.introduction}</div>
        </div>
        <c:if test="${club.clubType == 1}">
            <a href="/club/changeClub" class="club-describe-item">修改俱乐部信息</a><br>
            <a href="/club/uploadClubPicture" class="club-describe-item">上传俱乐部照片</a><br>
            <div class="club-item">成员</div>
            <c:forEach var="cj" items="${userJoins}">
                <span><a href="/user/otherHome?userId=${cj.userId}">${cj.userName}</a></span>
                <span>${cj.joinTime}</span>
                <c:if test="${cj.clubType == 1}"><span>管理成员</span></c:if>
                <c:if test="${cj.clubType == 0}"><span>普通成员</span></c:if>
            </c:forEach>
        </c:if>
    </div>
    <div class="club-activity">
        <div class="club-item">活动</div>
        <c:forEach var="activity" items="${activities}">
            <div class="activity-body">
                <div><a href="/activity/activityHome?activityId=${activity.id}">${activity.activityName}</a></div>
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
    </div>
    <div class="club-comments">
        <div class="club-item">评论</div>
        <c:if test="${club.clubType == 0}">
            <div class="input-comments">
                <form action="/club/insertComments" method="post">
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
                <a href="/club/clubHome?CommentsPage=${commentsPageNo - 1}&&clubId=${club.id}">上一页</a>
            </c:if>
            <c:if test="${commentsPageNo < maxCommentsPage}">
                <a href="/club/clubHome?CommentsPage=${commentsPageNo - 1}&&clubId=${club.id}">下一页</a>
            </c:if>
        </div>
    </div>
</div>
<div class="footer"></div>
</body>
</html>
