package com.atsk.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Ly
 * @date 2021-07-13 17:06
 * <p>
 * 购物车对象
 */
public class Cart {

    private Map<Integer,CartItems> items = new LinkedHashMap<>();

    /**
     * 添加商品
     */
    public void addItem(CartItems cartItems){
        // 先判断购物车中有无此商品，如果没有则添加；如果有，则更新商品数量和总金额
        CartItems item = items.get(cartItems.getId());
        if(item != null){
            //商品已存在
            item.setCount(item.getCount() + 1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }else{
            //商品不存在
            items.put(cartItems.getId(),cartItems);
        }
    }

    /**
     * 删除商品
     */
    public void deleteItemById(Integer id){
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void deleteAll(){
        items.clear();
    }

    /**
     * 修改商品数量
     */
    public void updateItemCount(Integer id,Integer count){
        // 先判断购物车中是否有此商品，如果有则修改商品数量，更新总金额
        CartItems item = items.get(id);
        if(item != null){
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }


    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer, CartItems> entry : items.entrySet()){
            totalCount += entry.getValue().getCount();
        }

        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer, CartItems> entry : items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }

        return totalPrice;
    }


    public Map<Integer, CartItems> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItems> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
