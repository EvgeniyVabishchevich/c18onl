package com.tms.webshop.dao;

import java.io.InputStream;

public interface ImageDao {
    String CONTEXT_NAME = "imageDao";
    void addImage(String imageName, InputStream inputStream);

    byte[] getImageByName(String name);
}
