package com.ajou.roommate_demo.controller;

import com.ajou.roommate_demo.dto.UserPreferenceDTO;
import com.ajou.roommate_demo.exception.UserNotFoundException;
import com.ajou.roommate_demo.security.JwtTokenProvider;
import com.ajou.roommate_demo.service.UserPreferenceService;
import com.ajou.roommate_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.controller
 * @CLASS_NAME: PreferenceController
 * @USER: BTS&ARMY
 * @Date 2024/11/8 20:40
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/user/preferences")
public class PreferenceController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;  // 通过 JWT 令牌提供者获取当前用户信息

    @Autowired
    private UserPreferenceService userPreferenceService;

    // 获取用户的个人偏好
    @GetMapping("/preferences")
    public ResponseEntity<List<UserPreferenceDTO>> getUserPreferences(@RequestHeader("Authorization") String token) {
        Integer userId = getUserIdFromToken(token);
        List<UserPreferenceDTO> preferences = userPreferenceService.getUserPreferences(userId);

        if (preferences == null || preferences.isEmpty()) {
            throw new UserNotFoundException("No preferences found for user with ID " + userId);
        }

        return ResponseEntity.ok(preferences);
    }

    // 更新用户的偏好
    @PutMapping("/preferences")
    public ResponseEntity<String> updateUserPreferences(
            @RequestBody List<Integer> preferenceValueIds, @RequestHeader("Authorization") String token) {
        Integer userId = getUserIdFromToken(token);

        try {
            userPreferenceService.saveOrUpdateUserPreferences(userId, preferenceValueIds);
            return ResponseEntity.ok("用户偏好更新成功");
        } catch (Exception e) {
            throw new RuntimeException("Failed to update user preferences", e);
        }
    }

    // 新增偏好记录时，可以提供一个接口来让用户选择偏好
    @PostMapping("/preferences")
    public ResponseEntity<String> createUserPreferences(
            @RequestBody List<Integer> preferenceValueIds, @RequestHeader("Authorization") String token) {
        Integer userId = getUserIdFromToken(token);

        try {
            userPreferenceService.saveOrUpdateUserPreferences(userId, preferenceValueIds);
            return ResponseEntity.ok("用户偏好创建成功");
        } catch (Exception e) {
            throw new RuntimeException("Failed to create user preferences", e);
        }
    }

    // 提取重复的逻辑，获取 token 中的 userId
    private Integer getUserIdFromToken(String token) {
        try {
            return jwtTokenProvider.getUserIdFromToken(token);
        } catch (Exception e) {
            throw new UserNotFoundException("Failed to extract userId from token.");
        }
    }
}
