package com.tms.webshop.utilsDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageDAO {
    private Connection connection;

    public ImageDAO(){
        connection = DBConnectionContainer.INSTANCE.getConnection();
    }

    public byte[] getImageByName(String name){
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
