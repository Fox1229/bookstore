package com.atsk.service.impl;

import com.atsk.dao.UserDAO;
import com.atsk.dao.impl.UserDAOImpl;
import com.atsk.pojo.User;
import com.atsk.service.UserService;

/**
 * @author Ly
 * @date 2021-07-07 17:52
 */
public class UserServiceImpl implements UserService {

    private UserDAO dao = new UserDAOImpl();

    @Override
    public boolean existsUsername(String username) {

        User user = dao.getUserByName(username);
        //用户不存在，可以插入
        if (user == null) {
            return false;
        }
        //用户名已存在，不能插入
        return true;
    }

    @Override
    public User login(User user)    {
        return dao.getUserByUsernamePassword(user.getUsername(), user.getPassword());
    }

    @Override
    public void registerUser(User user)    {
        dao.insert(user);
    }
}
