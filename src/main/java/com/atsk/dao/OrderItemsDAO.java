package com.atsk.dao;

import com.atsk.pojo.OrderItems;

import java.util.List;

/**
 * @author Ly
 * @date 2021-07-14 21:23
 */
public interface OrderItemsDAO {

    /**
     * 保存订单项
     */
    void saveOrderItem(OrderItems orderItem);

    /**
     * 根据订单号查询订单明细
     */
    List<OrderItems> queryOrderItemByOrderId(String orderId);
}
