<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2024/6/29
  Time: 下午4:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
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
    <div class="search-body">
        <form action="/user/search" method="post">
            <select id="search-target" name="search-target">
                <c:if test="${searchTarget == 0}">
                    <option value="0" selected>俱乐部</option>
                </c:if>
                <c:if test="${searchTarget != 0 || searchTarget == null}">
                    <option value="0">俱乐部</option>
                </c:if>
                <c:if test="${searchTarget == 1}">
                    <option value="1" selected>活动</option>
                </c:if>
                <c:if test="${searchTarget != 1 || searchTarget == null}">
                    <option value="1">活动</option>
                </c:if>
                <c:if test="${searchTarget == 2}">
                    <option value="2" selected>游戏标签</option>
                </c:if>
                <c:if test="${searchTarget != 2 || searchTarget == null}">
                    <option value="2">游戏标签</option>
                </c:if>
                <c:if test="${searchTarget == 3}">
                <option value="3">用户</option>
                </c:if>
                <c:if test="${searchTarget != 3 || searchTarget == null}">
                    <option value="3">用户</option>
                </c:if>
            </select>
            <input type="text" name="search-text" value="${searchText}">
            <input type="submit" value="提交" name="action">
        </form>
    </div>
    <div class="searched">
        <c:if test="${clubs != null && searchTarget == 0}">
            找到的俱乐部：<br>
            <c:forEach var="club" items="${clubs}">
                <div class="club-body">
                    <div><a href="/club/clubHome?clubId=${club.id}">${club.clubName}</a></div>
                    <div>标签：
                        <c:forEach var="tag" items="${club.tags}">
                            <a href="/user/search?search-target=2&&search-text=${tag}">${tag}</a>&nbsp;&nbsp;
                        </c:forEach>
                    </div>
                    <span>俱乐部人数：${club.number}</span>
                    <span>位置：
                        ${club.province}&nbsp;&nbsp;${club.city}
                    </span>
                    <div>
                        <c:if test="${club.joined == 1}">
                            <a href="">已加入</a>
                        </c:if>
                        <c:if test="${club.joined == 0}">
                            <a href="">未加入</a>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
            <div class="page">
                <c:if test="${pageNo1 > 1}">
                    <a href="/user/search?pageNo1=${pageNo1 - 1}&&pageNo2=${pageNo2}">上一页</a>
                </c:if>
                <c:if test="${pageNo1 < maxpageNo1}">
                    <a href="/user/search?pageNo1=${pageNo1 + 1}&&pageNo2=${pageNo2}">下一页</a>
                </c:if>
            </div>
        </c:if>
        <c:if test="${clubs == null && searchTarget == 0}">
            未找到相关俱乐部!
        </c:if>
        <c:if test="${activities != null && searchTarget == 1}">
            找到的活动：<br>
            <c:forEach var="activity" items="${activities}">
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
                    <a href="/user/search?pageNo2=${pageNo2 - 1}&&pageNo1=${pageNo1}">上一页</a>
                </c:if>
                <c:if test="${pageNo2 < maxpageNo2}">
                    <a href="/user/search?pageNo2=${pageNo2 + 1}&&pageNo1=${pageNo1}">下一页</a>
                </c:if>
            </div>
        </c:if>
        <c:if test="${activities == null && searchTarget == 1}">
            未找到相关活动!
        </c:if>
        <c:if test="${clubs != null && searchTarget == 2}">
            找到的俱乐部：<br>
            <c:forEach var="club" items="${clubs}">
                <div class="club-body">
                    <div><a href="/club/clubHome?clubId=${club.id}">${club.clubName}</a></div>
                    <div>标签：
                        <c:forEach var="tag" items="${club.tags}">
                            <a href="/user/search?search-target=2&&search-text=${tag}">${tag}</a>&nbsp;&nbsp;
                        </c:forEach>
                    </div>
                    <span>俱乐部人数：${club.number}</span>
                    <span>位置：
                        ${club.province}&nbsp;&nbsp;${club.city}
                    </span>
                    <div>
                        <c:if test="${club.joined == 1}">
                            <a href="">已加入</a>
                        </c:if>
                        <c:if test="${club.joined == 0}">
                            <a href="">未加入</a>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
            <div class="page">
                <c:if test="${pageNo1 > 1}">
                    <a href="/user/search?pageNo1=${pageNo1 - 1}&&pageNo2=${pageNo2}">上一页</a>
                </c:if>
                <c:if test="${pageNo1 < maxpageNo1}">
                    <a href="/user/search?pageNo1=${pageNo1 + 1}&&pageNo2=${pageNo2}">下一页</a>
                </c:if>
            </div>
            <c:if test="${activities != null}">
                找到的活动：<br>
                <c:forEach var="activity" items="${activities}">
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
                        <a href="/user/search?pageNo2=${pageNo2 - 1}&&pageNo1=${pageNo1}">上一页</a>
                    </c:if>
                    <c:if test="${pageNo2 < maxpageNo2}">
                        <a href="/user/search?pageNo2=${pageNo2 + 1}&&pageNo1=${pageNo1}">下一页</a>
                    </c:if>
                </div>
            </c:if>
            <c:if test="${activities == null}">
                未找到相关活动!
            </c:if>
        </c:if>
        <c:if test="${clubs == null && searchTarget == 2}">
            未找到相关俱乐部!
            <c:if test="${activities != null}">
                找到的活动：<br>
                <c:forEach var="activity" items="${activities}">
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
                        <a href="/user/search?pageNo2=${pageNo2 - 1}&&pageNo1=${pageNo1}">上一页</a>
                    </c:if>
                    <c:if test="${pageNo2 < maxpageNo2}">
                        <a href="/user/search?pageNo2=${pageNo2 + 1}&&pageNo1=${pageNo1}">下一页</a>
                    </c:if>
                </div>
            </c:if>
            <c:if test="${activities == null}">
                未找到相关活动!
            </c:if>
        </c:if>
        <c:if test="${users != null && searchTarget == 3}">
            找到的用户：<br>
            <c:forEach var="user" items="${users}">
                <div class="user-body">
                    <div><a href="/user/otherHome?userId=${user.id}">${user.userName}</a></div>
                    <div>
                        <span>年龄：${user.age}</span>
                        <span>性别：${user.gender}</span
                        <span>所在省：${user.province}</span>
                        <span>所在市：${user.city}</span>
                    </div>
                    <div>
                        <p>${user.introduction}</p>
                    </div>
                </div>
            </c:forEach>
            <div class="page">
                <c:if test="${pageNo1 > 1}">
                    <a href="/user/search?pageNo1=${pageNo1 - 1}&&pageNo2=${pageNo2}">上一页</a>
                </c:if>
                <c:if test="${pageNo1 < maxpageNo1}">
                    <a href="/user/search?pageNo1=${pageNo1 + 1}&&pageNo2=${pageNo2}">下一页</a>
                </c:if>
            </div>
        </c:if>
        <c:if test="${users == null && searchTarget == 3}">
            未找到相关用户!
        </c:if>
    </div>
</div>
<div class="footer"></div>
</body>
</html>
