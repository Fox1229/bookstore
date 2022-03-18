package com.atsk.test;

import com.atsk.dao.impl.ManagerUserDAOImpl;
import com.atsk.pojo.ManagerUser;
import com.atsk.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author Ly
 * @date 2021-07-16 21:14
 */
public class Test10_ManagerUserDAOImplTest {

    @Test
    public void testLogin(){
        ManagerUserDAOImpl test = new ManagerUserDAOImpl();
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            ManagerUser manger = test.getManagerByNamePassword("ly", "991229");
            if(manger != null){
                System.out.println("登录成功");
            }else{
                System.out.println("登录失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn);
        }

    }
}
