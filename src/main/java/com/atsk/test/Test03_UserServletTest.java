package com.atsk.test;


import java.lang.reflect.Method;

/**
 * @author Ly
 * @date 2021-07-08 9:59
 */
public class Test03_UserServletTest {

    public static void main(String[] args) {
        String action = "updatePassword";

        try {
            //获取action业务鉴别字符串，获取相应的业务方法反射对象
            Method method = Test03_UserServletTest.class.getDeclaredMethod(action);

            //调用目标业务方法
            method.invoke(new Test03_UserServletTest());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void login(){
        System.out.println("login方法被调用...");
    }
    public void register(){
        System.out.println("register方法被调用...");
    }
    public void updatePassword(){
        System.out.println("updatePassword方法被调用...");
    }

}
