package com.tms.webshop.commands;

import com.tms.webshop.model.Inject;
import com.tms.webshop.model.User;
import com.tms.webshop.model.UserType;
import com.tms.webshop.model.enums.Page;
import com.tms.webshop.model.enums.RequestParams;
import com.tms.webshop.service.UserServiceAware;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.util.regex.Pattern;

import static com.tms.webshop.model.enums.Page.*;

public class RegisterController implements BaseCommandController {
    @Inject
    private UserServiceAware userService;
    private static final LocalDate birthdayBorder = LocalDate.of(1907, 3, 4);

    @Override
    public Page execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(RequestParams.LOGIN.getValue());
        String name = request.getParameter(RequestParams.NAME.getValue());
        String surname = request.getParameter(RequestParams.SURNAME.getValue());
        String email = request.getParameter(RequestParams.EMAIL.getValue());
        String password = request.getParameter(RequestParams.PASSWORD.getValue());
        String passwordRepeat = request.getParameter(RequestParams.PASSWORD_REPEAT.getValue());
        String birthday = request.getParameter(RequestParams.BIRTHDAY.getValue());

        if (isLoginValid(login, userService) && isNameValid(name) && isNameValid(surname) && isEmailValid(email) &&
                password.equals(passwordRepeat) && isBirthdayValid(birthday)) {
            User newUser = new User(UserType.CLIENT, login, name, surname, email, LocalDate.parse(birthday));
            userService.addUser(newUser, password);
        } else {
            request.setAttribute("mistake", "Wrong parameters!!!");
            return REGISTER;
        }
        return LOGIN;
    }

    public boolean isBirthdayValid(String birthday) {
        return birthday != null && !birthday.isEmpty() && LocalDate.parse(birthday).isAfter(birthdayBorder);
    }

    public boolean isEmailValid(String email) {
        return !email.isEmpty() && Pattern.compile("^(.+)@(\\S+)").matcher(email).matches();
    }

    public boolean isNameValid(String name) {
        return !name.isEmpty() && Pattern.compile("[A-Z][a-z]*").matcher(name).matches();
    }

    public boolean isLoginValid(String login, UserServiceAware userService) {
        return !login.isEmpty() && !userService.loginInUse(login);
    }

    public void setUserService(UserServiceAware userService) {
        this.userService = userService;
    }
}
