package com.ajou.roommate_demo.dto;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.dto
 * @CLASS_NAME: PreferenceValueDTO
 * @USER: BTS&ARMY
 * @Date 2024/11/10 9:56
 * @Version 1.0
 */
public class PreferenceValueDTO {
    private Integer preferenceValueId;
    private String preferenceValue;

    // 构造函数
    public PreferenceValueDTO(Integer preferenceValueId, String preferenceValue) {
        this.preferenceValueId = preferenceValueId;
        this.preferenceValue = preferenceValue;
    }

    // Getter and Setter
    public Integer getPreferenceValueId() {
        return preferenceValueId;
    }

    public void setPreferenceValueId(Integer preferenceValueId) {
        this.preferenceValueId = preferenceValueId;
    }

    public String getPreferenceValue() {
        return preferenceValue;
    }

    public void setPreferenceValue(String preferenceValue) {
        this.preferenceValue = preferenceValue;
    }
}
