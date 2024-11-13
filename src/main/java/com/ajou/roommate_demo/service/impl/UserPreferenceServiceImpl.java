package com.ajou.roommate_demo.service.impl;

import com.ajou.roommate_demo.dto.PreferenceValueDTO;
import com.ajou.roommate_demo.dto.UserPreferenceDTO;
import com.ajou.roommate_demo.exception.PreferenceValueNotFoundException;
import com.ajou.roommate_demo.exception.UserNotFoundException;
import com.ajou.roommate_demo.model.PreferenceType;
import com.ajou.roommate_demo.model.PreferenceValue;
import com.ajou.roommate_demo.model.User;
import com.ajou.roommate_demo.model.UserPreference;
import com.ajou.roommate_demo.repository.PreferenceTypeRepository;
import com.ajou.roommate_demo.repository.PreferenceValueRepository;
import com.ajou.roommate_demo.repository.UserPreferenceRepository;
import com.ajou.roommate_demo.repository.UserRepository;
import com.ajou.roommate_demo.service.UserPreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.service.impl
 * @CLASS_NAME: UserPreferenceServiceImpl
 * @USER: BTS&ARMY
 * @Date 2024/11/8 19:02
 * @Version 1.0
 */
@Service
public class UserPreferenceServiceImpl implements UserPreferenceService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPreferenceRepository userPreferenceRepository;

    @Autowired
    private PreferenceValueRepository preferenceValueRepository;

    @Autowired
    private PreferenceTypeRepository preferenceTypeRepository;

    @Override
    public List<UserPreferenceDTO> getUserPreferences(Integer userId) {
        // 获取用户的偏好记录
        List<UserPreference> userPreferences = userPreferenceRepository.findByUser_UserId(userId);

        if (userPreferences.isEmpty()) {
            // 用户没有任何偏好记录，返回所有偏好类型及其对应的偏好值
            return getAllPreferenceTypesAndValues();  // 确保调用此方法
        } else {
            // 用户已经有偏好记录，返回用户的偏好数据
            return userPreferences.stream().map(userPreference -> {
                // 获取 PreferenceValue 对象
                PreferenceValue preferenceValue = userPreference.getPreferenceValue();
                if (preferenceValue == null) {
                    // 如果 PreferenceValue 为空，返回一个默认的偏好数据或抛出异常
                    throw new IllegalStateException("偏好值不能为空");
                }

                // 获取对应的 PreferenceType
                PreferenceType preferenceType = preferenceValue.getPreferenceType();
                if (preferenceType == null) {
                    // 如果 PreferenceType 为空，返回一个默认的偏好类型或抛出异常
                    throw new IllegalStateException("偏好类型不能为空");
                }

                // 获取该偏好类型下的所有偏好值
                List<PreferenceValue> preferenceValues = preferenceValueRepository.findByPreferenceType(preferenceType);

                // 将偏好值转换为DTO
                List<PreferenceValueDTO> preferenceValueDTOs = preferenceValues.stream()
                        .map(pv -> new PreferenceValueDTO(pv.getPreferenceValueId(), pv.getPreferenceValue()))
                        .collect(Collectors.toList());

                // 返回用户的偏好
                return new UserPreferenceDTO(preferenceType.getPreferenceTypeId(), preferenceType.getTypeName(), preferenceValueDTOs);
            }).collect(Collectors.toList());
        }
    }



    private List<UserPreferenceDTO> getAllPreferenceTypesAndValues() {
        // 获取所有偏好类型和偏好值
        List<PreferenceType> preferenceTypes = preferenceTypeRepository.findAll();

        if (preferenceTypes.isEmpty()) {
            // 如果没有偏好类型，返回空列表
            return Collections.emptyList();
        }

        return preferenceTypes.stream().map(preferenceType -> {
            // 获取该偏好类型下的所有偏好值
            List<PreferenceValue> preferenceValues = preferenceValueRepository.findByPreferenceType(preferenceType);

            // 如果没有偏好值，返回空的 DTO 列表
            if (preferenceValues.isEmpty()) {
                return new UserPreferenceDTO(preferenceType.getPreferenceTypeId(), preferenceType.getTypeName(), Collections.emptyList());
            }

            // 将偏好值转换为DTO
            List<PreferenceValueDTO> preferenceValueDTOs = preferenceValues.stream()
                    .map(pv -> new PreferenceValueDTO(pv.getPreferenceValueId(), pv.getPreferenceValue()))
                    .collect(Collectors.toList());

            // 返回偏好类型和偏好值
            return new UserPreferenceDTO(preferenceType.getPreferenceTypeId(), preferenceType.getTypeName(), preferenceValueDTOs);
        }).collect(Collectors.toList());
    }


    @Override
    public void saveOrUpdateUserPreferences(Integer userId, List<Integer> preferenceValueIds) {
        // 查找用户对象
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("用户不存在，ID: " + userId));

        // 如果用户没有任何偏好记录，可以直接添加新的偏好值
        if (userPreferenceRepository.existsByUser_UserId(userId)) {
            // 如果用户有偏好记录，先删除这些记录
            userPreferenceRepository.deleteByUser_UserId(userId);
        }

        // 为用户保存新的偏好
        for (Integer preferenceValueId : preferenceValueIds) {
            // 获取对应的偏好值
            PreferenceValue preferenceValue = preferenceValueRepository.findById(preferenceValueId)
                    .orElseThrow(() -> new PreferenceValueNotFoundException("偏好值不存在，ID: " + preferenceValueId));

            // 创建新的 UserPreference 实体
            UserPreference userPreference = new UserPreference();
            userPreference.setUser(user);
            userPreference.setPreferenceValue(preferenceValue);

            // 保存用户偏好
            userPreferenceRepository.save(userPreference);
        }
    }

}
