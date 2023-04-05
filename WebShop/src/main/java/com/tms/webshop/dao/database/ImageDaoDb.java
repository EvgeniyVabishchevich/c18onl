package com.tms.webshop.dao.database;

import com.tms.webshop.dao.ImageDao;
import com.tms.webshop.dao.utils.ConnectionWrapper;
import lombok.extern.log4j.Log4j2;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.tms.webshop.dao.BaseRepository.CONNECTION_POOL;

@Log4j2
public class ImageDaoDb implements ImageDao {

    @Override
    public void addImage(String imageName, InputStream imageStream) {
        try (ConnectionWrapper connectionWrapper = CONNECTION_POOL.getConnection()) {
            String sql = "INSERT INTO images (name, image) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = connectionWrapper.getConnection().prepareStatement(sql)) {
                preparedStatement.setString(1, imageName);
                preparedStatement.setBinaryStream(2, imageStream);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            log.error("Error, while trying to add new image in db.", e);
        } catch (Exception e) {
            log.error("Error, while trying to get or close connection.", e);
        }
    }

    @Override
    public byte[] getImageByName(String name) {
        try (ConnectionWrapper connectionWrapper = CONNECTION_POOL.getConnection()) {
            String sql = "SELECT * FROM images WHERE name = ?";

            try (PreparedStatement preparedStatement = connectionWrapper.getConnection().prepareStatement(sql)) {
                preparedStatement.setString(1, name);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
