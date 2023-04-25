package com.tms.webshop.repository;

import com.tms.webshop.model.User;
import com.tms.webshop.model.UserType;

public interface UserRepository {
    void addUser(User user, String password);

    User getUserByLogin(String login);

    User getUserByLoginAndPwd(String login, String password);

    UserType getUserType(String login, String password);
}
