<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%@ include file="../common/head.jsp"%>
    <script type="text/javascript">
        $(function (){
            $("#searchPageBtn").click(function (){
                var pageNo = $("#pn_input").val().trim();
                location.href="${ pageScope.basePath }client/bookServlet?action=${ requestScope.location }&pageNo=" + pageNo;
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

        #unFund {
            text-align: center;
            margin-top: 200px;
            color: red;
        }
    </style>
</head>
<body>
<div id="searchBookDiv">
    <form action="client/bookServlet" method="get">
        <input type="hidden" name="action" value="getBookByBookName">
        <input type="text" name="bookName" placeholder="小王子"  value="${ requestScope.bookName }" id="getBookByBookNameInput"><input type="submit" value="搜索" id="searchBookButton">
    </form>
</div>
<div id="header">
    <img class="logo_img" alt="" src="static/img/zsg.gif" >
    <div>
        <a href="pages/user/login.jsp">登录</a> |
        <a href="pages/user/register.jsp">注册</a>
        <a href="pages/cart/cart.jsp">购物车</a>
        <a href="pages/manager/manager.jsp">后台管理</a>
    </div>
</div>
<div id="main">

    <div id="book">
        <div class="book_cond">
            <form action="client/bookServlet" method="get">
                <input type="hidden" name="action" value="getItemsByPriceScope">
                价格：<input id="min" type="text" name="min" value="${ param.min }"> 元 -
                <input id="max" type="text" name="max" value="${ param.max }" > 元
                <input type="submit" value="查询" />
            </form>
        </div>

        <h1 id="unFund">未找到图书信息！</h1>
    </div>

</div>

<%@ include file="/pages/common/foot.jsp"%>
</body>
</html>