package com.ajou.roommate_demo.service;
import com.ajou.roommate_demo.dto.RegisterRequest;
import com.ajou.roommate_demo.dto.UserDTO;
import com.ajou.roommate_demo.model.User;
import com.ajou.roommate_demo.repository.UserRepository;
import com.ajou.roommate_demo.security.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.service
 * @CLASS_NAME: AuthServiceTest
 * @USER: BTS&ARMY
 * @Date 2024/11/6 15:44
 * @Version 1.0
 */
public class AuthServiceTest {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;  // 新增 JwtTokenProvider
    private AuthService authService;

    @BeforeEach
    void setUp() {
        // 使用 Mockito 模拟 UserRepository、PasswordEncoder 和 JwtTokenProvider
        userRepository = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        jwtTokenProvider = mock(JwtTokenProvider.class);  // 模拟 JwtTokenProvider

        // 创建 AuthService 实例，传入模拟的 JwtTokenProvider
        authService = new AuthService(userRepository, passwordEncoder, jwtTokenProvider);
    }

    @Test
    void register_ShouldReturnUserDTO_WhenRegistrationIsSuccessful() throws Exception {
        // 准备测试数据
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("testuser");
        registerRequest.setStudentId("123456");
        registerRequest.setPassword("password");
        registerRequest.setFullName("Test User");
        registerRequest.setGender("MALE");

        // 模拟 UserRepository 和 PasswordEncoder 的行为
        when(userRepository.existsByStudentId(registerRequest.getStudentId())).thenReturn(false);
        when(userRepository.existsByUsername(registerRequest.getUsername())).thenReturn(false);
        when(passwordEncoder.encode(registerRequest.getPassword())).thenReturn("encodedPassword");

        // 模拟保存用户后返回
        User savedUser = new User();
        savedUser.setStudentId("123456");
        savedUser.setUsername("testuser");
        savedUser.setFullName("Test User");
        savedUser.setPassword("encodedPassword");
        savedUser.setGender(User.Gender.MALE);
        when(userRepository.save(Mockito.any(User.class))).thenReturn(savedUser);

        // 执行注册方法
        UserDTO userDTO = authService.register(registerRequest);

        System.out.println(userDTO.getUsername());  // 输出返回的用户名
        System.out.println(userDTO.getFullName()); // 输出返回的全名

        // 断言返回的 UserDTO 是否符合预期
        assertNotNull(userDTO);
        assertEquals("testuser", userDTO.getUsername());
        assertEquals("Test User", userDTO.getFullName());
        assertEquals("123456", userDTO.getStudentId());
        assertEquals("MALE", userDTO.getGender());

        // 验证 passwordEncoder.encode() 是否被调用
        verify(passwordEncoder, times(1)).encode(registerRequest.getPassword());
    }

    @Test
    void register_ShouldThrowException_WhenStudentIdAlreadyExists() {
        // 准备测试数据
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("testuser");
        registerRequest.setStudentId("123456");
        registerRequest.setPassword("password");
        registerRequest.setFullName("Test User");
        registerRequest.setGender("MALE");

        // 模拟 UserRepository 的行为
        when(userRepository.existsByStudentId(registerRequest.getStudentId())).thenReturn(true);

        // 执行并验证异常
        assertThrows(Exception.class, () -> authService.register(registerRequest));
    }

}
