<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 运
  Date: 2021/7/7
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login_success_menu</title>
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
    <%-- 用户没有登录 --%>
    <c:if test="${ empty sessionScope.user }">
        <span>欢迎您光临书城</span>
        <%-- 提示用户登录 --%>
        <a href="${ pageScope.basePath }pages/user/login.jsp">我的订单</a>
        <a href="index.jsp">返回商城</a>
    </c:if>
    <c:if test="${ not empty sessionScope.user }">
        <span>欢迎<span class="um_span">${ sessionScope.user.username }</span>光临书城</span>
        <a href="${ pageScope.basePath }orderServlet?action=showMyOrder">我的订单</a>
        <a href="index.jsp">返回商城</a>
        <a href="userServlet?action=logout" id="logout">注销</a>
    </c:if>
</div>
</body>
</html>
