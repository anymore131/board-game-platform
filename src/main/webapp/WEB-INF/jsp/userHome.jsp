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
        /* 通用样式 */
        body {
            width: 100%;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
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

        .search-body {
            flex: 1; /* 占据尽可能多的空间 */
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

        .user-message {
            /* 不需要指定宽度，因为我们想要它根据内容自适应大小 */
            display: flex; /* 使用flex布局使头像和链接并排显示 */
            align-items: center; /* 垂直居中 */
            margin-right: 10px; /* 与搜索表单之间留一些空间 */
        }

        .user-avatar {
            width: 50px; /* 头像宽度 */
            height: 50px; /* 头像高度 */
            border-radius: 50%; /* 圆形头像 */
            /* margin-left: 1200px; 这个设置是错误的，应该去掉 */
            margin-right: 10px; /* 与链接之间留一些空间 */
            vertical-align: middle; /* 垂直居中（在这个上下文中可能不需要，因为已经使用了align-items: center） */
            border: 2px solid #ffffff; /* 为头像添加一个边框，如果需要的话 */
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

        /* 表格样式 */
        .body table {
            width: 80%; /* 表格宽度100% */
            border-collapse: collapse; /* 合并边框 */
        }

        .body table td,
        .body table th {
            padding: 8px; /* 单元格内边距 */
            text-align: left; /* 文本左对齐 */
            background-color: #fff;
        }


        .user-club::before,
        .user-manage::before {
            display: block;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .user-club {
            data-title: "我加入的俱乐部";
        }

        .user-manage {
            data-title: "我管理的俱乐部";
        }

        /* 俱乐部列表样式 */
        .club {
            margin-bottom: 20px;
            border: 1px solid #ccc;
            padding: 10px;
            border-radius: 5px;
        }

        .club-body {
            background-color: #fff;
            padding: 15px;
            border-radius: 8px;
            margin: 15px 0;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
        }

        .club-name a {
            text-decoration: none;
            color: #333;
        }

        .club-tags a {
            text-decoration: none;
            color: #666;
            margin-right: 5px;
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
    <span class="user-message" >
        <a href="/user/changeAvatar">
            <img src="/user/showAvatar/${user.avatarFname}" alt="头像" class="user-avatar" title="修改头像">
        </a>
    </span>
    <span class="header-out">
        <a href="/login/login">退出</a>
    </span>
</div>
<div class="side">
    <a href="/user/index"><p>首页</p></a>
</div>
<div class="body">
    <span>
        <table>
            <tr>
                <td>${user.userName}</td>
                <td><a href="/user/changeUser">修改个人信息</a></td>
            </tr>
            <tr>
                <td colspan="2">${user.name}</td>
            </tr>
            <tr>
                <td>年龄：${user.age}</td>
                <td>
                    <c:if test="${user.gender == 1}">
                        性别：男
                    </c:if>
                    <c:if test="${user.gender == 0}">
                        性别：女
                    </c:if>
                    <c:if test="${user.gender == null}">
                        性别：
                    </c:if>
                </td>
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
        <p>我加入的俱乐部</p>
        <c:if test="${clubs != null}">
            <c:forEach var="club" items="${clubs}">
                <div class="club-body">
                <span class="club-name">
                    <a href="/club/clubHome?clubId=${club.id}">${club.clubName}</a>
                </span>
                    <div class="club-tags">
                        标签：
                        <c:forEach var="tag" items="${club.tags}">
                            <a href="/user/search?search-target=2&&search-text=${tag}">${tag}</a>&nbsp;&nbsp;
                        </c:forEach>
                    </div>
                    <div class="club-join-time">
                        加入时间：${club.joinTime}
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${clubs == null}">
            <div class="club-body">
                未有加入的俱乐部
            </div>
        </c:if>
    </div>
    <div class="user-club">
        <p>我管理的俱乐部</p>
        <c:if test="${manageClubs != null}">
            <c:forEach var="club" items="${manageClubs}">
                <div class="club-body">
                <span class="club-name">
                    <a href="/club/clubHome?clubId=${club.id}">${club.clubName}</a>
                </span>
                    <div class="club-tags">
                        标签：
                        <c:forEach var="tag" items="${club.tags}">
                            <a href="/user/search?search-target=2&&search-text=${tag}">${tag}</a>&nbsp;&nbsp;
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${manageClubs == null}">
            <div class="club-body">
                未有管理的俱乐部
            </div>
        </c:if>
    </div>
    <div class="user-club">
        <p>所有活动</p>
        <%--正在进行的活动--%>
        <div class="club-activity">
            <c:if test="${activitiesStarting != null}">
                <div class="item">正在进行的活动</div>
                <c:forEach var="activity" items="${activitiesStarting}">
                    <div class="activity-body">
                        <div>
                            <a href="/activity/activityHome?activityId=${activity.id}">
                                    ${activity.activityName}
                            </a>
                        </div>
                        <div>
                            俱乐部名：
                            <a href="/club/clubHome?clubId=${activity.clubId}">
                                    ${activity.clubName}
                            </a>
                        </div>
                        <div>
                            标签：
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
                <div class="page">
                    <c:if test="${pageNo2 > 1}">
                        <a href="/user/index?pageNo2=${pageNo2-1}&&pageNo1=${pageNo1}&&pageNo3=${pageNo3}">上一页</a>
                    </c:if>
                    <c:if test="${pageNo2 < maxpageNo2}">
                        <a href="/user/index?pageNo2=${pageNo2+1}&&pageNo1=${pageNo1}&&pageNo3=${pageNo3}">下一页</a>
                    </c:if>
                </div>
            </c:if>
            <c:if test="${activitiesStarting == null}">
                <div class="item">目前没有还没正在进行的活动</div>
            </c:if>
        </div>
        <%--未开始的活动--%>
        <div class="club-activity">
            <c:if test="${activitiesUnStart != null}">
                <div class="item">未开始的活动</div>
                <c:forEach var="activity" items="${activitiesUnStart}">
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
                <div class="page">
                    <c:if test="${pageNo1 > 1}">
                        <a href="/user/index?pageNo2=${pageNo2}&&pageNo1=${pageNo1-1}&&pageNo3=${pageNo3}">上一页</a>
                    </c:if>
                    <c:if test="${pageNo1 < maxpageNo1}">
                        <a href="/user/index?pageNo2=${pageNo2}&&pageNo1=${pageNo1+1}&&pageNo3=${pageNo3}">下一页</a>
                    </c:if>
                </div>
            </c:if>
            <c:if test="${activitiesUnStart == null}">
                <div class="item">目前没有还没开始的活动</div>
            </c:if>
        </div>
        <%--已经结束的活动--%>
        <div class="club-activity">
            <c:if test="${activitiesEnd != null}">
                <div class="item">已经结束的活动</div>

                <c:forEach var="activity" items="${activitiesEnd}">
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

                <div class="page">
                    <c:if test="${pageNo2 > 1}">
                        <a href="/user/index?pageNo2=${pageNo2}&&pageNo1=${pageNo1}&&pageNo3=${pageNo3-1}">上一页</a>
                    </c:if>
                    <c:if test="${pageNo2 < maxpageNo2}">
                        <a href="/user/index?pageNo2=${pageNo2}&&pageNo1=${pageNo1}&&pageNo3=${pageNo3+1}">下一页</a>
                    </c:if>
                </div>

            </c:if>
            <c:if test="${activitiesEnd == null}">
                <div class="item">目前没有结束的活动</div>
            </c:if>
        </div>
    </div>
</div>
<div class="footer"></div>
</body>
</html>
