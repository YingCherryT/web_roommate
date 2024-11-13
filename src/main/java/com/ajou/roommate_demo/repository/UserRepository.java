package com.ajou.roommate_demo.repository;

import com.ajou.roommate_demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.repository
 * @CLASS_NAME: UserRepository
 * @USER: BTS&ARMY
 * @Date 2024/11/5
 * @Version 1.0
 */

public interface UserRepository extends JpaRepository<User,Integer> {
    // 根据用户名查找用户
    Optional<User> findByUsername(String username);

    // 根据学号查找用户
    Optional<User> findByStudentId(String studentId);

    // 根据用户名或学号查找用户
    Optional<User> findByStudentIdOrUsername(String studentId, String username);

    // 检查是否已存在相同学号
    boolean existsByStudentId(String studentId);

    // 检查是否已存在相同用户名
    boolean existsByUsername(String username);

    // 根据 userId 查找用户
    Optional<User> findById(Integer userId);
}
