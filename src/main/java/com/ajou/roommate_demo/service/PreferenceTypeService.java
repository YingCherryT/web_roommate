package com.ajou.roommate_demo.service;

import com.ajou.roommate_demo.model.PreferenceType;
import java.util.List;
import java.util.Optional;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.service
 * @CLASS_NAME: PreferenceTypeService
 * @USER: BTS&ARMY
 * @Date 2024/11/8 18:54
 * @Version 1.0
 */
public interface PreferenceTypeService {
    PreferenceType createPreferenceType(PreferenceType preferenceType);

    /**
     * @Author: Tyz 
     * @Description: 获取所有偏好类型
     * @Date: 2024/11/8 19:16
     * @Param:  
     * @return: List<PreferenceType>
     **/
    List<PreferenceType> getAllPreferenceTypes();

    Optional<PreferenceType> getPreferenceTypeById(Integer id);

    PreferenceType updatePreferenceType(Integer id, PreferenceType preferenceType);

    void deletePreferenceType(Integer id);
}
