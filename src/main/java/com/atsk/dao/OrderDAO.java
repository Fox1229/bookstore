package com.atsk.dao;

import com.atsk.pojo.Order;

import java.util.List;

/**
 * @author Ly
 * @date 2021-07-14 15:31
 */
public interface OrderDAO {

    /**
     * 保存订单
     */
    void saveOrder(Order order);

    /**
     * 修改订单状态
     */
    void changeOrderStatus(String orderId, int status);

    /**
     * 根据用户编号查询订单信息
     */
    List<Order> getOrderByUserId(Integer userId);

    /**
     * 查询所有订单
     */
    List<Order> getAllOrder();

    /**
     * 删除订单
     */
    void deleteOrder(String orderId);

    /**
     * 将某一个订单从用户的订单表中移除
     */
    void delUserOrder(String orderId);
}
