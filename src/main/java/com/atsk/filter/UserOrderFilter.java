package com.atsk.filter;

import com.atsk.pojo.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Ly
 * @date 2021-07-17 7:03
 */
public class UserOrderFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        User loginUser = (User) httpServletRequest.getSession().getAttribute("user");
        if (loginUser != null) {
            // 用户已登录
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // 用户没有登录
            servletRequest.getRequestDispatcher("/pages/user/login.jsp").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
