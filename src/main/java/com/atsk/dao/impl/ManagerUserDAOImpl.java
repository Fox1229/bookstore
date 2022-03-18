package com.atsk.dao.impl;

import com.atsk.dao.BaseDAO;
import com.atsk.dao.ManagerUserDAO;
import com.atsk.pojo.ManagerUser;

/**
 * @author Ly
 * @date 2021-07-16 21:07
 */
public class ManagerUserDAOImpl extends BaseDAO<ManagerUser> implements ManagerUserDAO {

    @Override
    public ManagerUser getManagerByNamePassword(String username, String password) {
        String sql = "select id,manager_name managerName,manager_password password from t_manager_user where manager_name = ? " +
                "and manager_password = ?";
        return getInstance(sql, username, password);
    }
}
