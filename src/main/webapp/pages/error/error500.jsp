<%--
  Created by IntelliJ IDEA.
  User: 运
  Date: 2021/7/17
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>error500</title>
      <%@ include file="../common/head.jsp"%>
      <style>
          div a {
              position: absolute;
              top: 61%;
              left: 26%;
              width: 148px;
              height: 46px;
              color: #006400;
              background: #D8BFD8;
              font-size: 35px;
          }
      </style>
  </head>
<body>
    <div><a href="${ pageScope.basePath }index.jsp">返回首页</a></div>
    <img width="800" height="650" src="static/img/error500.jpg">
</body>
</html>
