<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户登录页面</title>
    <base href="http://localhost:8080/bookstore/">
    <%@ include file="../common/head.jsp"%>
    <script type="text/javascript">
        $(function () {
            $("#sub_btn").click(function () {
                var username = $("#username").val().trim();
                if (username == null || username == "") {
                    $("span.errorMsg").text("用户名不能为空");
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
        <span class="login_word">欢迎登录</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>用户登录</h1>
                    <a href="${ pageScope.basePath }pages/user/register.jsp">立即注册</a>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <span class="errorMsg">
                        ${ empty requestScope.msg ? "请输入用户名和密码":requestScope.msg }
                    </span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="login"/>
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username" id="username"
                               <%-- 若没有输入用户名，则默认显示Cookie中的用户名；若输入用户名，则显示用户输入的用户名 --%>
                               value="${ empty requestScope.username?cookie.username.value:requestScope.username }"
                        />
                        <br/>
                        <br/>
                        <label>用户密码：</label>
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