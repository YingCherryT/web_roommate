package com.ajou.roommate_demo.repository;

import com.ajou.roommate_demo.model.PreferenceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.repository
 * @CLASS_NAME: PreferenceTypeRepository
 * @USER: BTS&ARMY
 * @Date 2024/11/8 18:58
 * @Version 1.0
 */
@Repository
public interface PreferenceTypeRepository extends JpaRepository<PreferenceType, Integer> {

}
