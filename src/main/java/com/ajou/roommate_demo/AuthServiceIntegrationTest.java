package com.ajou.roommate_demo;

import com.ajou.roommate_demo.dto.RegisterRequest;
import com.ajou.roommate_demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * @PACKAGE_NAME: com.ajou.roommate_demo
 * @CLASS_NAME: AuthServiceIntegrationTest
 * @USER: BTS&ARMY
 * @Date 2024/11/6 21:35
 * @Version 1.0
 */
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
public class AuthServiceIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    void register_ShouldReturnUserDTO_WhenRegistrationIsSuccessful() throws Exception {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("testuser");
        registerRequest.setStudentId("123456");
        registerRequest.setPassword("password");
        registerRequest.setFullName("Test User");
        registerRequest.setGender("MALE");

        mockMvc.perform(post("/auth/register")
                        .contentType("application/json")
                        .content("{\"username\":\"testuser\", \"studentId\":\"123456\", \"password\":\"password\", \"fullName\":\"Test User\", \"gender\":\"MALE\"}"))
                .andExpect(status().isOk())
                .andDo(print())  // 打印响应内容，检查实际返回的 JSON
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.fullName").value("Test User"));
    }
}

