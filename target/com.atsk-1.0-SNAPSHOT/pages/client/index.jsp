<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%@ include file="../common/head.jsp" %>
    <script type="text/javascript">
        $(function () {
            $("#searchPageBtn").click(function () {
                var pageNo = $("#pn_input").val().trim();
                // javaScript 语言提供了一个location 地址栏对象，它有一个属性交href，可以获取浏览器地址栏中的地址，
                location.href = "${ pageScope.basePath }client/bookServlet?action=${ requestScope.location }&pageNo=" + pageNo;
            });

            $("button.addToCart").click(function () {
                if(${ not empty sessionScope.user }){
                    // 用户登录后
                    // href属性可读，可写
                    var bookId = $(this).attr("bookId");
                    // 获取库存数量
                    location.href = "${ pageScope.basePath }cartServlet?action=addItem&id=" + bookId;
                } else{
                    // 用户没有登录
                    location.href = "${ pageScope.basePath }pages/user/login.jsp";
                }
            });

            $("#logout").click(function () {
                return confirm("确认注销？")
            });
        });
    </script>
    <style type="text/css">
        #getBookByBookNameInput {
            width: 294px;
            height: 24px;
            line-height: 24px;
            color: #999;
            padding: 4px;
            margin-bottom: 4px;
            border-width: 2px 0 2px 2px;
            border-color: #f30213;
            border-style: solid;
            outline: 0;
            font-size: 12px;
            margin-left: 580px;
        }

        #searchBookButton {
            width: 80px;
            height: 36px;
            font-family: tahoma,arial,Microsoft YaHei,Hiragino Sans GB,"\u5b8b\u4f53",sans-serif;
            background: #f30213;
            border: none;
            border-radius: 0;
            line-height: 0;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
            vertical-align:middle;
        }
    </style>
</head>
<body>
<div id="searchBookDiv">
    <form action="client/bookServlet" method="get">
        <input type="hidden" name="action" value="getBookByBookName">
        <input type="text" name="bookName" placeholder="小王子" value="${ requestScope.bookName }" id="getBookByBookNameInput"><input type="submit" value="搜索" id="searchBookButton">
    </form>
</div>
<div id="header">
    <img class="logo_img" alt="" src="static/img/zsg.gif">
    <span class="wel_word"></span>
    <div>
        <%-- 用户没有登录，显示【登录】【注册】--%>
        <c:if test="${ empty sessionScope.user }">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/register.jsp">注册</a>
            <a href="pages/cart/cart.jsp">购物车</a>
            <a href="pages/manager/manager.jsp">后台管理</a>
        </c:if>
        <%-- 用户登录后，不显示【登录】【注册】--%>
        <c:if test="${ not empty sessionScope.user }">
            <span>欢迎<span class="um_span">${ sessionScope.user.username }</span>光临书城</span>
            <a href="${ pageScope.basePath }orderServlet?action=showMyOrder">我的订单</a>
            <a href="pages/cart/cart.jsp">购物车</a>
            <a href="pages/manager/manager.jsp">后台管理</a>
            <a href="userServlet?action=logout" id="logout">注销</a>
        </c:if>
    </div>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="client/bookServlet" method="get">
                <input type="hidden" name="action" value="getItemsByPriceScope">
                价格：<input id="min" type="text" name="min" value="${ requestScope.minPrice }"> 元 -
                <input id="max" type="text" name="max" value="${ requestScope.maxPrice }"> 元
                <input type="submit" value="查询" id="queryById"/>
            </form>
        </div>
        <div style="text-align: center">
            <%-- 购物车为空 --%>
            <c:if test="${ empty sessionScope.cart.items }">
                <div>
                    <span style="color: red">您的购物车为空</span>
                </div>
            </c:if>

            <%-- 购物车不为空 --%>
            <c:if test="${ not empty sessionScope.cart.items }">
                <span id="cartTotalCount">您的购物车中有 ${ sessionScope.cart.totalCount } 件商品</span>
                <div>
                    您刚刚将
                    <span style="color: red" id="cartLastName">${ sessionScope.lastName }</span>
                    加入到了购物车中
                </div>
            </c:if>
        </div>
        <c:forEach items="${ requestScope.page.items }" var="book">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="无法显示图片" src="http://localhost:8080/img/${ book.imgName }"/>
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">&nbsp;名称:</span>
                        <span class="sp2">${ book.name }</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">&nbsp;作者:</span>
                        <span class="sp2">${ book.author }</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">&nbsp;价格:</span>
                        <span class="sp2">￥${ book.price }</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">&nbsp;销量:</span>
                        <span class="sp2">${ book.sales }</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">&nbsp;库存:</span>
                        <span class="sp2">${ book.stock }</span>
                    </div>
                    <div class="book_add">
                        <%-- 有库存，加入购物车 --%>
                        <c:if test="${ book.stock > 0 }">
                            &nbsp;<button class="addToCart" bookId="${ book.id }">加入购物车</button>
                        </c:if>
                        <%-- 没有库存，不可以加入购物车 --%>
                        <c:if test="${ book.stock <= 0 }">
                            &nbsp;<button class="addToCart" disabled="disabled">加入购物车</button>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <%-- 静态包含分页条--%>
    <%@ include file="/pages/common/page_pav.jsp" %>
</div>
<%@ include file="/pages/common/foot.jsp" %>
</body>
</html>