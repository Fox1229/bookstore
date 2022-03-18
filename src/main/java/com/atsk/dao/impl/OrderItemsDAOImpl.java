package com.atsk.dao.impl;

import com.atsk.dao.BaseDAO;
import com.atsk.dao.OrderItemsDAO;
import com.atsk.pojo.OrderItems;

import java.util.List;

/**
 * @author Ly
 * @date 2021-07-14 21:34
 */
public class OrderItemsDAOImpl extends BaseDAO<OrderItems> implements OrderItemsDAO {

    @Override
    public void saveOrderItem(OrderItems orderItem) {
        String sql = "insert into t_order_item(name,count,price,total_price,order_id) values(?,?,?,?,?)";
        update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalPrice(),orderItem.getOrderId());
    }

    @Override
    public List<OrderItems> queryOrderItemByOrderId(String orderId) {
        String sql = "select id,name,count,price,total_price totalPrice,order_id orderId from t_order_item where order_id = ?";
        return getForList(sql, orderId);
    }
}
