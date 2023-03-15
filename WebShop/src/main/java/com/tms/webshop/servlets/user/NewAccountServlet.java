package com.tms.webshop.servlets.user;

import com.tms.webshop.dao.UserDao;
import com.tms.webshop.model.User;
import com.tms.webshop.model.UserType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = "/create-account")
public class NewAccountServlet extends HttpServlet {
    private static final LocalDate birthdayBorder = LocalDate.of(1907, 3, 4);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordRepeat = request.getParameter("passwordRepeat");
        LocalDate birthday = LocalDate.parse(request.getParameter("birthday"));

        UserDao userDao = (UserDao) request.getServletContext().getAttribute(UserDao.CONTEXT_NAME);

        if (isLoginValid(login, userDao) || isNameValid(name) || isNameValid(surname) || isEmailValid(email) ||
                password.equals(passwordRepeat) || isBirthdayValid(birthday)) {
            User newUser = new User(UserType.CLIENT, login, name, surname, email, birthday);
            userDao.addUser(newUser, password);
        } else {
            response.setStatus(400);
        }
    }

    public boolean isBirthdayValid(LocalDate birthday) {
        return birthday.isAfter(birthdayBorder);
    }

    public boolean isEmailValid(String email) {
        return !email.isEmpty() && Pattern.compile("^(.+)@(\\S+)").matcher(email).matches();
    }

    public boolean isNameValid(String name) {
        return !name.isEmpty() && Pattern.compile("[A-Z][a-z]*").matcher(name).matches();
    }

    public boolean isLoginValid(String login, UserDao userDao) {
        return !login.isEmpty() && !userDao.loginInUse(login);
    }
}
