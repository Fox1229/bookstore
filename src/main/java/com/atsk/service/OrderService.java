package com.atsk.service;

import com.atsk.pojo.Cart;
import com.atsk.pojo.Order;
import com.atsk.pojo.OrderItems;

import java.text.ParseException;
import java.util.List;

/**
 * @author Ly
 * @date 2021-07-15 14:51
 */
public interface OrderService {

    /**
     * 生成订单
     *
     * @param cart   订单对象
     * @param userId 用户id
     */
    String createOrder(Cart cart, Integer userId);

    /**
     * 根据传入的书本的id，返回课本的数量
     */
    Integer checkStock(Integer bookId);

    /**
     * 发货
     *
     * @param orderId 订单号
     */
    void sendOrder(String orderId);

    /**
     * 签收订单
     *
     * @param orderId 订单号
     */
    void receiverOrder(String orderId);

    /**
     * 确认收货
     */
    void confirmOrder(String orderId);

    /**
     * 消费者申请退货
     */
    void rollBackOrder(String orderId);

    /**
     * 商家同意退货
     */
    void enableRollBackOrder(String orderId);

    /**
     * 退货成功
     */
    void successRollBackOrder(String orderId);

    /**
     * 查看订单详情
     *
     * @param orderId 订单号
     */
    List<OrderItems> showOrderDetail(String orderId);

    /**
     * 查看我的订单
     *
     * @param userId 用户id
     */
    List<Order> showMyOrders(int userId);

    /**
     * 查询所有订单
     */
    List<Order> showAllOrder();

    /**
     * 删除订单
     */
    void deleteOrder(String orderId);

    /**
     * 取消订单
     */
    void cancelOrder(String orderId);

    /**
     * 将某一个订单从用户的订单表中移除
     */
    void delUserOrder(String orderId);
}
