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
        * {
            margin: 0;
            padding: 0;
        }
        .box {list-style-type: none;}
        #circlebutton {
            position: absolute;
            bottom: 20px;
            left: 260px;
            list-style-type: none;
            text-align: center;
        }

        #circlebutton li {
            margin-left: 10px;
            float: left;
        }
        #circlebutton li div {
            width: 20px;
            height: 20px;
            background: #DDDDDD;
            border-radius: 10px;
            cursor: pointer;
            text-align: center;
            vertical-align: middle;
        }
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
            padding: 10px;
            background-color: #f2f2f2; /* 浅灰色背景 */
            border-bottom: 1px solid #ddd; /* 底部边框 */
        }

        .search-body {
            width: 80%;

            /*flex: 1; !* 占据尽可能多的空间 *!*/
            display: flex; /* 允许子元素并排显示 */
            align-items: center; /* 垂直居中 */
            justify-content: flex-end; /* 子元素靠右对齐 */
        }

        .search-body form {
            display: flex;
            align-items: center;
            margin-right: 20px;
            margin-top: 10px;
        }

        #search-target {
            margin-right: 10px; /* 下拉框和文本框之间留一些空间 */
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
            color: black; /* 退出链接文字颜色 */
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
            background-color: #6c6a6a;
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

        .club-img{
            width: 700px;
            height: 320px;
            position: relative;
            text-align:center;
            overflow: hidden;
            margin: 0 auto 20px;
        }

        .club-img img {
            width: 75%;
            object-fit: cover;
            margin: 0 auto;
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
    <a href="/user/index"><p>首页</p></a>
    <c:if test="${club.clubType == 1}">
        <a href="/activity/createActivity"><p>新建活动</p></a>
    </c:if>
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
    <div class="club-body">
        <div class="club-name">${club.clubName}</div>
        <div class="club-join">
            <c:if test="${club.joined == 1}">
                <div class="club-join"><p>已参加</p></div>
                <div class="club-join">
                    <a href="/user/userOut?clubId=${club.id}">退出</a>
                </div>
            </c:if>
            <c:if test="${club.joined == 0}">
                <div class="club-join">
                    <a href="/user/userJoin?clubId=${club.id}">加入</a>
                </div>
            </c:if>
        </div>
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
<%--                <div>标签：--%>
<%--                    <c:forEach var="tag" items="${activity.tags}">--%>
<%--                        <a href="/user/search?search-target=2&&search-text=${tag}">${tag}</a>&nbsp;&nbsp;--%>
<%--                    </c:forEach>--%>
<%--                </div>--%>
<%--                <span>参加人数：${activity.number}</span>--%>
                <span>时间：
                    ${activity.startTime}——${activity.endTime}
                </span>
                <c:if test="${club.clubType == 1}">
                    <a href="/club/activityCancel?activityId=${activity.id}">取消活动</a>
                </c:if>
            </div>
        </c:forEach>
    </div>
    <div class="club-comments">
        <div class="club-item">评论</div>
        <c:if test="${club.clubType == 0}">
            <div class="input-comments">
                <form action="/club/insertComments" method="post">
                    <textarea name="comments-text"></textarea>
                    <input type="submit" value="提交" >
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
<script type="text/javascript">
    var index = 0;
    var divCon = document.getElementsByClassName("divEle");
    var imgEle = document.getElementsByClassName("img-slide");
    var divPrev = document.getElementById("prev");
    var divNext = document.getElementById("next");
    for (var i = 0; i < divCon.length; i++) {
        divCon[i].index = i;
        divCon[i].onmouseover = function() {
            if (index == this.index){return;}
            index = this.index;
            changeImg();
            clearInterval(change1);
        }
    }
    function autoChangeImg() {
        index++;
        changeImg();
    }
    var change1 = setInterval(autoChangeImg, 3000);
    function changeImg() {
        if (index >= imgEle.length) {
            index = 0;
        }
        for (var i = 0; i < imgEle.length; i++) {
            imgEle[i].style.display = 'none';
            divCon[i].style.background = "#DDDDDD";
        }
        imgEle[index].style.display = 'block';
        divCon[index].style.background = "#FF0000";
    }
    divPrev.onclick = function() {
        clearInterval(change1);
        if (index > 0) {index--} else {index = 4;}
        changeImg();
    };
    divNext.onclick = function() {
        clearInterval(change1);
        if (index >= 4) {index = 0;} else {index++;}
        changeImg();
    };
    divNext.onmouseover = function() {clearInterval(change1);}
    divPrev.onmouseover = function() {clearInterval(change1);}
    divPrev.onmouseout = function() {
        change1 = setInterval(autoChangeImg, 3000);
    }
    divNext.onmouseout = function() {
        change1 = setInterval(autoChangeImg, 3000);
    }
</script>
</body>
</html>
