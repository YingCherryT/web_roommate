package com.ajou.roommate_demo.dto;

import java.util.List;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.dto
 * @CLASS_NAME: UserPreferenceDTO
 * @USER: BTS&ARMY
 * @Date 2024/11/8 20:46
 * @Version 1.0
 */
public class UserPreferenceDTO {
    private Integer preferenceTypeId;
    private String typeName;
    private List<PreferenceValueDTO> preferenceValues;

    public UserPreferenceDTO(Integer preferenceTypeId, String typeName, List<PreferenceValueDTO> preferenceValues) {
        this.preferenceTypeId = preferenceTypeId;
        this.typeName = typeName;
        this.preferenceValues = preferenceValues;
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

    public List<PreferenceValueDTO> getPreferenceValues() {
        return preferenceValues;
    }

    public void setPreferenceValues(List<PreferenceValueDTO> preferenceValues) {
        this.preferenceValues = preferenceValues;
    }
}
