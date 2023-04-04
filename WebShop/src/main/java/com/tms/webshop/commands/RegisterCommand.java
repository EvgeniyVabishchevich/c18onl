package com.tms.webshop.commands;

import com.tms.webshop.model.User;
import com.tms.webshop.model.UserType;
import com.tms.webshop.model.enums.Pages;
import com.tms.webshop.model.enums.RequestParams;
import com.tms.webshop.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.util.regex.Pattern;

import static com.tms.webshop.service.UserServiceAware.CONTEXT_NAME;

public class RegisterCommand implements BaseCommand {
    private static final LocalDate birthdayBorder = LocalDate.of(1907, 3, 4);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(RequestParams.LOGIN.getValue());
        String name = request.getParameter(RequestParams.NAME.getValue());
        String surname = request.getParameter(RequestParams.SURNAME.getValue());
        String email = request.getParameter(RequestParams.EMAIL.getValue());
        String password = request.getParameter(RequestParams.PASSWORD.getValue());
        String passwordRepeat = request.getParameter(RequestParams.PASSWORD_REPEAT.getValue());
        String birthday = request.getParameter(RequestParams.BIRTHDAY.getValue());

        UserService userService = (UserService) request.getServletContext().getAttribute(CONTEXT_NAME);

        if (isLoginValid(login, userService) && isNameValid(name) && isNameValid(surname) && isEmailValid(email) &&
                password.equals(passwordRepeat) && isBirthdayValid(birthday)) {
            User newUser = new User(UserType.CLIENT, login, name, surname, email, LocalDate.parse(birthday));
            userService.addUser(newUser, password);
        } else {
            request.setAttribute("mistake", "Wrong parameters!!!");
            return Pages.REGISTER.getValue();
        }
        return Pages.LOGIN.getValue();
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

    public boolean isLoginValid(String login, UserService userService) {
        return !login.isEmpty() && !userService.loginInUse(login);
    }
}
