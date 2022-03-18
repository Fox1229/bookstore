package com.atsk.dao;

import com.atsk.pojo.User;

import java.util.List;

/**
 * @author Ly
 * @date 2021-07-07 17:02
 */
public interface UserDAO {

    /**
     * 插入User
     */
    void insert(User user)   ;

    /**
     * 根据指定的id,删除User
     */
    void delete(int id)   ;

    /**
     * 修改User
     */
    void update(User user)   ;

    /**
     * 根据传入的id,查询一条记录
     *
     * @return
     */
    User getUserById(int id);

    /**
     * 根据传入的用户名，查询一条记录
     */
    User getUserByName(String username);

    /**
     * 根据传入的用户名、密码返回一条记录
     */
    User getUserByUsernamePassword(String username, String password);

    /**
     * 返回所有的User
     *
     * @return
     */
    List<User> getAllUser();

    /**
     * 返回User中指定目录的个数
     *
     * @return
     */
    Long getCount();
}
