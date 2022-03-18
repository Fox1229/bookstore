package com.atsk.web;

import com.atsk.pojo.ManagerUser;
import com.atsk.service.ManagerUserService;
import com.atsk.service.impl.ManagerUserServiceImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ly
 * @date 2021-07-16 20:56
 */
public class ManagerUserServlet extends BaseServlet {

    private ManagerUserService service = new ManagerUserServiceImpl();

    protected void managerLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String managerName = request.getParameter("managerName");
        String password = request.getParameter("password");
        ManagerUser managerUser = service.login(new ManagerUser(null, managerName, password));
        if (managerUser == null) {
            //登录失败
            request.setAttribute("msg", "用户名或密码错误");
            request.setAttribute("managerName", managerName);
            request.setAttribute("password", password);
            request.getRequestDispatcher("/pages/manageruser/manageruser_login.jsp").forward(request, response);
        } else {
            //登录成功
            Cookie cookie = new Cookie("managerName", managerName);
            cookie.setMaxAge(60 * 60 * 24 * 7);
            response.addCookie(cookie);
            request.getSession().setAttribute("managerUser", managerUser);
            response.sendRedirect(request.getContextPath() + "/pages/manager/manager.jsp");
        }
    }
}
