package com.tms.webshop.dao.database;

import com.tms.webshop.dao.UserDao;
import com.tms.webshop.model.User;
import com.tms.webshop.model.UserType;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoDb implements UserDao {
    private Connection connection;

    public UserDaoDb(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addUser(User user, String password) {
        try {
            String sql = "INSERT INTO users (login, password, user_type, name, surname, email, birthday) " +
                    "VALUES (?, ?, ?::privelege, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, password);
            preparedStatement.setObject(3, user.getUserType().name());
            preparedStatement.setString(4, user.getName());
            preparedStatement.setString(5, user.getSurname());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setDate(7, Date.valueOf(user.getBirthday()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error, while trying to add new user to db." + e.getMessage());
        }
    }

    @Override
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

    @Override
    public User getUser(String login) {
        try {
            String sql = "SELECT id, user_type, name, surname, email, birthday FROM users WHERE login=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return new User(resultSet.getInt(1), login, UserType.valueOf(resultSet.getString(2)),
                    resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                    resultSet.getDate(6).toLocalDate());
        } catch (SQLException e) {
            System.out.println("Error, while trying to get user from db");
        }
        return null;
    }

    @Override
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

    @Override
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
