package com.ajou.roommate_demo.repository;

import com.ajou.roommate_demo.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.repository
 * @CLASS_NAME: UserRepositoryTest
 * @USER: BTS&ARMY
 * @Date 2024/11/8 9:25
 * @Version 1.0
 */
@ActiveProfiles("test")  // 指定使用 "test" 配置
@DataJpaTest  // 启用 JPA 测试，使用内存数据库进行测试
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;  // 自动注入 UserRepository

    private User testUser;

    @BeforeEach
    void setUp() {
        // 初始化测试数据：创建一个 User 对象
        testUser = new User();
        testUser.setUsername("testuser");
        testUser.setStudentId("123456");
        testUser.setFullName("Test User");
        testUser.setPassword("encodedPassword");
        testUser.setGender(User.Gender.MALE);
    }

    @Test
    void testSaveUser() {
        // 保存用户
        User savedUser = userRepository.save(testUser);

        // 验证保存成功
        assertNotNull(savedUser);
        assertEquals(testUser.getUsername(), savedUser.getUsername());
        assertEquals(testUser.getFullName(), savedUser.getFullName());
        assertEquals(testUser.getStudentId(), savedUser.getStudentId());
    }

    @Test
    void testFindByUsername() {
        // 保存测试数据
        userRepository.save(testUser);

        // 根据用户名查找用户
        User foundUser = userRepository.findByUsername(testUser.getUsername()).orElseThrow();

        // 验证通过用户名查找是否成功
        assertNotNull(foundUser);
        assertEquals(testUser.getUsername(), foundUser.getUsername());
        assertEquals(testUser.getFullName(), foundUser.getFullName());
    }

    @Test
    void testExistsByUsername() {
        // 保存用户
        userRepository.save(testUser);

        // 检查用户名是否存在
        assertTrue(userRepository.existsByUsername(testUser.getUsername()));
        assertFalse(userRepository.existsByUsername("nonexistentuser"));
    }

    @Test
    void testFindByStudentId() {
        // 保存用户
        userRepository.save(testUser);

        // 根据学号查找用户
        User foundUser = userRepository.findByStudentId(testUser.getStudentId()).orElseThrow();

        // 验证通过学号查找是否成功
        assertNotNull(foundUser);
        assertEquals(testUser.getStudentId(), foundUser.getStudentId());
        assertEquals(testUser.getUsername(), foundUser.getUsername());
    }

    @Test
    void testFindByStudentIdOrUsername() {
        // 保存用户
        userRepository.save(testUser);

        // 使用学号或用户名查找
        User foundByStudentId = userRepository.findByStudentIdOrUsername(testUser.getStudentId(), null).orElseThrow();
        User foundByUsername = userRepository.findByStudentIdOrUsername(null, testUser.getUsername()).orElseThrow();

        // 验证通过学号或用户名查找是否成功
        assertNotNull(foundByStudentId);
        assertNotNull(foundByUsername);
        assertEquals(testUser.getUsername(), foundByUsername.getUsername());
        assertEquals(testUser.getStudentId(), foundByStudentId.getStudentId());
    }

    @Test
    void testExistsByStudentId() {
        // 保存用户
        userRepository.save(testUser);

        // 检查学号是否存在
        assertTrue(userRepository.existsByStudentId(testUser.getStudentId()));
        assertFalse(userRepository.existsByStudentId("nonexistentstudent"));
    }

    @Test
    void testDeleteUser() {
        // 保存用户
        User savedUser = userRepository.save(testUser);

        // 删除用户
        userRepository.delete(savedUser);

        // 验证用户是否被删除
        assertFalse(userRepository.findById(savedUser.getUserId()).isPresent());
    }
}
