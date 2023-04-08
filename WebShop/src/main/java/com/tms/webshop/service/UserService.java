package com.tms.webshop.service;

import com.tms.webshop.dao.UserDao;
import com.tms.webshop.model.Inject;
import com.tms.webshop.model.User;

public class UserService implements UserServiceAware {
    @Inject
    private UserDao userDao;

    @Override
    public void addUser(User user, String password) {
        userDao.addUser(user, password);
    }

    @Override
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    public boolean loginInUse(String login) {
        return userDao.getUserByLogin(login) != null;
    }

    @Override
    public boolean validateUser(String login, String password) {
        return userDao.getUserByLoginAndPwd(login, password) != null;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
