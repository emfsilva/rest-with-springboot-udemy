package io.github.emfsilva.services;

import com.ctc.wstx.util.StringUtil;
import io.github.emfsilva.config.FileStorageConfig;
import io.github.emfsilva.exception.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FIleStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FIleStorageService(FileStorageConfig fileStorageConfig) {
        this.fileStorageLocation = Paths.get(fileStorageConfig
                .getUploadDir())
                .toAbsolutePath()
                .normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw  new FileStorageException("Could not create the directory there the uploaded files will be storage", e);
        }
        }
    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! filename contains invalid path sequence" + fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        }catch (Exception e) {
            throw  new FileStorageException("Could not store file "+ fileName + ". Please try again", e);
        }
}
}
