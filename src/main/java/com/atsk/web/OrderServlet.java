package com.atsk.web;

import com.atsk.pojo.*;
import com.atsk.service.OrderService;
import com.atsk.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Ly
 * @date 2021-07-14 15:32
 */
public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    /**
     * 生成订单
     */
    protected void createOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 获取当前购买商品用户的id
        User loginUser = (User) request.getSession().getAttribute("user");
        if (loginUser == null) {
            // 用户没有登录
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
            return;
        }

        // 获取登录用户的id
        Integer userId = loginUser.getId();
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        // 遍历购物车中每一个商品项,获取用户购买的数量
        for (Map.Entry<Integer, CartItems> entry : cart.getItems().entrySet()) {
            // 获取每一个购物车中的商品项
            CartItems cartItem = entry.getValue();
            // 根据商品id,查询购物车中的商品的库存
            Integer stock = orderService.checkStock(cartItem.getId());
            if (stock < cartItem.getCount()) {
                // 库存不足，支付失败；并提示用户
                String cartItemName = cartItem.getName();
                request.setAttribute("cartItemName", cartItemName);
                request.setAttribute("stock", stock);
                request.getRequestDispatcher("/pages/error/shortStock.jsp").forward(request, response);
                return;
            }
        }

        // 生成订单
        String orderId = orderService.createOrder(cart, userId);
        request.getSession().setAttribute("orderId", orderId);
        response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");
    }

    /**
     * 取消订单
     */
    protected void cancelOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String orderId = request.getParameter("orderId");
        orderService.cancelOrder(orderId);
        showMyOrder(request, response);
    }

    /**
     * 发货
     */
    protected void sendOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 获取要发货商品的orderId
        String orderId = request.getParameter("orderId");
        if (orderId != null) {
            // 发货
            orderService.sendOrder(orderId);
            showAllOrders(request, response);
        }
    }

    /**
     * 签收订单
     */
    protected void receiverOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String orderId = request.getParameter("orderId");
        orderService.receiverOrder(orderId);
        showMyOrder(request, response);
    }

    /**
     * 确认收货
     */
    protected void confirmOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String orderId = request.getParameter("orderId");
        orderService.confirmOrder(orderId);
        showMyOrder(request, response);
    }

    /**
     * 消费者申请退货
     */
    protected void rollBackOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String orderId = request.getParameter("orderId");
        orderService.rollBackOrder(orderId);
        showMyOrder(request, response);
    }

    /**
     * 商家同意退货
     */
    protected void enableRollBackOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String orderId = request.getParameter("orderId");
        orderService.enableRollBackOrder(orderId);
        showAllOrders(request, response);
    }

    /**
     * 商家同意退货
     */
    protected void successRollBackOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String orderId = request.getParameter("orderId");
        orderService.successRollBackOrder(orderId);
        showAllOrders(request, response);
    }

    /**
     * 查看订单详情
     */
    protected void showOrderDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 获取订单号
        String orderId = request.getParameter("orderId");
        // 根据订单号查询订单的详细
        List<OrderItems> orderDetail = orderService.showOrderDetail(orderId);
        request.setAttribute("orderDetail", orderDetail);

        // 请求转发到 pages/order/order_detail.jsp
        request.getRequestDispatcher("/pages/order/order_detail.jsp").forward(request, response);
    }


    /**
     * 查看我的订单
     */
    protected void showMyOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 获取当前用户id
        User loginUser = (User) request.getSession().getAttribute("user");
        Integer userId = loginUser.getId();

        // 查询当前用户的订单
        List<Order> orders = orderService.showMyOrders(userId);
        request.setAttribute("orders", orders);

        request.getSession().setAttribute("location", "showMyOrder");
        // 请求转发到 pages/order/order.jsp
        request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
    }

    /**
     * 查看所有订单
     */
    protected void showAllOrders(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Order> orders = orderService.showAllOrder();
        request.setAttribute("orders", orders);

        request.getSession().setAttribute("location", "showAllOrders");
        // 请求转发到 pages/manager/order_manager.jsp
        request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
    }

    /**
     * 删除订单
     */
    protected void deleteOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String orderId = request.getParameter("orderId");
        orderService.deleteOrder(orderId);
        showAllOrders(request, response);
    }

    /**
     * 删除用户的订单
     */
    protected void deleteUserOrder(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String orderId = request.getParameter("orderId");
        orderService.delUserOrder(orderId);
        showMyOrder(request, response);
    }
}
