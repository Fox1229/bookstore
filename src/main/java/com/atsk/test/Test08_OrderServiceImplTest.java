package com.atsk.test;

import com.atsk.pojo.Cart;
import com.atsk.pojo.CartItems;
import com.atsk.pojo.Order;
import com.atsk.pojo.OrderItems;
import com.atsk.service.OrderService;
import com.atsk.service.impl.OrderServiceImpl;
import com.atsk.util.JdbcUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * @author Ly
 * @date 2021-07-15 15:26
 */
public class Test08_OrderServiceImplTest {
    private OrderService test = new OrderServiceImpl();

    @Test
    public void testCheckStock(){
        Integer stock = test.checkStock(2);
        System.out.println(stock);

    }


    @Test
    public void testTime(){
        long time = new Date().getTime();
        System.out.println(time);
        System.out.println();
        int userId = 1;
        String strId = String.valueOf(time) + "" + userId;
        System.out.println(strId);

        System.out.println();
        String show = System.currentTimeMillis() + "" + userId;
        System.out.println(show);

    }

    @Test
    public void testShowOrderDetail(){
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            List<OrderItems> list = test.showOrderDetail( "16263408699981");
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }

    }

    @Test
    public void testShowAllOrder(){
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            List<Order> orders = test.showAllOrder();
            orders.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }

    }



    @Test
    public void testShowMyOrders(){
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            List<Order> orders = test.showMyOrders(1);
            orders.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }

    }

    @Test
    public void testReceiverOrder(){
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            test.receiverOrder("071500002");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }

    }

    @Test
    public void testSendOrder(){
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            test.sendOrder("071500002");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }

    }

    @Test
    public void testCreateOrder(){
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            Cart cart = new Cart();
            cart.addItem(new CartItems(2,"三体",1,new BigDecimal(50),new BigDecimal(50)));
            String orderId = test.createOrder(cart, 2);
            System.out.println(orderId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }

    }
}
