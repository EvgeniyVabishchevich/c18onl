package com.tms.webshop.dao;

import java.io.InputStream;

public interface ImageDao {
    void addImage(String imageName, InputStream inputStream);

    byte[] getImageByName(String name);
}
