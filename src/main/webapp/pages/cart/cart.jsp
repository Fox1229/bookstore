<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <base href="http://localhost:8080/bookstore/">
    <%@ include file="../common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            // 给删除按钮绑定单击事件
            $("a.deleteItem").click(function () {
                return confirm("您确定要删除[ " + $(this).parent().parent().find("td:first").text() + " ]吗？");
            });

            // 给清空购物车按钮绑定单击事件
            $("#deleteAll").click(function () {
                return confirm("您确定要清空购物车吗？");
            });

            // 给修改购物车数量按钮绑定单击事件
            $(".updateCount").change(function (){
                // 获取要修改商品的名称
                var name = $(this).parent().parent().find("td:first").text();
                // 获取要修改商品的数量
                var count = this.value;
                // 获取要修改商品的id
                var bookId = $(this).attr("bookId");

                if(confirm("您确定要修改[ " + name + " ]的数量为: " + count +  " 吗?")){
                    // 确定修改
                    location.href="${ pageScope.basePath }cartServlet?action=updateItemCount&id="
                        + bookId + "&count=" + count;
                }else{
                    // 取消修改:恢复原来的数量
                    this.value = this.defaultValue;
                }
            });
        });
    </script>
</head>
<body>
<div id="header">
<%--    <img class="logo_img" alt="" src="static/img/logo.gif">--%>
    <span class="wel_word">购物车</span>
    <%@ include file="../common/login_success_menu.jsp" %>
</div>
<div id="main">
    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>
        <%-- 购物车为空 --%>
        <c:if test="${ empty sessionScope.cart.items }">
            <tr>
                <td colspan="5"><a href="index.jsp">亲，您的购物车为空，点击去剁手！！！</a></td>
            </tr>
        </c:if>

        <%-- 购物车非空 --%>
        <c:if test="${ not empty sessionScope.cart.items }">
            <c:forEach items="${ sessionScope.cart.items }" var="entry">
                <tr>
                    <td>${ entry.value.name }</td>
                    <td>
                        <input class="updateCount" style="width: 40px" type="text"
                               value="${ entry.value.count }" bookId="${ entry.value.id }"/>
                    </td>
                    <td>${ entry.value.price }</td>
                    <td>${ entry.value.totalPrice }</td>
                    <td><a href="${ pageScope.basePath }cartServlet?action=deleteItemById&id=${ entry.value.id }"
                           class="deleteItem" bookId="${ entry.value.id }">删除</a></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <%-- 购物车非空 --%>
    <c:if test="${ not empty sessionScope.cart.items }">
        <div class="cart_info">
            <span class="cart_span">购物车中共有
                <span class="b_count">${ sessionScope.cart.totalCount }
            </span>件商品</span>
            <span class="cart_span">总金额
                <span class="b_price">${ sessionScope.cart.totalPrice }</span>元
            </span>
            <span class="cart_span">
                <a href="${ pageScope.basePath }cartServlet?action=deleteAll" id="deleteAll">清空购物车</a>
            </span>
            <span class="cart_span">
                <a href="${ pageScope.basePath }orderServlet?action=createOrder">提交订单</a>
            </span>
        </div>
    </c:if>
</div>

<%@ include file="/pages/common/foot.jsp" %>
</body>
</html>