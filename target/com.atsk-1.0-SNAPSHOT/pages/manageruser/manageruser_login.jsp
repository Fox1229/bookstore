<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理员登录页面</title>
    <base href="http://localhost:8080/bookstore/">
    <%@ include file="../common/head.jsp"%>
    <script type="text/javascript">
        $(function () {
            $("#sub_btn").click(function () {
                var managerName = $("#managerName").val().trim();
                if (managerName == null || managerName == "") {
                    $("span.errorMsg").text("账户名不能为空");
                    return false;
                }

                var password = $("#pwd").val().trim();
                if (password == null || password == "") {
                    $("span.errorMsg").text("密码不能为空");
                    return false;
                }

                $("span.errorMsg").text("");
            });
        });
    </script>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/zsg.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎管理员登录</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>管理员登录</h1>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <span class="errorMsg">
                        ${ empty requestScope.msg ? "请输入账户名和密码":requestScope.msg }
                    </span>
                </div>
                <div class="form">
                    <form action="managerUserServlet" method="post">
                        <input type="hidden" name="action" value="managerLogin"/>
                        <label>账户：</label>
                        <input class="itxt" type="text" placeholder="请输入账户名" autocomplete="off" tabindex="1"
                               name="managerName" id="managerName"
                               value="${ empty requestScope.managerName?cookie.managerName.value:requestScope.managerName }"/>
                        <br/>
                        <br/>
                        <label>密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1"
                               name="password" id="pwd" value="${ requestScope.password }"/>
                        <br/>
                        <br/>
                        <input type="submit" value="登录" id="sub_btn"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/pages/common/foot.jsp" %>

</body>
</html>