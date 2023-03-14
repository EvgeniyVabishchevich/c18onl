package com.tms.webshop.dao;

import com.tms.webshop.model.User;
import com.tms.webshop.model.UserType;

public interface UserDao {
    String CONTEXT_NAME = "userDao";
    void addUser(User user, String password);

    boolean loginInUse(String newLogin);

    User getUser(String login);

    boolean validateUser(String login, String password);

    UserType getUserType(String login, String password);
}
