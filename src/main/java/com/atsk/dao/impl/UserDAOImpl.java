package com.atsk.dao.impl;

import com.atsk.dao.BaseDAO;
import com.atsk.dao.UserDAO;
import com.atsk.pojo.User;

import java.util.List;

/**
 * @author Ly
 * @date 2021-07-07 17:03
 */
public class UserDAOImpl extends BaseDAO<User> implements UserDAO {

    @Override
    public void insert(User user) {
        String sql = "insert into t_user(username,password,phone,email) values(?,?,?,?)";
        update(sql, user.getUsername(), user.getPassword(), user.getPhone(), user.getEmail());
    }

    @Override
    public void delete(int id) {
        String sql = "delete from t_user where id = ?";
        update(sql, id);
    }

    @Override
    public void update(User user) {
        String sql = "update t_user set username = ?,password = ?,email = ? where id = ?";
        update(sql, user.getUsername(), user.getPassword(), user.getEmail(), user.getId());
    }

    @Override
    public User getUserById(int id) {
        String sql = "select id,username,password,email from t_user where id = ?";
        return getInstance(sql, id);
    }

    @Override
    public User getUserByName(String username) {
        String sql = "select id,username,password,email from t_user where username = ?";
        return getInstance(sql, username);
    }

    @Override
    public User getUserByUsernamePassword(String username, String password) {
        String sql = "select id,username,password,email from t_user where username = ? and password = ?";
        return getInstance(sql, username, password);
    }

    @Override
    public List<User> getAllUser() {
        String sql = "select id,username,password,email from t_user";
        List<User> list = getForList(sql);
        return list;
    }

    @Override
    public Long getCount() {
        return null;
    }
}
