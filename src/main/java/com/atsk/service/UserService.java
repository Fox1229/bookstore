package com.atsk.service;

import com.atsk.pojo.User;

/**
 * @author Ly
 * @date 2021-07-07 17:48
 */
public interface UserService {

    /**
     * 检查用户名是否可用
     * true 用户名存在，不可用
     * false 用户名不存在，可用
     */
    boolean existsUsername(String username);

    /**
     * 登录
     *
     * @return null 登录失败；！null 登录成功
     */
    User login(User user);

    /**
     * 注册用户
     */
    void registerUser(User user);
}
