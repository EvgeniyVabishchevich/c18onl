package com.tms.webshop.service;

import java.io.InputStream;

public interface ImageServiceAware {
    void addImage(String imageName, InputStream imageStream);

    byte[] getImageByName(String name);
}
