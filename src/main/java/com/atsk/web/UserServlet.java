package com.atsk.web;

import com.atsk.pojo.User;
import com.atsk.service.impl.UserServiceImpl;
import com.atsk.util.WebUtil;
import net.sf.json.JSONObject;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author Ly
 * @date 2021-07-08 9:27
 */
public class UserServlet extends BaseServlet {

    private UserServiceImpl dao = new UserServiceImpl();

    /**
     * 处理登录
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = dao.login(new User(username, password));
        if (user == null) {
            //登录失败
            request.setAttribute("msg", "用户名或密码错误");
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        } else {
            //登录成功
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cookie);
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("/pages/user/login_success.jsp").forward(request, response);
        }
    }

    /**
     * 处理注册
     */
    protected void register(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String repwd = request.getParameter("repwd");
        String codeAttribute = (String) request.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        request.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        String code = request.getParameter("code");

        User user = WebUtil.copyParamToBean(request.getParameterMap(), new User());
        request.setAttribute("username", user.getUsername());
        request.setAttribute("password", user.getPassword());
        request.setAttribute("repwd", repwd);
        request.setAttribute("phone", user.getPhone());
        request.setAttribute("email", user.getEmail());

        if (codeAttribute != null && codeAttribute.equals(code)) {
            // 验证码正确
            if (dao.existsUsername(user.getUsername())) {
                //用户名存在,返回首页
                request.setAttribute("msg", "用户名已存在");
                request.getRequestDispatcher("/pages/user/register.jsp").forward(request, response);
            } else {
                //用户名不存在,执行注册
                dao.registerUser(new User(user.getUsername(), user.getPassword(), user.getPhone(), user.getEmail()));
                request.getRequestDispatcher("/pages/user/register_success.jsp").forward(request, response);
            }
        } else {
            //验证码错误，返回首页
            request.setAttribute("msg", "验证码错误");
            request.getRequestDispatcher("/pages/user/register.jsp").forward(request, response);
        }
    }

    /**
     * 注销功能
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 销毁Session 中用户登录的信息
        request.getSession().invalidate();
        // 重定向到首页（或登录页面）
        response.sendRedirect(request.getContextPath());
    }

    /**
     * 使用AJAX判断用户名是否存在
     */
    protected void ajaxExistsUsername(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String username = request.getParameter("username");
        boolean existsUsername = dao.existsUsername(username);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("existsUsername", existsUsername);
        response.getWriter().write(jsonObject.toString());
    }
}
