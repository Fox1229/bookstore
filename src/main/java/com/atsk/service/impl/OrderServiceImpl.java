package com.atsk.service.impl;

import com.atsk.dao.BookDAO;
import com.atsk.dao.OrderDAO;
import com.atsk.dao.OrderItemsDAO;
import com.atsk.dao.impl.BookDAOImpl;
import com.atsk.dao.impl.OrderDAOImpl;
import com.atsk.dao.impl.OrderItemsDAOImpl;
import com.atsk.pojo.*;
import com.atsk.service.BookService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.atsk.service.OrderService;

/**
 * @author Ly
 * @date 2021-07-15 15:03
 */
public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO = new OrderDAOImpl();
    private OrderItemsDAO itemsDAO = new OrderItemsDAOImpl();
    private BookService bookService = new BookServiceImpl();
    private BookDAO bookDAO = new BookDAOImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {

        String orderId = System.currentTimeMillis() + "" + userId;

        // 创建一个订单对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Order order = new Order(orderId, sdf.format(new Date()), cart.getTotalPrice(), 0, userId);
        // 保存订单
        orderDAO.saveOrder(order);

        // 遍历购物车中每一个商品项转化为订单项保存到数据库
        for (Map.Entry<Integer, CartItems> entry : cart.getItems().entrySet()) {
            // 获取每一个购物车中的商品项
            CartItems cartItem = entry.getValue();
            // 转化为每一个订单项
            OrderItems orderItem = new OrderItems(null, cartItem.getName(), cartItem.getCount(),
                    cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            // 保存订单项
            itemsDAO.saveOrderItem(orderItem);

            Book book = bookService.getBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookService.updateBook(book);
        }
        // 清空购物车
        cart.deleteAll();
        // 返回订单号
        return orderId;
    }

    @Override
    public Integer checkStock(Integer bookId) {
        Book book = bookDAO.getBookById(bookId);
        return book.getStock();
    }

    @Override
    public void sendOrder(String orderId)    {
        orderDAO.changeOrderStatus(orderId, 1);
    }

    @Override
    public void receiverOrder(String orderId)    {
        orderDAO.changeOrderStatus(orderId, 2);
    }

    @Override
    public void confirmOrder(String orderId)    {
        orderDAO.changeOrderStatus(orderId, 3);
    }

    @Override
    public void rollBackOrder(String orderId) {
        orderDAO.changeOrderStatus(orderId, 4);
    }

    @Override
    public void enableRollBackOrder(String orderId) {
        orderDAO.changeOrderStatus(orderId, 5);
    }

    @Override
    public void successRollBackOrder(String orderId) {
        orderDAO.changeOrderStatus(orderId, 6);
    }

    @Override
    public List<OrderItems> showOrderDetail(String orderId)    {
        return itemsDAO.queryOrderItemByOrderId(orderId);
    }

    @Override
    public List<Order> showMyOrders(int userId)    {
        return orderDAO.getOrderByUserId(userId);
    }

    @Override
    public List<Order> showAllOrder()    {
        return orderDAO.getAllOrder();
    }

    @Override
    public void deleteOrder(String orderId) {
        orderDAO.deleteOrder(orderId);
    }

    @Override
    public void cancelOrder(String orderId) {
        orderDAO.changeOrderStatus(orderId,7);
    }

    @Override
    public void delUserOrder(String orderId) {
        orderDAO.delUserOrder(orderId);
    }
}
