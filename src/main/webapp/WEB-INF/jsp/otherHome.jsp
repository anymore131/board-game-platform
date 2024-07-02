<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2024/6/30
  Time: 下午5:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${otherUser.userName}的空间</title>
    <script>
        <c:if test="${sessionScope.error != null}">
        alert("${sessionScope.error}")
        ${sessionScope.remove("error")}
        </c:if>
    </script>
    <style>
        .user-avatar {
            width: 50px; /* 头像宽度 */
            height: 50px; /* 头像高度 */
            border-radius: 50%; /* 圆形头像 */
            /* margin-left: 1200px; 这个设置是错误的，应该去掉 */
            margin-right: 10px; /* 与链接之间留一些空间 */
            vertical-align: middle; /* 垂直居中（在这个上下文中可能不需要，因为已经使用了align-items: center） */
            border: 2px solid #ffffff; /* 为头像添加一个边框，如果需要的话 */
        }
        .user-message {
            /* 不需要指定宽度，因为我们想要它根据内容自适应大小 */
            display: flex; /* 使用flex布局使头像和链接并排显示 */
            align-items: center; /* 垂直居中 */
            margin-right: 10px; /* 与搜索表单之间留一些空间 */
        }
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
</div>
<div class="body">
    <span class="user-message">
        <img src="/user/showAvatar/${otherUser.avatarFname}" alt="头像" class="user-avatar" title="修改头像">
    </span>
    <span>
        <table>
            <tr>
                <td>${otherUser.userName}</td>
            </tr>
            <tr>
                <td>${otherUser.age}</td>
                <td>${otherUser.gender}</td>
            </tr>
            <tr>
                <td colspan="2">所在城市：&nbsp;&nbsp;${otherUser.province}&nbsp;${otherUser.city}</td>
            </tr>
            <tr>
                <td colspan="2">简介：${otherUser.introduction}</td>
            </tr>
            <tr>
                <td>手机号:${otherUser.mobile}</td>
                <td>邮箱：${otherUser.email}</td>
            </tr>
            <tr>
                <td>QQ:${otherUser.QQ}</td>
                <td>微信：${otherUser.weixin}</td>
            </tr>
        </table>
    </span>
</div>
<div class="footer"></div>
</body>
</html>
