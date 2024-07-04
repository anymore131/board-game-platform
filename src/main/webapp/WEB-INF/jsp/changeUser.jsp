<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2024/7/3
  Time: 下午9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>修改个人信息</title>
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
            margin-left: 150px; /* 左边距，留出侧边栏的空间 */
        }

        /* 表格样式 */
        .body table {
            width: 80%; /* 表格宽度100% */
            border-collapse: collapse; /* 合并边框 */
        }

        .body table td,
        .body table th {
            border: 1px solid #ddd; /* 单元格边框 */
            padding: 8px; /* 单元格内边距 */
            text-align: left; /* 文本左对齐 */
        }

        .user-club::before,
        .user-manage::before {
            content: attr(data-title) ": ";
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

        .club-name a {
            text-decoration: none;
            color: #333;
        }

        .club-tags a {
            text-decoration: none;
            color: #666;
            margin-right: 5px;
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
    <span class="avatar-body" >
        <a href="/user/changeAvatar">
            <img src="/user/showAvatar/${user.avatarFname}" alt="头像" class="avatar" title="修改头像">
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
    <form action="/user/changeUser" method="post">
        <table>
            <tr>
                <td>用户名：</td>
                <td>${user.userName}</td>
            </tr>
            <tr>
                <td>姓名：</td>
                <td><input type="text" name="name" value="${user.name}"></td>
            </tr>
            <tr>
                <td colspan="2">
                    年龄：<input type="number" name="age" max="100" min="0" value="${user.age}">
                    性别：
                    <select id="gender" name="gender">
                        <c:if test="${user.gender == 0}">
                            <option value="0" selected>女</option>
                            <option value="1">男</option>
                        </c:if>
                        <c:if test="${user.gender == 1 || user.gender == null}">
                            <option value="0">女</option>
                            <option value="1" selected>男</option>
                        </c:if>
                    </select>
                </td>
            </tr>
            <tr>
                <td>简介：</td>
                <td>
                    <textarea name="introduction">${user.introduction}</textarea>
                </td>
            </tr>
            <tr>
                <td>位置：</td>
                <td><input type="text" name="province" value="${user.province}"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="text" name="city" value="${user.city}"></td>
            </tr>
            <tr>
                <td>手机号：</td>
                <td><input type="text" name="mobile" value="${user.mobile}"></td>
            </tr>
            <tr>
                <td>邮箱：</td>
                <td><input type="text" name="email" value="${user.email}"></td>
            </tr>
            <tr>
                <td>QQ：</td>
                <td><input type="text" name="QQ" value="${user.QQ}"></td>
            </tr>
            <tr>
                <td>微信：</td>
                <td><input type="text" name="weixin" value="${user.weixin}"></td>
            </tr>
            <tr>
                <td colspan="2"><input type="submit" value="提交" style="float: right"></td>
            </tr>
        </table>
    </form>
</div>
<div class="footer"></div>
</body>
</html>
