package com.atsk.pojo;

import java.math.BigDecimal;

/**
 * @author Ly
 * @date 2021-07-13 17:08
 *
 * 购物车商品项
 */
public class CartItems {

    private Integer id;
    private String name;
    private Integer count;
    private BigDecimal price;// 商品单价
    private BigDecimal totalPrice;// 商品总价

    public CartItems() {
    }

    public CartItems(Integer count) {
        this.count = count;
    }

    public CartItems(Integer id, String name, Integer count, BigDecimal price, BigDecimal totalPrice) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartItems{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
