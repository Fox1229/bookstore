<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <%@ include file="../common/head.jsp"%>
</head>
<body>

<div id="header">
    <span class="wel_word">订单详情</span>
    <%@ include file="../common/manager_menu.jsp"%>
</div>

<div id="main">
    <table>
        <tr>
            <td>名称</td>
            <td>数量</td>
            <td>价格</td>
            <td>总价</td>
        </tr>
        <c:forEach items="${ requestScope.orderDetail }" var="order">
            <tr>
                <td>${ order.name }</td>
                <td>${ order.count }</td>
                <td>${ order.price }</td>
                <td>${ order.totalPrice }</td>
            </tr>
        </c:forEach>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td>
                <a href="${ pageScope.basePath }orderServlet?action=${ sessionScope.location }">返回</a>
            </td>
        </tr>
    </table>

    <%-- 静态包含分页条--%>
<%--    <%@ include file="/pages/common/page_pav.jsp"%>--%>

</div>
<%@ include file="/pages/common/foot.jsp"%>
</body>
</html>