package com.tms.webshop.utilsDB;

import com.tms.webshop.UserType;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {
    Connection connection;

    public UserDAO() {
        connection = DBConnectionContainer.INSTANCE.getConnection();
    }

    public boolean validateUser(String login, String password) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(
                    "SELECT * FROM users WHERE login='%s' AND password='%s'", login, password));
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("SQL exception, while trying to validate user.");
        }
        return false;
    }

    public UserType getUserType(String login, String password) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format(
                    "SELECT user_type FROM users WHERE login='%s' AND password='%s'", login, password));
            resultSet.next();
            return UserType.valueOf(resultSet.getString("user_type"));
        } catch (SQLException e) {
            System.out.println("SQL exception, while trying to find user type.");
        }
        return null;
    }
}
