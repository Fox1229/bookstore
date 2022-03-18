package com.atsk.service.impl;

import com.atsk.dao.ManagerUserDAO;
import com.atsk.dao.impl.ManagerUserDAOImpl;
import com.atsk.pojo.ManagerUser;
import com.atsk.service.ManagerUserService;

/**
 * @author Ly
 * @date 2021-07-16 21:20
 */
public class ManagerUserServiceImpl implements ManagerUserService {

    @Override
    public ManagerUser login(ManagerUser managerUser) {
        ManagerUserDAO dao = new ManagerUserDAOImpl();
        return dao.getManagerByNamePassword(managerUser.getManagerName(),managerUser.getPassword());
    }
}
