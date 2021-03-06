package com.atsk.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author Ly
 * @date 2021-07-08 10:23
 */
public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 解决post请求中文乱码
        request.setCharacterEncoding("UTF-8");
        // 解决响应中文乱码
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");

        try {
            // 获取action业务鉴别字符串，获取相应的业务方法反射对象
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            // 调用目标业务方法
            method.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            // 把异常抛给Filter过滤器
            throw new RuntimeException(e);
        }
    }
}
