package com.atsk.test;

import com.atsk.pojo.ManagerUser;
import com.atsk.service.ManagerUserService;
import com.atsk.service.impl.ManagerUserServiceImpl;
import com.atsk.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author Ly
 * @date 2021-07-16 21:22
 */
public class Test11_ManagerUserServiceImplTest {

    @Test
    public void testLogin(){
        ManagerUserService test = new ManagerUserServiceImpl();
        Connection conn = null;
        try {
            conn = JdbcUtil.getConnection();
            ManagerUser manger = test.login(new ManagerUser(null,"ly","991229"));
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
