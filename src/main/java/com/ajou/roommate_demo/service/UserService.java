package com.ajou.roommate_demo.service;

import com.ajou.roommate_demo.dto.UpdateUserRequest;
import com.ajou.roommate_demo.dto.UserDTO;
import com.ajou.roommate_demo.exception.UserNotFoundException;  // 引入自定义异常
import com.ajou.roommate_demo.model.User;
import com.ajou.roommate_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.security
 * @CLASS_NAME: UserService
 * @USER: BTS&ARMY
 * @Date 2024/11/5 9:47
 * @Version 1.0
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 更新用户个人信息
    public UserDTO updateUser(UpdateUserRequest updateUserRequest, Integer userId) {
        // 1. 根据 userId 查找用户
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));

        // 2. 更新用户信息
        if (updateUserRequest.getEmail() != null) {
            user.setEmail(updateUserRequest.getEmail());
        }
        if (updateUserRequest.getPhone() != null) {
            user.setPhone(updateUserRequest.getPhone());
        }
        if (updateUserRequest.getFullName() != null) {
            user.setFullName(updateUserRequest.getFullName());
        }
        if (updateUserRequest.getGender() != null) {
            user.setGender(User.Gender.valueOf(updateUserRequest.getGender().toUpperCase()));
        }

        // 3. 保存并返回更新后的用户
        user = userRepository.save(user);

        // 返回更新后的 UserDTO
        return new UserDTO(
                user.getStudentId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getFullName(),
                user.getGender().toString(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    // 通过用户 userId 获取用户资料
    public UserDTO getUserProfile(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));

        return new UserDTO(
                user.getStudentId(),
                user.getUsername(),
                user.getEmail(),
                user.getPhone(),
                user.getFullName(),
                user.getGender().toString(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    // 更新用户头像路径
    public UserDTO updateUserProfilePicture(Integer userId, String profilePicturePath) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));

        // 更新头像路径
        user.setProfilePicturePath(profilePicturePath);

        // 保存更新后的用户
        userRepository.save(user);

        return new UserDTO(user);
    }
}
