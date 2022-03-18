package com.atsk.filter;

import com.atsk.util.JdbcUtil;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author Ly
 * @date 2021-07-17 15:37
 */
public class TransactionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            // 提交事务，关闭连接
            JdbcUtil.commitAndClose();
        } catch (Exception e) {
            // 回滚事务，关闭连接
            JdbcUtil.rollbackAndClose();
            e.printStackTrace();
            // 把异常抛给服务器，展示错误提示页面
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {

    }
}
