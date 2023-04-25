package com.tms.webshop.service;

import com.tms.webshop.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class ImageService implements ImageServiceAware {
    private final ImageRepository imageRepository;

    @Override
    public void addImage(String imageName, InputStream imageStream) {
        imageRepository.addImage(imageName, imageStream);
    }

    @Override
    public byte[] getImageByName(String name) {
        return imageRepository.getImageByName(name);
    }
}
