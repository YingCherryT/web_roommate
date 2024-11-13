package com.ajou.roommate_demo.controller;

import com.ajou.roommate_demo.dto.UpdateUserRequest;
import com.ajou.roommate_demo.dto.UserDTO;
import com.ajou.roommate_demo.exception.FileUploadException;
import com.ajou.roommate_demo.exception.UserNotFoundException;
import com.ajou.roommate_demo.security.JwtTokenProvider;
import com.ajou.roommate_demo.service.FileStorageService;
import com.ajou.roommate_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.controller
 * @CLASS_NAME: UserController
 * @USER: BTS&ARMY
 * @Date 2024/11/5 9:45
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private FileStorageService fileStorageService;

    // 获取用户个人信息
    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getUserProfile(@RequestHeader("Authorization") String token) {
        // 从 token 中提取 userId
        Integer userId = jwtTokenProvider.getUserIdFromToken(token);

        // 调用 Service 层获取用户资料
        UserDTO userDTO = userService.getUserProfile(userId);

        // 如果用户不存在，抛出自定义异常
        if (userDTO == null) {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }

        return ResponseEntity.ok(userDTO);
    }

    // 更新用户个人信息
    @PutMapping("/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UpdateUserRequest updateUserRequest,
                                              @RequestHeader("Authorization") String token) {
        // 从 token 中提取 userId
        Integer userId = jwtTokenProvider.getUserIdFromToken(token);

        // 调用 Service 层更新用户资料
        UserDTO updatedUser = userService.updateUser(updateUserRequest, userId);

        // 如果更新失败，抛出自定义异常
        if (updatedUser == null) {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }

        return ResponseEntity.ok(updatedUser);
    }

    // 上传用户头像
    @PostMapping("/uploadProfilePicture")
    public ResponseEntity<String> uploadProfilePicture(@RequestParam("file") MultipartFile file,
                                                       @RequestHeader("Authorization") String token) {
        try {
            // 从 token 中提取 userId
            Integer userId = jwtTokenProvider.getUserIdFromToken(token);

            // 调用 FileStorageService 保存图片并返回文件路径
            String fileName = fileStorageService.storeFile(file);

            // 更新用户的头像路径
            UserDTO updatedUser = userService.updateUserProfilePicture(userId, fileName);

            // 返回成功响应
            return ResponseEntity.ok("Profile picture uploaded successfully!");

        } catch (IOException e) {
            // 如果文件上传失败，抛出 FileUploadException
            throw new FileUploadException("File upload failed: " + e.getMessage(), e);
        } catch (Exception e) {
            // 捕获其他可能的异常并抛出 FileUploadException
            throw new FileUploadException("An error occurred while uploading the file", e);
        }
    }
}
