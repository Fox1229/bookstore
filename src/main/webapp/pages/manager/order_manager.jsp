<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript">
		$(function (){
			$(".sendOrder").click(function (){
				return confirm("确认发货？");
			});
			$(".receiverOrder").click(function (){
				return confirm("确认签收？");
			});
			$(".enableRollBackOrder").click(function (){
				return confirm("同意退货？");
			});
			$(".successRollBackOrder").click(function (){
				return confirm("确认完成退货？");
			});
			$(".deleteOrder").click(function (){
				return confirm("确认删除订单？");
			});
		});
	</script>
</head>
<body>
	<div id="header">
			<span class="wel_word">订单管理</span>
			<%@ include file="../common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>订单详情</td>
				<td>订单状态</td>
				<td>修改状态</td>
			</tr>

			<c:forEach items="${ requestScope.orders }" var="order">
				<tr>
					<td>${ order.createTime}</td>
					<td>${ order.price}</td>
					<td>
						<a href="${ pageScope.basePath }orderServlet?action=showOrderDetail&orderId=${ order.orderId }">
						查看详情</a></td>
					<td>
						<c:choose>
							<c:when test="${ order.status == 0 }">未发货</c:when>
							<c:when test="${ order.status == 1 }">已发货</c:when>
							<c:when test="${ order.status == 2 }">已签收</c:when>
							<c:when test="${ order.status == 3 }">已收货</c:when>
							<c:when test="${ order.status == 4 }">申请退货</c:when>
							<c:when test="${ order.status == 5 }">退货中</c:when>
							<c:when test="${ order.status == 6 }">退货成功</c:when>
							<c:when test="${ order.status == 7 }">已取消</c:when>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${ order.status == 0}">
								<a href="${ pageScope.basePath }orderServlet?action=sendOrder&orderId=${ order.orderId }"
								   class="sendOrder">发货</a>
							</c:when>
							<c:when test="${ order.status == 4 }">
								<a href="${ pageScope.basePath }orderServlet?action=enableRollBackOrder&orderId=${ order.orderId }"
								   class="enableRollBackOrder">同意退货</a>
							</c:when>
							<c:when test="${ order.status == 5 }">
								<a href="${ pageScope.basePath }orderServlet?action=successRollBackOrder&orderId=${ order.orderId }"
								   class="successRollBackOrder">完成收货</a>
							</c:when>
							<c:when test="${ order.status == 7 }">
								<a href="${ pageScope.basePath }orderServlet?action=deleteOrder&orderId=${ order.orderId }"
								   class="deleteOrder">删除订单</a>
							</c:when>
						</c:choose>
					</td>
				</tr>
			</c:forEach>

		</table>
	</div>

	<%@ include file="/pages/common/foot.jsp"%>
</body>
</html>