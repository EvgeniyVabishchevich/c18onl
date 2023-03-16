package com.tms.webshop.service;

import com.tms.webshop.dao.ImageDao;
import com.tms.webshop.dao.database.ImageDaoDb;

import java.io.InputStream;

public class ImageService implements ImageServiceAware {
    private final ImageDao imageDao;
    public ImageService(ImageDao imageDao) {
        this.imageDao = imageDao;
    }
    @Override
    public void addImage(String imageName, InputStream imageStream) {
        imageDao.addImage(imageName, imageStream);
    }

    @Override
    public byte[] getImageByName(String name) {
        return imageDao.getImageByName(name);
    }
}
