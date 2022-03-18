package com.atsk.dao.impl;

import com.atsk.dao.BaseDAO;
import com.atsk.dao.OrderDAO;
import com.atsk.pojo.Order;

import java.util.List;

/**
 * @author Ly
 * @date 2021-07-14 15:39
 */
public class  OrderDAOImpl extends BaseDAO<Order> implements OrderDAO {

    @Override
    public void saveOrder(Order order) {
        String sql = "insert into t_order(order_id,create_time,price,status,user_id,isuserorder) values(?,?,?,?,?,0)";
        update(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
    }

    @Override
    public void changeOrderStatus(String orderId, int status) {
        String sql = "update t_order set status = ? where order_id = ?";
        update(sql, status, orderId);
    }

    @Override
    public List<Order> getOrderByUserId(Integer userId) {
        String sql = "select order_id orderId,create_time createTime,price,status,user_id userId from t_order where user_id = ? and isuserorder = 0";
        return getForList(sql, userId);
    }

    @Override
    public List<Order> getAllOrder() {
        String sql = "select order_id orderId,create_time createTime,price,status,user_id userId from t_order";
        return getForList(sql);
    }

    @Override
    public void deleteOrder(String orderId) {
        // 删除订单项
        String sql1 = "DELETE FROM t_order_item WHERE order_id = ?";
        update(sql1,orderId);

        // 删除订单
        String sql2 = "delete FROM t_order where order_id = ?";
        update(sql2,orderId);
    }

    @Override
    public void delUserOrder(String orderId) {
        String sql = "update t_order set isuserorder = 1 where order_id = ?";
        update(sql,orderId);
    }
}
