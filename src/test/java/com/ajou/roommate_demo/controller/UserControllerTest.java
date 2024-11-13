package com.ajou.roommate_demo.controller;

import com.ajou.roommate_demo.dto.PreferenceValueDTO;
import com.ajou.roommate_demo.dto.UpdateUserRequest;
import com.ajou.roommate_demo.dto.UserDTO;
import com.ajou.roommate_demo.dto.UserPreferenceDTO;
import com.ajou.roommate_demo.security.JwtTokenProvider;
import com.ajou.roommate_demo.service.UserPreferenceService;
import com.ajou.roommate_demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.controller
 * @CLASS_NAME: UserControllerTest
 * @USER: BTS&ARMY
 * @Date 2024/11/8 9:25
 * @Version 1.0
 */
public class UserControllerTest {
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private UserPreferenceService userPreferenceService;

    @InjectMocks
    private UserController userController;

    private String token = "dVbdmF9YHJ";
    private UserDTO mockUserDTO;
    private UpdateUserRequest updateUserRequest;
    private List<UserPreferenceDTO> mockPreferences;

    @BeforeEach
    void setUp() {
        // 初始化 mock 对象
        MockitoAnnotations.openMocks(this);

        // 设置 mock 用户信息
        mockUserDTO = new UserDTO(1, "S12345", "john_doe", "john.doe@example.com", "123456789", "John Doe", "Male", LocalDateTime.now(), LocalDateTime.now());

        // 设置 mock 用户偏好
        List<PreferenceValueDTO> preferenceValues = new ArrayList<>();  // 空列表

        mockPreferences = Arrays.asList(
                new UserPreferenceDTO(1, "Preference 1", preferenceValues),
                new UserPreferenceDTO(2, "Preference 2", preferenceValues)
        );

        // 初始化 MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testGetUserProfile() throws Exception {
        // 设置 jwt token 的行为
        when(jwtTokenProvider.getUserIdFromToken(token)).thenReturn(1);

        // 设置 UserService 的行为
        when(userService.getUserProfile(1)).thenReturn(mockUserDTO);

        // 发起 GET 请求，验证响应状态和内容
        mockMvc.perform(get("/api/users/profile")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.username").value("john_doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    void testUpdateUser() throws Exception {
        // 设置 jwt token 的行为
        when(jwtTokenProvider.getUserIdFromToken(token)).thenReturn(1);

        // 设置 UserService 的行为
        when(userService.updateUser(any(UpdateUserRequest.class), eq(1))).thenReturn(mockUserDTO);

        // 发起 PUT 请求，验证响应状态和内容
        mockMvc.perform(put("/api/users/update")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\", \"email\":\"john.doe@example.com\", \"password\":\"newpassword123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.username").value("john_doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }



}
