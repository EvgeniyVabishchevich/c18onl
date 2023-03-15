package com.tms.webshop.dao.database;

import com.tms.webshop.dao.ImageDao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageDaoDb implements ImageDao {
    @Override
    public void addImage(String imageName, InputStream inputStream) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            String sql = "INSERT INTO images (name, image) VALUES (?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, imageName);
            preparedStatement.setBinaryStream(2, inputStream);
            preparedStatement.executeUpdate();

            ConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            System.out.println("Error, while trying to add new image in db.");
        } catch (Exception e) {
            System.out.println("Error, while trying to get or close connection.");
        }
    }

    @Override
    public byte[] getImageByName(String name) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            String sql = "SELECT * FROM images WHERE name = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            ConnectionPool.getInstance().closeConnection(connection);

            return resultSet.next() ? resultSet.getBytes("image") : null;
        } catch (SQLException e) {
            System.out.println("Error, while trying to load image from db.");
        } catch (Exception e) {
            System.out.println("Error, while trying to get or close connection.");
        }
        return null;
    }
}
