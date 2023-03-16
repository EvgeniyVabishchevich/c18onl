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
    @Override
    public void addUser(User user, String password) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

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

            ConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            System.out.println("Error, while trying to add new user to db." + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error, while trying to get or close connection.");
        }
    }

    @Override
    public User getUserByLogin(String login) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            String sql = "SELECT id, user_type, name, surname, email, birthday FROM users WHERE login=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, login);

            ResultSet resultSet = preparedStatement.executeQuery();

            ConnectionPool.getInstance().closeConnection(connection);

            if (resultSet.next()) {
                return new User(resultSet.getInt(1), login, UserType.valueOf(resultSet.getString(2)),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getDate(6).toLocalDate());
            }
        } catch (SQLException e) {
            System.out.println("Error, while trying to get user from db");
        } catch (Exception e) {
            System.out.println("Error, while trying to get or close connection.");
        }
        return null;
    }

    @Override
    public User getUserByLoginAndPwd(String login, String password) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            String sql = "SELECT id, user_type, name, surname, email, birthday FROM users WHERE login=? AND password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            ConnectionPool.getInstance().closeConnection(connection);

            if (resultSet.next()) {
                return new User(resultSet.getInt(1), login, UserType.valueOf(resultSet.getString(2)),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getDate(6).toLocalDate());
            }
        } catch (SQLException e) {
            System.out.println("SQL exception, while trying to validate user.");
        } catch (Exception e) {
            System.out.println("Error, while trying to get or close connection.");
        }
        return null;
    }

    @Override
    public UserType getUserType(String login, String password) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            String sql = "SELECT user_type FROM users WHERE login=? AND password=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            ConnectionPool.getInstance().closeConnection(connection);

            return UserType.valueOf(resultSet.getString("user_type"));
        } catch (SQLException e) {
            System.out.println("SQL exception, while trying to find user type.");
        } catch (Exception e) {
            System.out.println("Error, while trying to get or close connection.");
        }
        return null;
    }
}
