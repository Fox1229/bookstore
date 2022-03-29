<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑图书</title>
    <%@ include file="../common/head.jsp" %>
    <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#rollBack").click(function () {
                var pageNo = $("#pageNoNumber").val();
                location.href = "${ pageScope.basePath }manager/bookServlet?action=page&pageNo=" + pageNo;
            });
        });

    </script>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }

        input, form div {
            text-align: center;
        }
    </style>
</head>
<body>
<div id="header">
    <span class="wel_word">编辑图书</span>
    <%@ include file="../common/manager_menu.jsp" %>
</div>

<div id="main">
    <form action="manager/bookServlet">
        <input type="hidden" name="action" value="${ empty param.id?"addBook":"updateBook" }">
        <table>
            <c:forEach items="${ requestScope.book }" var="book">
                <tr>
                    <td>
                        <input name="bookId" type="hidden" value="${ book.id }">
                    </td>
                </tr>
                <tr>
                    <td>名称：</td>
                    <td><input name="name" type="text" value="${ book.name }"/></td>
                </tr>
                <tr>
                    <td>价格：</td>
                    <td><input name="price" type="text" value="${ book.price }"/></td>
                </tr>
                <tr>
                    <td>作者：</td>
                    <td><input name="author" type="text" value="${ book.author }"/></td>
                </tr>
                <tr>
                    <td>销量：</td>
                    <td><input name="sales" type="text" value="${ book.sales }"/></td>
                </tr>
                <tr>
                    <td>库存：</td>
                    <td><input name="stock" type="text" value="${ book.stock }"/></td>
                </tr>
                <tr>
                    <td>图片：</td>
                    <td>
                        <img src="http://localhost:8080/img/${ book.imgName }"
                             class="img-thumbnail" alt="未加载！" width="304" height="236">
                        <input type="hidden" name="oldImg" value="${ book.imgName }"/>
                    </td>
                    <td>
                        <input name="imgName" type="file" id="newImg"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <div>
            <button type="submit">提交</button>
            <button type="button" id="rollBack">取消</button>
        </div>
    </form>
</div>

<%@ include file="/pages/common/foot.jsp" %>
</body>
</html>