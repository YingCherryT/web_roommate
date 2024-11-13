package com.ajou.roommate_demo.controller;

import com.ajou.roommate_demo.dto.LoginRequest;
import com.ajou.roommate_demo.dto.RegisterRequest;
import com.ajou.roommate_demo.dto.UserDTO;
import com.ajou.roommate_demo.exception.ErrorResponse;
import com.ajou.roommate_demo.exception.RegistrationException;
import com.ajou.roommate_demo.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.controller
 * @CLASS_NAME: AuthControllerTest
 * @USER: BTS&ARMY
 * @Date 2024/11/10 15:57
 * @Version 1.0
 */
@WebMvcTest(AuthController.class)  // 只加载 AuthController
public class AuthControllerTest {

//    @Autowired
//    private MockMvc mockMvc;  // 用于模拟 HTTP 请求
//
//    @MockBean  // 用来模拟 AuthService 服务
//    private AuthService authService;
//
//    @Autowired
//    private ObjectMapper objectMapper;  // 用于将对象转为 JSON
//
//    @InjectMocks
//    private AuthController authController;  // 注入 Mock 的服务到控制器中
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);  // 初始化 Mockito 注解
//    }
//
//    @Test
//    public void testRegister() throws Exception {
//        // 构造一个 RegisterRequest
//        RegisterRequest registerRequest = new RegisterRequest();
//        registerRequest.setUsername("john_doe");
//        registerRequest.setPassword("password123");
//        registerRequest.setFullName("John Doe");
//        registerRequest.setGender("Male");
//        registerRequest.setStudentId("2024001");
//
//        // 模拟 authService.register 方法返回的结果
//        UserDTO mockUser = new UserDTO("2024001", "john_doe", "John Doe", "Male");
//        when(authService.register(any(RegisterRequest.class))).thenReturn(mockUser);
//
//        // 执行 POST 请求并验证响应
//        mockMvc.perform(post("/auth/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(registerRequest)))  // 转换成 JSON 请求体
//                .andExpect(status().isOk())  // 期望返回 200 OK 状态
//                .andExpect(jsonPath("$.username").value("john_doe"))  // 验证返回的 JSON 内容
//                .andExpect(jsonPath("$.studentId").value("2024001"));  // 验证学号是否正确
//    }
//
//    @Test
//    public void testLogin() throws Exception {
//        // 构造一个 LoginRequest
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setUsername("john_doe");
//        loginRequest.setPassword("password123");
//
//        // 模拟 authService.login 方法返回的 token
//        String mockToken = "mock-jwt-token";
//        when(authService.login(any(LoginRequest.class))).thenReturn(mockToken);
//
//        // 执行 POST 请求并验证响应
//        mockMvc.perform(post("/auth/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(loginRequest)))  // 转换成 JSON 请求体
//                .andExpect(status().isOk())  // 期望返回 200 OK 状态
//                .andExpect(jsonPath("$.token").value("mock-jwt-token"));  // 验证返回的 token
//    }
}