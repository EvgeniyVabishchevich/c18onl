package com.tms.webshop.servlets.user;

import com.tms.webshop.model.UserType;
import com.tms.webshop.service.UserDAO;
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
    private final LocalDate birthdayBorder = LocalDate.of(1907, 3, 4);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordRepeat = request.getParameter("passwordRepeat");
        LocalDate birthday = LocalDate.parse(request.getParameter("birthday"));

        UserDAO userDAO = new UserDAO();

        if (isLoginValid(login, userDAO) || isNameValid(name) || isNameValid(surname) || isEmailValid(email) ||
                password.equals(passwordRepeat) || isBirthdayValid(birthday)) {
            userDAO.addUser(login, password, UserType.client, name, surname, email, birthday);
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

    public boolean isLoginValid(String login, UserDAO userDAO) {
        return !login.isEmpty() && !userDAO.loginInUse(login);
    }
}
