package com.ajou.roommate_demo.dto;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.dto
 * @CLASS_NAME: UserDTOTest
 * @USER: BTS&ARMY
 * @Date 2024/11/8 9:25
 * @Version 1.0
 */
public class UserDTOTest {

    @Test
    void testUserDTOConstructorAndGetters() {
        // 准备测试数据
        Integer userId = 1;
        String studentId = "123456";
        String username = "john_doe";
        String email = "john@example.com";
        String phone = "123-456-7890";
        String fullName = "John Doe";
        String gender = "MALE";
        LocalDateTime createdAt = LocalDateTime.now().minusDays(1);
        LocalDateTime updatedAt = LocalDateTime.now();

        // 使用构造函数创建 UserDTO 实例
        UserDTO userDTO = new UserDTO(userId, studentId, username, email, phone, fullName, gender, createdAt, updatedAt);

        // 使用断言验证 UserDTO 的属性值
        assertEquals(userId, userDTO.getUserId());
        assertEquals(studentId, userDTO.getStudentId());
        assertEquals(username, userDTO.getUsername());
        assertEquals(email, userDTO.getEmail());
        assertEquals(phone, userDTO.getPhone());
        assertEquals(fullName, userDTO.getFullName());
        assertEquals(gender, userDTO.getGender());
        assertEquals(createdAt, userDTO.getCreatedAt());
        assertEquals(updatedAt, userDTO.getUpdatedAt());
    }

    @Test
    void testSettersAndGetters() {
        // 创建一个空的 UserDTO 实例
        UserDTO userDTO = new UserDTO();

        // 使用 setter 设置属性
        userDTO.setUserId(2);
        userDTO.setStudentId("654321");
        userDTO.setUsername("jane_doe");
        userDTO.setEmail("jane@example.com");
        userDTO.setPhone("987-654-3210");
        userDTO.setFullName("Jane Doe");
        userDTO.setGender("FEMALE");
        userDTO.setCreatedAt(LocalDateTime.now().minusDays(2));
        userDTO.setUpdatedAt(LocalDateTime.now().minusHours(1));

        // 使用断言验证 getter 是否返回正确的值
        assertEquals(2, userDTO.getUserId());
        assertEquals("654321", userDTO.getStudentId());
        assertEquals("jane_doe", userDTO.getUsername());
        assertEquals("jane@example.com", userDTO.getEmail());
        assertEquals("987-654-3210", userDTO.getPhone());
        assertEquals("Jane Doe", userDTO.getFullName());
        assertEquals("FEMALE", userDTO.getGender());
        assertNotNull(userDTO.getCreatedAt());
        assertNotNull(userDTO.getUpdatedAt());
    }

    @Test
    void testDefaultConstructor() {
        // 使用默认构造函数创建 UserDTO 实例
        UserDTO userDTO = new UserDTO();

        // 验证默认值（这些值默认应该是 null 或空值）
        assertNull(userDTO.getUserId());
        assertNull(userDTO.getStudentId());
        assertNull(userDTO.getUsername());
        assertNull(userDTO.getEmail());
        assertNull(userDTO.getPhone());
        assertNull(userDTO.getFullName());
        assertNull(userDTO.getGender());
        assertNull(userDTO.getCreatedAt());
        assertNull(userDTO.getUpdatedAt());
    }

    @Test
    void testGenderEnum() {
        // 测试 Gender 枚举值
        UserDTO userDTO = new UserDTO();
        userDTO.setGender("MALE");
        assertEquals("MALE", userDTO.getGender());

        userDTO.setGender("FEMALE");
        assertEquals("FEMALE", userDTO.getGender());
    }
}
