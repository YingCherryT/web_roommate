package com.ajou.roommate_demo.repository;

import com.ajou.roommate_demo.model.PreferenceType;
import com.ajou.roommate_demo.model.PreferenceValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.repository
 * @CLASS_NAME: PreferenceValueRepository
 * @USER: BTS&ARMY
 * @Date 2024/11/8 18:59
 * @Version 1.0
 */
@Repository
public interface PreferenceValueRepository extends JpaRepository<PreferenceValue, Integer> {
    /**
     * 根据偏好类型查询对应的所有偏好值
     */
    List<PreferenceValue> findByPreferenceType(PreferenceType preferenceType);

    /**
     * @Author: Tyz
     * @Description: 通过id获取
     * @Date: 2024/11/8 20:28
     * @Param:
* @param typeId
     * @return: List<PreferenceValue>
     **/
    @Query("SELECT p FROM PreferenceValue p WHERE p.preferenceType.preferenceTypeId = :typeId")
    List<PreferenceValue> findByPreferenceTypeId(@Param("typeId") Integer typeId);

}
