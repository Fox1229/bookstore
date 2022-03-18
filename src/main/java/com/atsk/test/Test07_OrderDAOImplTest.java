package com.atsk.test;

import com.atsk.dao.OrderDAO;
import com.atsk.dao.impl.OrderDAOImpl;
import com.atsk.pojo.Order;
import com.atsk.util.JdbcUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Ly
 * @date 2021-07-14 15:52
 */
public class Test07_OrderDAOImplTest {
    private OrderDAO orderDAO = new OrderDAOImpl();


    @Test
    public void testTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        System.out.println(format);
    }

    @Test
    public void testGetAll() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            List<Order> list = orderDAO.getAllOrder();
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }
    }

    @Test
    public void testGetOrderByUserId() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            List<Order> list = orderDAO.getOrderByUserId(1);
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }

    }

    @Test
    public void testChangeOrder() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            orderDAO.changeOrderStatus("1234", 1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }

    }

    @Test
    public void testSaveOrder() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
//            long time = System.currentTimeMillis();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String date = sdf.format(time);
//            Date parse = sdf.parse(date);
//            System.out.println(parse);

//            orderDAO.saveOrder(new Order(1, "0714001103", new Date(), new BigDecimal(45), 0, 1));
//            orderDAO.saveOrder(new Order(1, "0714021004", new Date(), new BigDecimal(45), 0, 1));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }
    }
}
