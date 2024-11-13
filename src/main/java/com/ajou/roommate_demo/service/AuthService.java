package com.ajou.roommate_demo.service;

import com.ajou.roommate_demo.dto.LoginRequest;
import com.ajou.roommate_demo.dto.RegisterRequest;
import com.ajou.roommate_demo.dto.UserDTO;
import com.ajou.roommate_demo.exception.LoginException;
import com.ajou.roommate_demo.exception.RegistrationException;
import com.ajou.roommate_demo.model.User;
import com.ajou.roommate_demo.repository.UserRepository;
import com.ajou.roommate_demo.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.service
 * @CLASS_NAME: AuthService
 * @USER: BTS&ARMY
 * @Date 2024/11/5 19:51
 * @Version 1.0
 */
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 用户注册
    public UserDTO register(RegisterRequest registerRequest) {
        // 1. 检查学号或用户名是否已存在
        if (userRepository.existsByStudentId(registerRequest.getStudentId())) {
            throw new RegistrationException("Student ID already exists", "USER_ALREADY_EXISTS");
        }
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RegistrationException("Username already exists", "USER_ALREADY_EXISTS");
        }

        // 2. 创建用户实体并加密密码
        User user = new User();
        user.setStudentId(registerRequest.getStudentId());
        user.setUsername(registerRequest.getUsername());
        user.setFullName(registerRequest.getFullName());

        // 3. 将字符串类型的性别转换为枚举类型 Gender
        User.Gender gender = User.Gender.valueOf(registerRequest.getGender().toUpperCase());
        user.setGender(gender);

        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        // 4. 保存用户
        user = userRepository.save(user);

        // 5. 转换为 DTO 并返回
        return new UserDTO(user.getStudentId(), user.getUsername(), user.getFullName(), user.getGender().toString());
    }

    // 用户登录
    public String login(LoginRequest loginRequest) {
        // 1. 查找用户
        User user = userRepository.findByStudentIdOrUsername(loginRequest.getStudentId(), loginRequest.getUsername())
                .orElseThrow(() -> new LoginException("Invalid credentials", "INVALID_CREDENTIALS"));

        // 2. 验证密码
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new LoginException("Invalid credentials", "INVALID_CREDENTIALS");
        }

        // 3. 生成 JWT Token
        return jwtTokenProvider.generateToken(user.getUserId(), user.getUsername());
    }
}