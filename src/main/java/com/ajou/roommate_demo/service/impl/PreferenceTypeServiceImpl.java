package com.ajou.roommate_demo.service.impl;

import com.ajou.roommate_demo.model.PreferenceType;
import com.ajou.roommate_demo.repository.PreferenceTypeRepository;
import com.ajou.roommate_demo.service.PreferenceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.service.impl
 * @CLASS_NAME: PreferenceTypeServiceImpl
 * @USER: BTS&ARMY
 * @Date 2024/11/8 18:57
 * @Version 1.0
 */
@Service
public class PreferenceTypeServiceImpl implements PreferenceTypeService {

    @Autowired
    private PreferenceTypeRepository preferenceTypeRepository;

    @Override
    public PreferenceType createPreferenceType(PreferenceType preferenceType) {
        return preferenceTypeRepository.save(preferenceType);
    }

    @Override
    public List<PreferenceType> getAllPreferenceTypes() {
        return preferenceTypeRepository.findAll();
    }

    @Override
    public Optional<PreferenceType> getPreferenceTypeById(Integer id) {
        return preferenceTypeRepository.findById(id);
    }

    @Override
    public PreferenceType updatePreferenceType(Integer id, PreferenceType preferenceType) {
        if (!preferenceTypeRepository.existsById(id)) {
            return null;  // 或者抛出一个异常
        }
        preferenceType.setPreferenceTypeId(id);
        return preferenceTypeRepository.save(preferenceType);
    }

    @Override
    public void deletePreferenceType(Integer id) {
        preferenceTypeRepository.deleteById(id);
    }
}
