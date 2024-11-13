package com.ajou.roommate_demo.service.impl;

import com.ajou.roommate_demo.model.PreferenceValue;
import com.ajou.roommate_demo.repository.PreferenceValueRepository;
import com.ajou.roommate_demo.service.PreferenceValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.service.impl
 * @CLASS_NAME: PreferenceValueServiceImpl
 * @USER: BTS&ARMY
 * @Date 2024/11/8 19:02
 * @Version 1.0
 */
@Service
public class PreferenceValueServiceImpl implements PreferenceValueService {

    @Autowired
    private PreferenceValueRepository preferenceValueRepository;

    @Override
    public PreferenceValue createPreferenceValue(PreferenceValue preferenceValue) {
        return preferenceValueRepository.save(preferenceValue);
    }

    @Override
    public List<PreferenceValue> getAllPreferenceValues() {
        return preferenceValueRepository.findAll();
    }

    @Override
    public Optional<PreferenceValue> getPreferenceValueById(Integer id) {
        return preferenceValueRepository.findById(id);
    }

    @Override
    public PreferenceValue updatePreferenceValue(Integer id, PreferenceValue preferenceValue) {
        if (!preferenceValueRepository.existsById(id)) {
            return null;  // 或者抛出一个异常
        }
        preferenceValue.setPreferenceValueId(id);
        return preferenceValueRepository.save(preferenceValue);
    }

    @Override
    public void deletePreferenceValue(Integer id) {
        preferenceValueRepository.deleteById(id);
    }

    @Override
    public List<PreferenceValue> getPreferenceValuesByType(Integer preferenceTypeId) {
        return preferenceValueRepository.findByPreferenceTypeId(preferenceTypeId);
    }
}