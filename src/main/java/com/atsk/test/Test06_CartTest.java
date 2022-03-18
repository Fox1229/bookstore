package com.atsk.test;

import com.atsk.pojo.Cart;
import com.atsk.pojo.CartItems;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author Ly
 * @date 2021-07-13 18:01
 */
public class Test06_CartTest {
    private Cart cart = new Cart();

    @Test
    public void test3(){

        JSONObject jsonObject = new JSONObject();
        //        json.put("cartTotalCount",cart.getTotalCount());
//        json.put("lastName",cartItems.getName());
        jsonObject.put("cartTotalCount","1");
        jsonObject.put("lastName","2");
        System.out.println(jsonObject.toString());
    }

    @Test
    public void testAddItem(){
        cart.addItem(new CartItems(1,"边城",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItems(1,"边城",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItems(2,"三体",1,new BigDecimal(50),new BigDecimal(50)));

        System.out.println(cart);
    }

    @Test
    public void testDelete(){
        cart.addItem(new CartItems(1,"边城",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItems(1,"边城",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItems(2,"三体",1,new BigDecimal(50),new BigDecimal(50)));
        cart.deleteItemById(1);

        System.out.println(cart);

    }

    @Test
    public void testDeleteAll(){
        cart.addItem(new CartItems(1,"边城",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItems(1,"边城",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItems(2,"三体",1,new BigDecimal(50),new BigDecimal(50)));

        cart.deleteAll();
        System.out.println(cart);

    }

    @Test
    public void testUpdate(){
        cart.addItem(new CartItems(1,"边城",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItems(1,"边城",1,new BigDecimal(100),new BigDecimal(100)));
        cart.addItem(new CartItems(2,"三体",1,new BigDecimal(50),new BigDecimal(50)));

        cart.deleteAll();
        cart.addItem(new CartItems(1,"边城",1,new BigDecimal(100),new BigDecimal(100)));

        cart.updateItemCount(1,4);
        System.out.println(cart);

    }


}
