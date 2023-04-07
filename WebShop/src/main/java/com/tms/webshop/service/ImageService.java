package com.tms.webshop.service;

import com.tms.webshop.dao.ImageDao;
import com.tms.webshop.model.Inject;

import java.io.InputStream;

public class ImageService implements ImageServiceAware {
    @Inject
    private ImageDao imageDao;

    @Override
    public void addImage(String imageName, InputStream imageStream) {
        imageDao.addImage(imageName, imageStream);
    }

    @Override
    public byte[] getImageByName(String name) {
        return imageDao.getImageByName(name);
    }

    public void setImageDao(ImageDao imageDao) {
        this.imageDao = imageDao;
    }
}
