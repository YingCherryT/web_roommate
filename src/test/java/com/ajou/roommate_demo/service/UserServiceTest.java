package com.ajou.roommate_demo.service;

import com.ajou.roommate_demo.dto.UpdateUserRequest;
import com.ajou.roommate_demo.dto.UserDTO;
import com.ajou.roommate_demo.model.User;
import com.ajou.roommate_demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.service
 * @CLASS_NAME: UserServiceTest
 * @USER: BTS&ARMY
 * @Date 2024/11/8 9:26
 * @Version 1.0
 */
public class UserServiceTest {
    @Mock
    private UserRepository userRepository; // 模拟 UserRepository 依赖

    @InjectMocks
    private UserService userService; // 将模拟的 repository 注入到 UserService 中

    private User user; // 创建一个测试用的 User 实体对象

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // 初始化 Mockito 模拟对象
        // 初始化一个用户对象，模拟从数据库中获取的用户数据
        user = new User();
        user.setUserId(1);
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setPhone("123456789");
        user.setFullName("Test User");
        user.setGender(User.Gender.MALE);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
    }

    @Test
    public void testUpdateUser() {
        // 模拟 repository 的行为，查找用户时返回已创建的 user 对象
        when(userRepository.findById(1)).thenReturn(Optional.of(user)); // 模拟找到了用户
        when(userRepository.save(any(User.class))).thenReturn(user); // 模拟保存更新后的用户并返回该用户

        // 创建 UpdateUserRequest 请求对象，模拟用户输入的新数据
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setEmail("newemail@example.com");
        updateUserRequest.setPhone("987654321");
        updateUserRequest.setFullName("Updated User");
        updateUserRequest.setGender("FEMALE");

        // 调用 UserService 的 updateUser 方法进行用户信息更新
        UserDTO updatedUserDTO = userService.updateUser(updateUserRequest, 1);

        // 验证 UserRepository 是否按照预期被调用
        verify(userRepository, times(1)).findById(1); // 验证 findById 方法被调用了一次
        verify(userRepository, times(1)).save(any(User.class)); // 验证 save 方法被调用了一次

        // 使用断言验证更新后的结果是否符合预期
        assertEquals("newemail@example.com", updatedUserDTO.getEmail()); // 验证邮箱是否更新
        assertEquals("987654321", updatedUserDTO.getPhone()); // 验证电话是否更新
        assertEquals("Updated User", updatedUserDTO.getFullName()); // 验证姓名是否更新
        assertEquals("FEMALE", updatedUserDTO.getGender()); // 验证性别是否更新为 FEMALE
    }

    @Test
    public void testGetUserProfile() {
        // 模拟 repository 的行为，查找用户时返回已创建的 user 对象
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        // 调用 UserService 的 getUserProfile 方法获取用户资料
        UserDTO userDTO = userService.getUserProfile(1);

        // 验证 UserRepository 是否按照预期被调用
        verify(userRepository, times(1)).findById(1); // 验证 findById 方法被调用了一次

        // 使用断言验证返回的用户信息是否符合预期
        assertEquals("test@example.com", userDTO.getEmail()); // 验证邮箱是否正确
        assertEquals("123456789", userDTO.getPhone()); // 验证电话是否正确
        assertEquals("Test User", userDTO.getFullName()); // 验证姓名是否正确
        assertEquals("MALE", userDTO.getGender()); // 验证性别是否为 "MALE"
    }

    @Test
    public void testUpdateUser_UserNotFound() {
        // 模拟 repository 行为，当查找用户时返回空（表示未找到用户）
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        // 创建 UpdateUserRequest 请求对象
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        updateUserRequest.setEmail("newemail@example.com");

        // 调用 updateUser 方法，并验证在未找到用户时会抛出异常
        assertThrows(RuntimeException.class, () -> userService.updateUser(updateUserRequest, 1)); // 预期抛出 RuntimeException
    }

    @Test
    public void testGetUserProfile_UserNotFound() {
        // 模拟 repository 行为，当查找用户时返回空（表示未找到用户）
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        // 调用 getUserProfile 方法，并验证在未找到用户时会抛出异常
        assertThrows(RuntimeException.class, () -> userService.getUserProfile(1)); // 预期抛出 RuntimeException
    }
}
