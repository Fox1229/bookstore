<%--
  Created by IntelliJ IDEA.
  User: 运
  Date: 2021/7/17
  Time: 18:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>shortStock</title>
    <style>
        div {
            color: #000000;
            font-size: 22px;
        }
        span {
            color: #8A2BE2;
            font-size: 25px;
        }

        span1 {
            color: #DDA0DD;
            font-size: 22px;
        }

        div a {
            border: none;
            width: 120px;
            height: 80px;
            color: #fff;
            border-radius: 5px;
            background: #FF69B4;
        }
    </style>
</head>
<body>
    <div>非常抱歉<span> ${ requestScope.cartItemName } </span>
        库存不足(<span1>当前库存：${ requestScope.stock }</span1>),支付失败！！！
    </div>
    <div><a href="${ pageScope.basePath }index.jsp">返回首页</a></div>
</body>
</html>
