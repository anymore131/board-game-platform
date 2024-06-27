<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2024/6/27
  Time: 下午5:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>注册</title>
    <script>
        <c:if test="${sessionScope.error != null}">
        alert("${sessionScope.error}")
        ${sessionScope.remove("error")}
        </c:if>
    </script>
    <style>
        * {
            padding: 0;
            margin: 0;
        }
        .wrapper {
            width: 100vw;
            height: 100vh;
            background-color: rgb(235, 236, 240);
            position: relative;
            overflow: hidden;
            display: flex;
            justify-content: center;
        }
        .wrapper .left-img {
            width: 40%;
            position: absolute;
            top: 50%;
            transform: translateY(-50%);
            left: 10%;
        }
        .wrapper .round {
            width: 500px;
            height: 500px;
            background-color: #fff;
            border-radius: 50%;
            position: absolute;
            left: 40%;
            top: -350px;
            transform: translateX(-40%);
        }
        .wrapper .round::after {
            content: "";
            display: block;
            border: 1px solid rgb(194, 205, 216);
            width: 500px;
            height: 500px;
            border-radius: 50%;
            position: absolute;
            left: 30%;
        }
        .wrapper .name {
            position: absolute;
            left: 30px;
            top: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            margin-right: 10px;
        }
        .wrapper .name img {
            width: 70px;
            height: 70px;
            border-radius: 50%;
            margin-right: 20px;
        }
        .wrapper .name span {
            font-size: 30px;
            font-weight: 900;
        }
        .wrapper .introduce {
            position: absolute;
            bottom: 20px;
            left: 30px;
            display: flex;
            flex-direction: column;
        }
        .wrapper .introduce .nav ul li {
            float: left;
            margin-right: 20px;
            list-style: none;
            font-size: 30px;
            cursor: pointer;
            transition: 0.3s;
        }
        .wrapper .introduce .nav ul li:hover {
            color: rgb(87, 59, 255);
        }
        .wrapper .form-wrapper {
            width: 400px;
            position: absolute;
            right: 10%;
            top: 50%;
            transform: translateY(-50%);
            background-color: #fff;
            border: 0;
            border-radius: 10px;
            padding: 50px 60px;
        }
        .wrapper .form-wrapper .tips {
            display: block;
            margin: 20px 0;
        }
        .wrapper .form-wrapper .tips .tips-span {
            color: rgb(87, 59, 255);
            margin: 10px;
            cursor: pointer;
        }
        .wrapper .form-wrapper .other-login {
            width: 100%;
            display: flex;
            margin: 10px 0;
        }
        .wrapper .form-wrapper .other-login .other-login-item {
            height: 40px;
            flex: 1;
            border: 1px solid rgb(194, 205, 216);
            border-radius: 10px;
            display: flex;
            justify-content: center;
            align-items: center;
            cursor: pointer;
            transition: 0.3s;
        }
        .wrapper .form-wrapper .other-login .other-login-item:nth-child(1) {
            margin-right: 10px;
        }
        .wrapper .form-wrapper .other-login .other-login-item:nth-child(2) {
            margin-left: 10px;
        }
        .wrapper .form-wrapper .other-login .other-login-item:hover {
            border: 1px solid rgb(87, 59, 255);
        }
        .wrapper .form-wrapper .other-login .other-login-item .other-login-svg {
            width: 30px;
            height: 30px;
            margin-right: 5px;
        }
        .wrapper .form-wrapper .diliver {
            width: 100%;
            margin: 10px 0;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .wrapper .form-wrapper .diliver span {
            display: inline-block;
            margin: 0 40px;
        }
        .wrapper .form-wrapper .diliver::before {
            content: "";
            display: block;
            width: 100%;
            height: 1px;
            background-color: rgb(194, 205, 216);
        }
        .wrapper .form-wrapper .diliver::after {
            content: "";
            display: block;
            width: 100%;
            height: 1px;
            background-color: rgb(194, 205, 216);
        }
        .wrapper .form-wrapper .form-input {
            width: 100%;
            margin: 10px 0;
        }
        .wrapper .form-wrapper .form-input .form-input-item {
            width: 100%;
            margin-bottom: 10px;
            position: relative;
        }
        .wrapper .form-wrapper .form-input .form-input-item p {
            font-size: 16px;
            font-weight: 900;
            margin-bottom: 5px;
        }
        .wrapper .form-wrapper .form-input .form-input-item .ipt {
            width: 100%;
            height: 45px;
            border-radius: 10px;
            border: 1px solid rgb(194, 205, 216);
            padding: 15px 100px 15px 50px;
            box-sizing: border-box;
            margin-bottom: 5px;
            outline: none;
            font-size: 20px;
            transition: 0.3s;
        }
        .wrapper .form-wrapper .form-input .form-input-item .ipt:hover {
            border: 1px solid rgb(87, 59, 255);
        }
        .wrapper .form-wrapper .form-input .form-input-item .ipt:hover ~ .ipt-svg {
            fill: rgb(87, 59, 255);
        }
        .wrapper .form-wrapper .form-input .form-input-item .ipt:focus {
            border: 1px solid rgb(87, 59, 255);
        }
        .wrapper .form-wrapper .form-input .form-input-item .ipt:focus ~ .ipt-svg {
            fill: rgb(87, 59, 255);
        }
        .wrapper .form-wrapper .form-input .form-input-item .ipt-svg {
            position: absolute;
            width: 25px;
            height: 25px;
            left: 10px;
            top: 50%;
            fill: #707070;
            transition: 0.3s;
        }
        .wrapper .form-wrapper .form-input .form-input-item .veri-code-tips {
            position: absolute;
            right: 10px;
            bottom: 10px;
            cursor: pointer;
            color: rgb(87, 59, 255);
        }
        .wrapper .form-wrapper .form-input .btn {
            width: 100%;
            height: 50px;
            background-color: #000;
            color: #fff;
            border-radius: 10px;
            border: 0;
            margin: 10px 0;
            font-size: 20px;
            cursor: pointer;
            font-family: 微软雅黑, serif;
        }
        .btn:hover{
            color: #000;
            background-color: #2d8cd7;
        }
        .wrapper .form-wrapper .forgot-pwd {
            width: 100%;
            text-align: center;
            color: rgb(87, 59, 255);
            margin: 10px 0;
            cursor: pointer;
        }
        .wrapper .form-wrapper .login-form {
            width: 100%;
            display: block;
        }
        .wrapper .form-wrapper .regite-form {
            width: 100%;
            display: none;
        }
        .wrapper .form-wrapper .forgot-pwd-form {
            width: 100%;
            display: none;
        }

        .rotate-vert-center {
            animation: rotate-vert-center 0.5s cubic-bezier(0.455, 0.03, 0.515, 0.955) both;
        }

        @keyframes rotate-vert-center {
            0% {
                transform: translateY(-50%) rotate(0);
            }
            0% {
                transform: translateY(-50%) rotate(360deg);
            }
        }/*# sourceMappingURL=style.css.map */
    </style>
</head>
<body>
<div class="wrapper">
    <div class="name">
        <img src="" alt="">
        <span></span>
    </div>
    <img src="" alt="" class="left-img">
    <div class="round"></div>
    <div class="form-wrapper">
        <div class="register-form">
            <h1 class="title">注册</h1>
            <p class="tips">
                已有账号？
                <span class="tips-span sigin"><a href="/login/login">去登录</a></span>
            </p>
            <div class="form-input">
                <form action="/login/register" method="post">
                    <div class="form-input-item">
                        <p>Username</p>
                        <input type="text" class="ipt" name="userName">
                        <svg class="ipt-svg icon" t="1706422441211" viewBox="0 0 1024 1024" version="1.1"
                             xmlns="http://www.w3.org/2000/svg" p-id="10013" width="128" height="128">
                            <path
                                    d="M874.666667 181.333333H149.333333c-40.533333 0-74.666667 34.133333-74.666666 74.666667v512c0 40.533333 34.133333 74.666667 74.666666 74.666667h725.333334c40.533333 0 74.666667-34.133333 74.666666-74.666667V256c0-40.533333-34.133333-74.666667-74.666666-74.666667z m-725.333334 64h725.333334c6.4 0 10.666667 4.266667 10.666666 10.666667v25.6L512 516.266667l-373.333333-234.666667V256c0-6.4 4.266667-10.666667 10.666666-10.666667z m725.333334 533.333334H149.333333c-6.4 0-10.666667-4.266667-10.666666-10.666667V356.266667l356.266666 224c4.266667 4.266667 10.666667 4.266667 17.066667 4.266666s12.8-2.133333 17.066667-4.266666l356.266666-224V768c0 6.4-4.266667 10.666667-10.666666 10.666667z"
                                    p-id="10014"></path>
                        </svg>
                    </div>
                    <div class="form-input-item">
                        <p>Password</p>
                        <input type="password" class="ipt" name="password">
                        <svg class="ipt-svg icon" t="1706422409197" viewBox="0 0 1024 1024" version="1.1"
                             xmlns="http://www.w3.org/2000/svg" p-id="7561" width="128" height="128">
                            <path
                                    d="M804.571429 1023.975619 219.428571 1023.975619C165.571048 1023.975619 121.904762 1004.714667 121.904762 950.832762L121.904762 536.356571C121.904762 482.499048 165.571048 438.832762 219.428571 438.832762L219.428571 438.832762 219.428571 292.547048 219.428571 292.547048C219.428571 130.974476 350.403048-0.024381 512-0.024381 673.572571-0.024381 804.571429 130.974476 804.571429 292.547048L804.571429 438.832762C858.428952 438.832762 902.095238 482.499048 902.095238 536.356571L902.095238 950.832762C902.095238 1004.714667 858.428952 1023.975619 804.571429 1023.975619ZM755.809524 292.547048 755.809524 292.547048C755.809524 292.547048 755.809524 292.547048 755.809524 292.547048 755.809524 157.891048 646.631619 48.737524 512 48.737524 377.344 48.737524 268.166095 157.891048 268.166095 292.547048 268.166095 292.547048 268.166095 292.547048 268.166095 292.547048L268.190476 292.547048 268.190476 438.832762 755.809524 438.832762 755.809524 292.547048ZM853.333333 536.356571C853.333333 509.44 831.488 487.594667 804.571429 487.594667L219.428571 487.594667C192.487619 487.594667 170.666667 509.44 170.666667 536.356571L170.666667 950.832762C170.666667 977.773714 192.487619 975.213714 219.428571 975.213714L804.571429 975.213714C831.488 975.213714 853.333333 977.773714 853.333333 950.832762L853.333333 536.356571ZM536.380952 801.109333 536.380952 877.714286C536.380952 891.172571 525.458286 902.095238 512 902.095238 498.541714 902.095238 487.619048 891.172571 487.619048 877.714286L487.619048 801.109333C445.635048 790.235429 414.47619 752.420571 414.47619 707.023238 414.47619 653.165714 458.142476 609.499429 512 609.499429 565.857524 609.499429 609.52381 653.165714 609.52381 707.023238 609.52381 752.420571 578.364952 790.235429 536.380952 801.109333ZM512 658.261333C485.059048 658.261333 463.238095 680.106667 463.238095 707.023238 463.238095 733.96419 485.059048 755.809524 512 755.809524 538.916571 755.809524 560.761905 733.96419 560.761905 707.023238 560.761905 680.106667 538.916571 658.261333 512 658.261333Z"
                                    p-id="7562"></path>
                        </svg>
                    </div>
                    <div class="form-input-item">
                        <p>Name</p>
                        <input type="password" class="ipt" name="name">
                        <svg class="ipt-svg icon" t="1706422409197" viewBox="0 0 1024 1024" version="1.1"
                             xmlns="http://www.w3.org/2000/svg" p-id="7561" width="128" height="128">
                            <path
                                    d="M804.571429 1023.975619 219.428571 1023.975619C165.571048 1023.975619 121.904762 1004.714667 121.904762 950.832762L121.904762 536.356571C121.904762 482.499048 165.571048 438.832762 219.428571 438.832762L219.428571 438.832762 219.428571 292.547048 219.428571 292.547048C219.428571 130.974476 350.403048-0.024381 512-0.024381 673.572571-0.024381 804.571429 130.974476 804.571429 292.547048L804.571429 438.832762C858.428952 438.832762 902.095238 482.499048 902.095238 536.356571L902.095238 950.832762C902.095238 1004.714667 858.428952 1023.975619 804.571429 1023.975619ZM755.809524 292.547048 755.809524 292.547048C755.809524 292.547048 755.809524 292.547048 755.809524 292.547048 755.809524 157.891048 646.631619 48.737524 512 48.737524 377.344 48.737524 268.166095 157.891048 268.166095 292.547048 268.166095 292.547048 268.166095 292.547048 268.166095 292.547048L268.190476 292.547048 268.190476 438.832762 755.809524 438.832762 755.809524 292.547048ZM853.333333 536.356571C853.333333 509.44 831.488 487.594667 804.571429 487.594667L219.428571 487.594667C192.487619 487.594667 170.666667 509.44 170.666667 536.356571L170.666667 950.832762C170.666667 977.773714 192.487619 975.213714 219.428571 975.213714L804.571429 975.213714C831.488 975.213714 853.333333 977.773714 853.333333 950.832762L853.333333 536.356571ZM536.380952 801.109333 536.380952 877.714286C536.380952 891.172571 525.458286 902.095238 512 902.095238 498.541714 902.095238 487.619048 891.172571 487.619048 877.714286L487.619048 801.109333C445.635048 790.235429 414.47619 752.420571 414.47619 707.023238 414.47619 653.165714 458.142476 609.499429 512 609.499429 565.857524 609.499429 609.52381 653.165714 609.52381 707.023238 609.52381 752.420571 578.364952 790.235429 536.380952 801.109333ZM512 658.261333C485.059048 658.261333 463.238095 680.106667 463.238095 707.023238 463.238095 733.96419 485.059048 755.809524 512 755.809524 538.916571 755.809524 560.761905 733.96419 560.761905 707.023238 560.761905 680.106667 538.916571 658.261333 512 658.261333Z"
                                    p-id="7562"></path>
                        </svg>
                    </div>
                    <input type="submit" class="btn" value="注册">
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
