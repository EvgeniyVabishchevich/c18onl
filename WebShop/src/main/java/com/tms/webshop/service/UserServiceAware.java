package com.tms.webshop.service;

import com.tms.webshop.model.User;

public interface UserServiceAware {
    String CONTEXT_NAME = "userService";
    void addUser(User user, String password);
    User getUserByLogin(String login);
    boolean loginInUse(String login);
    boolean validateUser(String login, String password);
}
