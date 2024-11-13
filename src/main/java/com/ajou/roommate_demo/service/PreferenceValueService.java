package com.ajou.roommate_demo.service;

import com.ajou.roommate_demo.model.PreferenceValue;
import java.util.List;
import java.util.Optional;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.service
 * @CLASS_NAME: PreferenceValueService
 * @USER: BTS&ARMY
 * @Date 2024/11/8 18:55
 * @Version 1.0
 */
public interface PreferenceValueService {
    PreferenceValue createPreferenceValue(PreferenceValue preferenceValue);

    List<PreferenceValue> getAllPreferenceValues();

    Optional<PreferenceValue> getPreferenceValueById(Integer id);

    PreferenceValue updatePreferenceValue(Integer id, PreferenceValue preferenceValue);

    void deletePreferenceValue(Integer id);

    // 获取特定偏好类型下的所有偏好值
    List<PreferenceValue> getPreferenceValuesByType(Integer preferenceTypeId);
}
