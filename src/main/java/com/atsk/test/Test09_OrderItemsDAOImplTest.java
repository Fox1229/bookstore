package com.atsk.test;

import com.atsk.dao.OrderItemsDAO;
import com.atsk.dao.impl.OrderItemsDAOImpl;
import com.atsk.pojo.OrderItems;
import com.atsk.util.JdbcUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

/**
 * @author Ly
 * @date 2021-07-14 21:43
 */
public class Test09_OrderItemsDAOImplTest {
    private OrderItemsDAO dao = new OrderItemsDAOImpl();

    @Test
    public void testQueryOrderItemByOrderId(){
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            List<OrderItems> list = dao.queryOrderItemByOrderId("0714001103");
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }

    }

    @Test
    public void testSaveOrderItem(){
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            dao.saveOrderItem(new OrderItems(2,"Java入门",2,new BigDecimal(10),new BigDecimal(20),"0714001103"));
            dao.saveOrderItem(new OrderItems(3,"C++",1,new BigDecimal(50),new BigDecimal(50),"0714001103"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }

    }
}
