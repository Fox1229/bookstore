package com.atsk.web;

import com.atsk.pojo.Book;
import com.atsk.pojo.Cart;
import com.atsk.pojo.CartItems;
import com.atsk.service.BookService;
import com.atsk.service.impl.BookServiceImpl;
import com.atsk.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

/**
 * @author Ly
 * @date 2021-07-13 19:04
 */
public class CartServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();
//    private JSONObject json = new JSONObject();

    /**
     * 添加商品
     */
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 获取要添加书本的id
        int id = WebUtil.parseInt(request.getParameter("id"), 0);
        // 根据id查找要添加课本的信息
        Book book = bookService.getBookById(id);

        // 将图书信息转换为购物车商品项
        CartItems cartItems = new CartItems(book.getId(), book.getName(), 1, BigDecimal.valueOf(book.getPrice()), BigDecimal.valueOf(book.getPrice()));
        // 添加商品项
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItems);

        request.getSession().setAttribute("lastName", cartItems.getName());
        // 获取添加的商品的URL
        String referer = request.getHeader("Referer");
        // 重定向到原来商品所在的页面
        response.sendRedirect(referer);
    }

    /**
     * 删除商品
     */
    protected void deleteItemById(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 获取删除信息的id
        int id = WebUtil.parseInt(request.getParameter("id"), 0);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            cart.deleteItemById(id);
            // 重定向到原来商品所在的页面
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    /**
     * 清空购物车
     */
    protected void deleteAll(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            cart.deleteAll();
            // 重定向到原来商品所在的页面
            response.sendRedirect(request.getHeader("Referer"));
        }
    }

    /**
     * 修改商品数量
     */
    protected void updateItemCount(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int id = WebUtil.parseInt(request.getParameter("id"), 0);
        int count = WebUtil.parseInt(request.getParameter("count"), 0);

        // 获取删除信息的id
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart != null) {
            cart.updateItemCount(id, count);
            // 重定向到原来商品所在的页面
            response.sendRedirect(request.getHeader("Referer"));
        }
    }
}