package ru.skypro.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.models.EntityWithImage;
import ru.skypro.homework.service.PhotoService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.UUID;

@Service
public class PhotoServiceImpl implements PhotoService {
    private final String photoDir;

    public PhotoServiceImpl(@Value("${image-directory}") String photoDir) {
        this.photoDir = "./" + photoDir;
    }

    @Override
    public String saveImage(MultipartFile multipartFile, EntityWithImage entity) throws IOException {
        String subPath = entity.getClass().getSimpleName();
        Path path;
        if (entity.getImage() != null && entity.getImage().isBlank()) {
            Files.deleteIfExists(Path.of(photoDir, subPath, stripUrl(entity.getImage())));
        }
        path = writeImageToFile(multipartFile, subPath);
        return path.getFileName().toString();
    }

    private Path writeImageToFile(MultipartFile multipartFile, String subPath) throws IOException {
        String file = UUID.randomUUID() + getExtensions(multipartFile.getOriginalFilename());
        Path filePath = Path.of(photoDir, subPath, file);
        Files.createDirectories(filePath.getParent());
        try (InputStream inputStream = multipartFile.getInputStream();
             OutputStream outputStream = Files.newOutputStream(filePath,
                     StandardOpenOption.CREATE_NEW);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream, 1024);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream, 1024)) {
            bufferedInputStream.transferTo(bufferedOutputStream);
        }
        return filePath;
    }

    @Override
    public void deletePhoto(String filename, String subPath) throws IOException {
        Path filePath = Path.of(photoDir, subPath, filename);
        Files.deleteIfExists(filePath);
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private String stripUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }
}

