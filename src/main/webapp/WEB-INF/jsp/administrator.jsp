<%--
  Created by IntelliJ IDEA.
  User: zhw
  Date: 2024/7/4
  Time: 下午1:37
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
</head>
<body>
<div class="header">
    <a href="/user/index?userId=${user.id}">返回首页</a>
</div>
<div class="side"></div>
<div class="body">

    <c:if test="${newClubs != null}">
        <div class="item">待审核的俱乐部</div>
        <c:forEach var="newClub" items="${newClubs}">
            <div class="newClubs-body">

                <div>${newClub.clubName}</div>
                <div>${newClub.introduction}</div>
                <form method="post" action="/manage/administrator">
                    <input type="hidden" name="userId" value="${userId}">
                    <input type="hidden"  name="id" value="${newClub.id}">
                    <input type="submit" value="提交">
                </form>
            </div>

        </c:forEach>
    </c:if>
    <c:if test="${newClubs == null}">
        <div class="item">还没有待审核的新俱乐部</div>
    </c:if>
</div>
<div class="footer"></div>
</body>
</html>
