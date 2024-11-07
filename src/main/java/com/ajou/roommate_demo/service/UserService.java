package com.ajou.roommate_demo.service;

import com.ajou.roommate_demo.model.User;
import com.ajou.roommate_demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 用户注册
    public User registerUser(User user) {
        // 检查用户名是否已存在
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }

        // 检查学号是否已存在
        if (userRepository.findByStudentId(user.getStudentId()) != null) {
            throw new RuntimeException("Student ID already exists");
        }

        // 如果传入的 fullName 或 studentId 为空，可以设置默认值或抛出异常
        if (user.getFullName() == null || user.getFullName().isEmpty()) {
            throw new RuntimeException("Full name is required");
        }

        if (user.getStudentId() == null || user.getStudentId().isEmpty()) {
            throw new RuntimeException("Student ID is required");
        }

        // 对密码进行加密
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // 保存用户
        return userRepository.save(user);
    }

    // 用户登录
    public User loginUser(String identifier, String password) {
        // 根据用户名或者学号查找用户
        User user = userRepository.findByUsername(identifier); // 首先根据用户名查找
        if (user == null) {
            user = userRepository.findByStudentId(identifier); // 如果没有找到，再根据学号查找
        }

        // 如果用户不存在
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // 验证密码是否正确
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // 如果用户名和密码都正确，返回用户信息
        return user;
    }

}
