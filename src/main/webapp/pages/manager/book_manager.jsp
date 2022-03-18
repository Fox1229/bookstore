<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript">
		$(function (){
			$("a.delete").click(function (){
				return confirm("确认删除[ "+ $(this).parent().parent().find("td:first").text() +" ]？");
			});

			$("#searchPageBtn").click(function (){
				var pageNo = $("#pn_input").val().trim();
				// javaScript 语言提供了一个location 地址栏对象，它有一个属性交href，可以获取浏览器地址栏中的地址，
				// href属性可读，可写
				location.href="${ pageScope.basePath }manager/bookServlet?action=page&pageNo=" + pageNo;
			});
		});
	</script>
</head>
<body>
	
	<div id="header">
			<span class="wel_word">图书管理</span>
		<%@ include file="../common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>名称</td>
				<td>价格</td>
				<td>作者</td>
				<td>销量</td>
				<td>库存</td>
				<td colspan="1">操作</td>
			</tr>
			<c:forEach items="${ requestScope.page.items }" var="book">
				<tr>
					<td>${ book.name }</td>
					<td>${ book.price }</td>
					<td>${ book.author }</td>
					<td>${ book.sales }</td>
					<td>${ book.stock }</td>
					<td><a href="manager/bookServlet?action=getBookById&id=${ book.id }
					        &pageNo=${ requestScope.page.pageNo }">修改</a>&nbsp;&nbsp;
						<a class="delete" href="manager/bookServlet?action=delBook&id=${ book.id }
						    &pageNo=${ requestScope.page.pageNo }">删除</a>
					</td>
				</tr>
			</c:forEach>

			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?&pageNo=${ requestScope.page.pageTotal }">添加图书</a></td>
			</tr>
		</table>

		<%-- 静态包含分页条--%>
		<%@ include file="/pages/common/page_pav.jsp"%>

	</div>
	<%@ include file="/pages/common/foot.jsp"%>
</body>
</html>