<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>后台管理</title>
	<%@ include file="../common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	h1 span{
		color: red;
		font-size: 25px;
		margin: 10px;
	}
</style>
</head>
<body>
	<div id="header">
<%--			<img class="logo_img" alt="" src="../../static/img/logo.gif" >--%>
			<span class="wel_word">后台管理系统</span>
		<%@ include file="../common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<h1>欢迎管理员<span>${ sessionScope.managerUser.managerName }</span>进入后台管理系统</h1>
	</div>

	<%@ include file="/pages/common/foot.jsp"%>
</body>
</html>