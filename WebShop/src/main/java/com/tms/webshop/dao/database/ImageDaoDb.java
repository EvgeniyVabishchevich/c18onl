package com.tms.webshop.dao.database;

import com.tms.webshop.dao.ImageDao;
import lombok.extern.log4j.Log4j2;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
public class ImageDaoDb implements ImageDao {

    @Override
    public void addImage(String imageName, InputStream imageStream) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            String sql = "INSERT INTO images (name, image) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, imageName);
                preparedStatement.setBinaryStream(2, imageStream);
                preparedStatement.executeUpdate();
            }

            ConnectionPool.getInstance().closeConnection(connection);
        } catch (SQLException e) {
            log.error("Error, while trying to add new image in db.", e);
        } catch (Exception e) {
            log.error("Error, while trying to get or close connection.", e);
        }
    }

    @Override
    public byte[] getImageByName(String name) {
        try {
            Connection connection = ConnectionPool.getInstance().getConnection();

            String sql = "SELECT * FROM images WHERE name = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, name);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    ConnectionPool.getInstance().closeConnection(connection);

                    return resultSet.next() ? resultSet.getBytes("image") : null;
                }
            }
        } catch (SQLException e) {
            log.error("Error, while trying to load image from db.", e);
        } catch (Exception e) {
            log.error("Error, while trying to get or close connection.", e);
        }
        return null;
    }
}
