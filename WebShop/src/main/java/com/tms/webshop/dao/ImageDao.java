package com.tms.webshop.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageDao {
    private Connection connection;

    public ImageDao() {
        connection = DBConnectionContainer.INSTANCE.getConnection();
    }

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
