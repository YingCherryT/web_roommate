package com.ajou.roommate_demo.service;

import com.ajou.roommate_demo.dto.UserPreferenceDTO;
import com.ajou.roommate_demo.model.UserPreference;
import java.util.List;
import java.util.Optional;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.service
 * @CLASS_NAME: UserPreferenceService
 * @USER: BTS&ARMY
 * @Date 2024/11/8 18:56
 * @Version 1.0
 */
public interface UserPreferenceService {

    // 获取用户偏好
    List<UserPreferenceDTO> getUserPreferences(Integer userId);

    // 更新或保存用户的偏好
    void saveOrUpdateUserPreferences(Integer userId, List<Integer> preferenceValueIds);
}
