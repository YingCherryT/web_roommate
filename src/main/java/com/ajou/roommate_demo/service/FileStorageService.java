package com.ajou.roommate_demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
/**
 *
 * @PACKAGE_NAME: com.ajou.roommate_demo.service
 * @CLASS_NAME: FileStorageService
 * @USER: BTS&ARMY
 * @Date 2024/11/12 21:53
 * @Version 1.0
 */
@Service
public class FileStorageService {

    // 上传目录
    @Value("${file.upload-dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file) throws IOException {
        // 创建目录
        Path path = Paths.get(uploadDir);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        // 使用UUID避免文件名冲突
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path targetLocation = path.resolve(fileName);

        // 保存文件到目标位置
        Files.copy(file.getInputStream(), targetLocation);

        // 返回文件路径
        return fileName;
    }
}