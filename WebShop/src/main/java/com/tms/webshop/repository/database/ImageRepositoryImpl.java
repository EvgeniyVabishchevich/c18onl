package com.tms.webshop.repository.database;

import com.tms.webshop.repository.ImageRepository;
import com.tms.webshop.repository.utils.ConnectionWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.tms.webshop.repository.BaseRepository.CONNECTION_POOL;

@Slf4j
@Repository
public class ImageRepositoryImpl implements ImageRepository {

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
