package com.ajou.roommate_demo.model;

import jakarta.persistence.*;
import java.util.Objects;

/**
 * 偏好值实体类，表示用户选择的具体偏好值。
 */
@Entity
@Table(name = "preference_values")
public class PreferenceValue {

    // 主键ID，自动生成且不可修改
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "preference_value_id", nullable = false, updatable = false)
    private Integer preferenceValueId;

    // 偏好类型，ManyToOne关联，延迟加载（LAZY）
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preference_type_id", nullable = false)
    private PreferenceType preferenceType;

    // 偏好具体值
    @Column(name = "preference_value", nullable = false)
    private String preferenceValue;

    // 无参构造函数
    public PreferenceValue() {
    }

    // 有参构造函数
    public PreferenceValue(Integer preferenceValueId, PreferenceType preferenceType, String preferenceValue) {
        this.preferenceValueId = preferenceValueId;
        this.preferenceType = preferenceType;
        this.preferenceValue = preferenceValue;
    }


    // Getter 和 Setter 方法
    public Integer getPreferenceValueId() {
        return preferenceValueId;
    }

    public void setPreferenceValueId(Integer preferenceValueId) {
        this.preferenceValueId = preferenceValueId;
    }

    public PreferenceType getPreferenceType() {
        return preferenceType;
    }

    public void setPreferenceType(PreferenceType preferenceType) {
        this.preferenceType = preferenceType;
    }

    public String getPreferenceValue() {
        return preferenceValue;
    }

    public void setPreferenceValue(String preferenceValue) {
        this.preferenceValue = preferenceValue;
    }

    // 重写toString方法
    @Override
    public String toString() {
        return "PreferenceValue{" +
                "preferenceValueId=" + preferenceValueId +
                ", preferenceType=" + preferenceType +
                ", preferenceValue='" + preferenceValue + '\'' +
                '}';
    }

    // 重写equals方法
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreferenceValue that = (PreferenceValue) o;
        return preferenceValueId.equals(that.preferenceValueId);
    }

    // 重写hashCode方法
    @Override
    public int hashCode() {
        return Objects.hash(preferenceValueId);
    }
}
