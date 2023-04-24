package com.tms.webshop.service;

import com.tms.webshop.model.User;
import com.tms.webshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserServiceAware {
    private final UserRepository userRepository;

    @Override
    public void addUser(User user, String password) {
        userRepository.addUser(user, password);
    }

    @Override
    public User getUserByLogin(String login) {
        return userRepository.getUserByLogin(login);
    }

    @Override
    public boolean loginInUse(String login) {
        return userRepository.getUserByLogin(login) != null;
    }

    @Override
    public boolean validateUser(String login, String password) {
        return userRepository.getUserByLoginAndPwd(login, password) != null;
    }
}
