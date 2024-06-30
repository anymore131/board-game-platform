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
            margin-left: 100px;
            width: 140px; /* 侧边栏宽度 */
            float: left; /* 浮动到左边 */
            background-color: #f8f9fa; /* 背景色 */
            padding: 10px; /* 内边距 */
            text-align: center;
        }

        .side a {
            display: block; /* 链接作为块级元素显示 */
            padding: 5px 0; /* 上下内边距 */
            text-decoration: none; /* 去除下划线 */
            color: #333; /* 文本颜色 */
        }

        /* 主体内容样式 */
        .body {
            margin-left: 150px; /* 左边距，留出侧边栏的空间 */
        }
        .avatar{
            width: 60px;
            height: 60px;
            border-radius: 100%;
            border-color: #ffffff
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
