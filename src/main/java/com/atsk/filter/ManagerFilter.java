package com.atsk.filter;

import com.atsk.pojo.ManagerUser;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Ly
 * @date 2021-07-16 22:11
 */
public class ManagerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        ManagerUser managerUser = (ManagerUser) httpServletRequest.getSession().getAttribute("managerUser");
        if(managerUser != null){
            // 管理员已登录
            filterChain.doFilter(servletRequest,servletResponse);
        } else{
            // 管理员没有登录,请求转发到管理员登录页面
            httpServletRequest.getRequestDispatcher("/pages/manageruser/manageruser_login.jsp").forward(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
