package com.tms.webshop.dao.database;

import com.tms.webshop.dao.ImageDao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageDaoDb implements ImageDao {
    private Connection connection;

    public ImageDaoDb(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addImage(String imageName, InputStream inputStream) {
        String sql = "INSERT INTO images (name, image) VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, imageName);
            preparedStatement.setBinaryStream(2, inputStream);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error, while trying to add new image in db.");
        }
    }

    @Override
    public byte[] getImageByName(String name) {
        String sql = "SELECT * FROM images WHERE name = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next() ? resultSet.getBytes("image") : null;
        } catch (SQLException e) {
            System.out.println("Error, while trying to load image from db.");
        }
        return null;
    }
}
