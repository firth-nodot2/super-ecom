package org.rhydo.superecom.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || !originalFilename.contains(".")) {
            throw new IOException("Invalid file: filename is null or missing extension");
        }

        String randomId = UUID.randomUUID().toString();
        String fileName = randomId.concat(originalFilename.substring(originalFilename.lastIndexOf('.')));
        String filePath = path + File.separator + fileName;

        File folder = new File(path);
        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            if (!created) {
                throw new IOException("Unable to create directory" + path);
            }
        }

        Files.copy(file.getInputStream(), Paths.get(filePath));
        return fileName;
    }
}
