<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2024/7/4
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>创建游戏</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
            overflow-x: hidden;
            overflow-y: auto;
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

        .body {
            margin-left: 150px;
            margin-right: 150px;
            padding: 20px;
        }



        .activity-body a {
            text-decoration: none;
            color: #007bff;
        }

        .footer {
            background-color: #343a40;
            color: #fff;
            padding: 15px;
            text-align: center;
            width: 100%;
            position: absolute;
            bottom: 0;
        }
    </style>
    <script>
        <c:if test="${sessionScope.error != null}">
        alert("${sessionScope.error}")
        ${sessionScope.remove("error")}
        </c:if>
    </script>
</head>
<body>
<div class="header"> <span class="avatar-body">
        <a href="/user/userHome">
            <img src="/user/showAvatar/${user.avatarFname}" alt="头像" class="avatar" title="个人中心">
        </a>
    </span>
    <span class="header-out">
        <a href="/login/login">退出</a>
    </span>

</div>
<div class="side">
    <a href="/user/index?userId=${user.id}">
        <p>返回首页</p>
    </a>
</div>
<div class="body">
        <div class="item">游戏创建</div>
            <div class="newGame-body">
                <form method="post" action="/manage/createGame">
                    <input type="text" name="name"><br/>
                    <textarea name="introduction" placeholder="规则"></textarea><br/>

<%--                    创建时间：<input type="date" name="insertTime">--%>
                    <input type="submit" value="创建">
                </form>
            </div>
</div>
<div class="footer"></div>
</body>
</html>
