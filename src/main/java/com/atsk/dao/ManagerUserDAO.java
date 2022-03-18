package com.atsk.dao;

import com.atsk.pojo.ManagerUser;

/**
 * @author Ly
 * @date 2021-07-16 21:06
 */
public interface ManagerUserDAO {

    /**
     * 根据传入的账户名、密码返回一条记录
     */
    ManagerUser getManagerByNamePassword(String username, String password);
}
