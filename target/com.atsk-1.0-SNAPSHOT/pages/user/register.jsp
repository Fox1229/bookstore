<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册页面</title>
    <base href="http://localhost:8080/bookstore/">
    <%@ include file="../common/head.jsp" %>
    <script type="text/javascript">
        // 页面加载完成之后
        $(function () {
            $("#password").change(function () {
                var passwordText = $("#password").val();
                var passwordPatt = /^\w{5,12}$/;
                if (!passwordPatt.test(passwordText)) {
                    $("span.errorMsg").text("密码不合法");
                } else {
                    $("span.errorMsg").text("");
                }
            });

            $("#repwd").change(function () {
                var passwordText = $("#password").val();
                var repwdText = $("#repwd").val();
                if (repwdText != passwordText) {
                    $("span.errorMsg").text("确认密码和密码不一致");
                } else {
                    $("span.errorMsg").text("");
                }
            });

            $("#phone").change(function () {
                var phoneText = $("#phone").val();
                var phonePatt = /1[3|5|7|8|]\d{9}/;
                if (!phonePatt.test(phoneText)) {
                    $("span.errorMsg").text("手机号码格式不合法");
                } else {
                    $("span.errorMsg").text("");
                }
            });

            $("#email").change(function () {
                var emailText = $("#email").val();
                var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                if (!emailPatt.test(emailText)) {
                    $("span.errorMsg").text("邮箱格式不合法");
                } else {
                    $("span.errorMsg").text("");
                }
            });

            // 给验证码图片绑定单击事件
            $("#code_img").click(function () {
                this.src = "${basePath}kaptcha.jpg?d+" + new Date().getTime();
            });

            // 给注册绑定单击事件
            $("#sub_btn").click(function () {
                // 验证用户名：必须由字母，数字下划线组成，并且长度为5到12位
                //1 获取用户名输入框里的内容
                var usernameText = $("#username").val();
                //2 创建正则表达式对象
                var usernamePatt = /^\w{5,12}$/;
                //3 使用test方法验证
                if (!usernamePatt.test(usernameText)) {
                    //4 提示用户结果
                    $("span.errorMsg").text("用户名不合法");
                    return false;
                }

                // 验证密码：必须由字母，数字下划线组成，并且长度为5到12位
                //1 获取用户名输入框里的内容
                var passwordText = $("#password").val();
                //2 创建正则表达式对象
                var passwordPatt = /^\w{5,12}$/;
                //3 使用test方法验证
                if (!passwordPatt.test(passwordText)) {
                    //4 提示用户结果
                    $("span.errorMsg").text("密码不合法");
                    return false;
                }

                // 验证确认密码：和密码相同
                //1 获取确认密码内容
                var repwdText = $("#repwd").val();
                //2 和密码相比较
                if (repwdText != passwordText) {
                    //3 提示用户
                    $("span.errorMsg").text("确认密码和密码不一致");
                    return false;
                }

                // 校验电话号码
                var phoneText = $("#phone").val();
                //2 创建正则表达式对象
                var phonePatt = /1[3|5|7|8|]\d{9}/;
                //3 使用test方法验证是否合法
                if (!phonePatt.test(phoneText)) {
                    //4 提示用户
                    $("span.errorMsg").text("手机号码格式不合法");
                    return false;
                }

                // 邮箱验证：xxxxx@xxx.com
                //1 获取邮箱里的内容
                var emailText = $("#email").val();
                //2 创建正则表达式对象
                var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                //3 使用test方法验证是否合法
                if (!emailPatt.test(emailText)) {
                    //4 提示用户
                    $("span.errorMsg").text("邮箱格式不合法");
                    return false;
                }

                var codeText = $("#code").val();
                //去掉验证码前后空格
                codeText = $.trim(codeText);
                if (codeText == null || codeText == "") {
                    //4 提示用户
                    $("span.errorMsg").text("验证码不能为空");
                    return false;
                }

                // 去掉错误信息
                $("span.errorMsg").text("");
            });

            $("#username").blur(function () {
                var username = this.value;
                var usernamePatt = /^\w{5,12}$/;
                if (!usernamePatt.test(username)) {
                    $("span.errorMsg").text("用户名不合法");
                } else {
                    // 用户名合法，判断用户名是否存在
                    $.getJSON(
                        "${ pageScope.basePath }userServlet", "action=ajaxExistsUsername&username=" + username, function (data) {
                            if (data.existsUsername) {
                                $("span.errorMsg").text("用户名已存在");
                            } else {
                                $("span.errorMsg").text("");
                            }
                        }
                    );
                }
            });
        });
    </script>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }
    </style>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/zsg.gif">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>用户注册</h1>
                    <span class="errorMsg">
                        ${ empty requestScope.msg?"":requestScope.msg }
                    </span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="register"/>
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名"
                               autocomplete="off" tabindex="1" name="username" id="username"
                               value="${ requestScope.username }"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码"
                               autocomplete="off" tabindex="1" name="password" id="password"
                               value="${ requestScope.password }"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input class="itxt" type="password" placeholder="确认密码"
                               autocomplete="off" tabindex="1" name="repwd" id="repwd"
                               value="${ requestScope.repwd }"/>
                        <br/>
                        <br/>
                        <label>手机号码：</label>
                        <input class="itxt" type="text" placeholder="请输入手机号码"
                               autocomplete="off" tabindex="1" name="phone" id="phone"
                               value="${ requestScope.phone }"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input class="itxt" type="text" placeholder="请输入邮箱地址"
                               autocomplete="off" tabindex="1" name="email" id="email"
                               value="${ requestScope.email }"/>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" type="text" style="width: 100px;" id="code" name="code"/>
                        <img alt="" id="code_img" src="kaptcha.jpg"
                             style="float: right; margin-right: 50px;width:110px;height:35px">
                        <br/>
                        <br/>
                        <input type="submit" value="立即注册" id="sub_btn"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/pages/common/foot.jsp" %>
</body>
</html>