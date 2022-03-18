package com.atsk.test;

import com.atsk.pojo.User;
import com.atsk.service.impl.UserServiceImpl;
import com.atsk.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author Ly
 * @date 2021-07-07 17:57
 */
public class Test02_UserServiceTest {
    private UserServiceImpl dao = new UserServiceImpl();

    @Test
    public void testRegisterUser() {
        try {
            Connection conn = JdbcUtil.getConnection();
            if (dao.existsUsername("Jack")) {
                System.out.println("用户已存在");
            } else {
                dao.registerUser(new User("Jack", "123456", "15665465","jack@qq.com"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoginUser() {
        try {
            Connection conn = JdbcUtil.getConnection();
            User user = dao.login(new User("lucky", "991229"));
            if (user == null) {
                System.out.println("登录失败");
            } else {
                System.out.println("登录成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testExistsUser() {
        try {
            Connection conn = JdbcUtil.getConnection();
            if (dao.existsUsername("admin12")) {
                System.out.println("用户名存在，不可用");
            } else {
                System.out.println("用户名可用");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
