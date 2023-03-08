package com.tms.webshop.dao;

import com.tms.webshop.model.User;
import com.tms.webshop.model.UserType;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserDao {
    Connection connection;

    public UserDao() {
        connection = DBConnectionContainer.INSTANCE.getConnection();
    }

    public void addUser(String login, String password, UserType userType, String name, String surname, String email,
                        LocalDate birthday) {
        try {
            String sql = "INSERT INTO users (login, password, user_type, name, surname, email, birthday) " +
                    "VALUES (?, ?, ?::privelege, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setObject(3, userType.name());
            preparedStatement.setString(4, name);
            preparedStatement.setString(5, surname);
            preparedStatement.setString(6, email);
            preparedStatement.setDate(7, Date.valueOf(birthday));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error, while trying to add new user to db." + e.getMessage());
        }
    }

    public boolean loginInUse(String newLogin) {
        try {
            String sql = "SELECT * FROM users WHERE login=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, newLogin);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Error, while trying to check login match");
        }
        return true;
    }

    public User getUser(String login) {
        try {
            String sql = "SELECT user_type, name, surname, email, birthday FROM users WHERE login=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return new User(login, UserType.valueOf(resultSet.getString(1)),
                    resultSet.getString(2), resultSet.getString(3),
                    resultSet.getString(4), resultSet.getDate(5).toLocalDate());
        } catch (SQLException e) {
            System.out.println("Error, while trying to get user from db");
        }
        return null;
    }

    public boolean validateUser(String login, String password) {
        try {
            String sql = "SELECT * FROM users WHERE login=? AND password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("SQL exception, while trying to validate user.");
        }
        return false;
    }

    public UserType getUserType(String login, String password) {
        try {
            String sql = "SELECT user_type FROM users WHERE login=? AND password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            return UserType.valueOf(resultSet.getString("user_type"));
        } catch (SQLException e) {
            System.out.println("SQL exception, while trying to find user type.");
        }
        return null;
    }
}
