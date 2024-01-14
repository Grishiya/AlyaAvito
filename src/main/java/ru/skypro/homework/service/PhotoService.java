package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.models.EntityWithImage;

import java.io.IOException;

public interface PhotoService {
    String saveImage(MultipartFile multipartFile, EntityWithImage entity) throws IOException;

    void deletePhoto(String filename, String subPath) throws IOException;
}
