package com.tms.webshop.repository;

import java.io.InputStream;

public interface ImageRepository {
    void addImage(String imageName, InputStream inputStream);

    byte[] getImageByName(String name);
}
