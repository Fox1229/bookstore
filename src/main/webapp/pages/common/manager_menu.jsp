<%--
  Created by IntelliJ IDEA.
  User: 运
  Date: 2021/7/7
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>manager</title>
    <base href="http://localhost:8080/bookstore/">
    <script type="text/javascript">
        $(function (){
            $("#logout").click(function (){
                return confirm("确认注销？")
            });
        });
    </script>
</head>
<body>
    <div>
        <%
            String Referer = request.getHeader("Referer");
            if(Referer.contains("showAllOrders")){
        %>
        <%-- 管理员登录 --%>
        <a href="manager/bookServlet?action=page">图书管理</a>
        <a href="${ pageScope.basePath }orderServlet?action=showAllOrders">订单管理</a>
        <a href="index.jsp">返回商城</a>
        <a href="userServlet?action=logout" id="logout">注销</a>
        <%
            } else if(Referer.contains("showMyOrder")){
        %>
        <%-- 用户登录 --%>
        <a href="index.jsp">返回商城</a>
        <a href="userServlet?action=logout" id="logout">注销</a>
        <%
            } else {
        %>
        <a href="manager/bookServlet?action=page">图书管理</a>
        <a href="${ pageScope.basePath }orderServlet?action=showAllOrders">订单管理</a>
        <a href="index.jsp">返回商城</a>
        <a href="userServlet?action=logout" id="logout">注销</a>
        <%
            }
        %>
    </div>
</body>
</html>
