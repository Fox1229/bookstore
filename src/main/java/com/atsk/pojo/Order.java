package com.atsk.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Ly
 * @date 2021-07-14 15:20
 * 订单
 */
public class Order {

    // 订单序号
    private Integer memberId = 1;
    private String orderId;
    private String createTime;
    private BigDecimal price;
    /**
     * 0 未发货
     * 1 已发货
     * 2 已签收
     * 3 确认收货
     * 4 消费者申请退货
     * 5 卖家同意退货
     * 6 商家收到货物，退货成功
     * 7 取消订单
     */
    private Integer status = 0;
    private Integer userId;
    /**
     * 1:订单已被用户删除
     * 0:订单没有被用户删除
     */
    private Integer isUserOrder = 0;

    public Order() {
    }

    public Order(String orderId) {
        this.orderId = orderId;
    }

    public Order(String orderId, String createTime, BigDecimal price, Integer status, Integer userId) {
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    public Order(Integer memberId, String orderId, String createTime, BigDecimal price, Integer status, Integer userId) {
        this.memberId = memberId;
        this.orderId = orderId;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    public Order(Integer memberId, String orderId, String createTime, BigDecimal price, Integer status, Integer userId, Integer isUserOrder) {
        this(memberId,orderId,createTime,price,status,userId);
        this.isUserOrder = isUserOrder;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsUserOrder() {
        return isUserOrder;
    }

    public void setIsUserOrder(Integer isUserOrder) {
        this.isUserOrder = isUserOrder;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                ", isUserOrder=" + isUserOrder +
                '}';
    }
}
