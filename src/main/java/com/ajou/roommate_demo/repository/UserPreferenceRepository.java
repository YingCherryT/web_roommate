package com.ajou.roommate_demo.repository;

import com.ajou.roommate_demo.model.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.repository
 * @CLASS_NAME: UserPreferenceRepository
 * @USER: BTS&ARMY
 * @Date 2024/11/8 16:45
 * @Version 1.0
 */
public interface UserPreferenceRepository extends JpaRepository<UserPreference, Integer> {

    // 使用 user.userId 而不是 userId
    List<UserPreference> findByUser_UserId(Integer userId);

    // 根据 userId 删除 UserPreference
    void deleteByUser_UserId(Integer userId);  // 注意这里是 user_UserId，而不是直接使用 userId
    // 根据用户ID检查用户是否有偏好记录
    boolean existsByUser_UserId(Integer userId);
}
