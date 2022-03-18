<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.atsk.pojo.Order" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.sun.org.apache.xpath.internal.operations.Or" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>
	<%@ include file="../common/head.jsp"%>
	<script type="text/javascript">
		$(function (){
			// 给确认收货绑定单击事件
			$(".confirmOrder").click(function (){
				return confirm("确认收货？")
			});
			$(".rollBackOrder").click(function (){
				return confirm("确认退货？")
			});
			$(".cancelOrder").click(function (){
				return confirm("确认取消订单？")
			});
		});
	</script>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
</head>
<body>
	<div id="header">
		<span class="wel_word">我的订单</span>
		<%@ include file="../common/login_success_menu.jsp" %>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
				<td>操作</td>
			</tr>
			<%-- 订单为空 --%>
			<c:if test="${ empty requestScope.orders }">
				<tr>
					<td colspan="5"><a href="index.jsp"/>没有您的订单，点击去剁手！！！</td>
				</tr>
			</c:if>
			<c:forEach items="${ requestScope.orders }" var="order">
				<tr>
					<td>${ order.createTime}</td>
					<td>${ order.price}</td>
					<td>
						<c:choose>
							<c:when test="${ order.status == 0 }">待发货</c:when>
							<c:when test="${ order.status == 1 }">已发货</c:when>
							<c:when test="${ order.status == 2 }">已签收</c:when>
							<c:when test="${ order.status == 3 }">已收货</c:when>
							<c:when test="${ order.status == 4 }">退货受理中</c:when>
							<c:when test="${ order.status == 5 }">退货中</c:when>
							<c:when test="${ order.status == 6 }">退货成功</c:when>
							<c:when test="${ order.status == 7 }">已取消</c:when>
						</c:choose>
					</td>
					<td><a href="${ pageScope.basePath }orderServlet?action=showOrderDetail&orderId=${ order.orderId }">查看详情</a></td>
					<%-- 订单未发货，用户可取消订单 --%>
					<c:if test="${ order.status == 0 }">
						<td><a href="${ pageScope.basePath }orderServlet?action=cancelOrder&orderId=${ order.orderId }"
							   class="cancelOrder">取消订单</a></td>
					</c:if>
					<%-- 用户收到货物后，用进行签收 --%>
					<c:if test="${ order.status == 1 }">
						<td><a href="${ pageScope.basePath }orderServlet?action=receiverOrder&orderId=${ order.orderId }"
							   class="cancelOrder">签收</a></td>
					</c:if>
					<%-- 若商品已签收，用户可以选择收货或者申请退货 --%>
					<c:if test="${ order.status == 2 }">
						<td>
							<a href="${ pageScope.basePath }orderServlet?action=confirmOrder&orderId=${ order.orderId }"
							   class="confirmOrder">确认收货
							</a>
						</td>
						<td>
							<a href="${ pageScope.basePath }orderServlet?action=rollBackOrder&orderId=${ order.orderId }"
							   class="rollBackOrder">申请退货</a>
						</td>
					</c:if>
					<c:if test="${ order.status == 7 }">
						<td>
							<a href="${ pageScope.basePath }orderServlet?action=deleteUserOrder&orderId=${ order.orderId }"
							   class="deleteUserOrder">删除订单
							</a>
						</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</div>

	<%@ include file="/pages/common/foot.jsp"%>
</body>
</html>