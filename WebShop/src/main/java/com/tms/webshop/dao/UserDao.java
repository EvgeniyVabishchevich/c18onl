package com.tms.webshop.dao;

import com.tms.webshop.model.User;
import com.tms.webshop.model.UserType;

import java.time.LocalDate;

public interface UserDao {
    String CONTEXT_NAME = "userDao";
    void addUser(String login, String password, UserType userType, String name, String surname, String email,
                 LocalDate birthday);

    boolean loginInUse(String newLogin);

    User getUser(String login);

    boolean validateUser(String login, String password);

    UserType getUserType(String login, String password);
}
