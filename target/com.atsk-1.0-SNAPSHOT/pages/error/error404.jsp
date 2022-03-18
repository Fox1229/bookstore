<%--
  Created by IntelliJ IDEA.
  User: 运
  Date: 2021/7/17
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>error404</title>
    <%@ include file="../common/head.jsp"%>
    <style>
        div a {
            position: absolute;
            top: 60%;
            left: 70%;
            width: 150px;
            height: 50px;
            color: #fff;
            background: #FF69B4;
            font-size: 36px;
        }
    </style>
</head>
<body>
    <div><a href="${ pageScope.basePath }index.jsp">返回首页</a></div>
    <img width="800" height="650" src="static/img/error404.jpg">
</body>
</html>
