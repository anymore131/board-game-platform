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
<div class="body">
    <div class="search-body">
        <form action="/user/search" method="post">
            <select id="search-target" name="search-target">
                <option value="0">俱乐部</option>
                <option value="1">活动</option>
                <option value="2">游戏标签</option>
                <option value="3">用户</option>
            </select>
            <input type="text" name="search-text" value="${searchText}">
            <input type="submit" value="提交" name="action">
        </form>
    </div>
    <div class="searched">
        <c:if test="${clubs != null && searchTarget == 0}">
            找到的俱乐部：<br>
            <c:forEach var="club" items="${clubs}">
                <div class="club">
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
                <div class="activity">
                    <div><a href="">${activity.activityName}</a></div>
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
                <div class="club">
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
                    <div class="activity">
                        <div><a href="">${activity.activityName}</a></div>
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
        </c:if>
    </div>
</div>
<div class="footer"></div>
</body>
</html>
