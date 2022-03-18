package com.atsk.service;

import com.atsk.pojo.ManagerUser;

/**
 * @author Ly
 * @date 2021-07-16 21:19
 */
public interface ManagerUserService {

    /**
     * 登录
     *
     * @return null 登录失败；！null 登录成功
     */
    ManagerUser login(ManagerUser managerUser);

}
