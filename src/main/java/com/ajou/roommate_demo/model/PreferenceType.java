package com.ajou.roommate_demo.model;

import jakarta.persistence.*;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.model
 * @CLASS_NAME: PreferenceType
 * @USER: BTS&ARMY
 * @Date 2024/11/8 16:31
 * @Version 1.0
 */

@Entity
@Table(name = "preference_types")
public class PreferenceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preference_type_id", nullable = false, updatable = false)  // 主键列名更具描述性
    private Integer preferenceTypeId;  // 主键，自动生成且不可修改，作为该实体的唯一标识符

    @Column(name = "type_name", nullable = false, unique = true)
    private String typeName;  // 偏好类型名称

    public PreferenceType(Integer preferenceTypeId, String typeName) {
        this.preferenceTypeId = preferenceTypeId;
        this.typeName = typeName;
    }

    public PreferenceType() {
    }

    public Integer getPreferenceTypeId() {
        return preferenceTypeId;
    }

    public void setPreferenceTypeId(Integer preferenceTypeId) {
        this.preferenceTypeId = preferenceTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
