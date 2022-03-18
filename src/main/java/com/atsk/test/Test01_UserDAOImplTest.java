package com.atsk.test;

import com.atsk.dao.impl.UserDAOImpl;
import com.atsk.pojo.User;
import com.atsk.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * @author Ly
 * @date 2021-07-07 17:07
 */
public class Test01_UserDAOImplTest {
    UserDAOImpl dao = new UserDAOImpl();

    @Test
    public void testGetAllUser() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            List<User> allUser = dao.getAllUser();
            allUser.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource( conn);
        }
    }

    @Test
    public void testGetUserByUsernamePassword() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            User user = dao.getUserByUsernamePassword("admin", "123456");
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource( conn);
        }
    }

    @Test
    public void testGetUserByName() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            User user = dao.getUserByName("admin");
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource( conn);
        }
    }

    @Test
    public void testGetUserById() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            User user = dao.getUserById(1);
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource( conn);
        }
    }

    @Test
    public void testUpdate() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            User user = new User(2, "Tom", "666666", "15941513512", "111@qq.com");
            dao.update(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource( conn);
        }
    }


    @Test
    public void testDel() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            dao.delete(2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource( conn);
        }
    }

    @Test
    public void testInsert() {
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            User user = new User("Tom", "123456", "asdfasdfsad","tom@qq.com");
            dao.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource( conn);
        }
    }
}
