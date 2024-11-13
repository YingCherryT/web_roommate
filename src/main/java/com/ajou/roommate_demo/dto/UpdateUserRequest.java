package com.ajou.roommate_demo.dto;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.dto
 * @CLASS_NAME: UpdateUserRequest
 * @USER: BTS&ARMY
 * @Date 2024/11/7 21:18
 * @Version 1.0
 */

public class UpdateUserRequest {

    private String username;
    private String email;
    private String phone;
    private String fullName;
    private String gender; // 可以是 "MALE" 或 "FEMALE"

    // 构造函数
    public UpdateUserRequest(String username, String email, String phone, String fullName, String gender) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.fullName = fullName;
        this.gender = gender;
    }
    public UpdateUserRequest(){};

    // Getters 和 Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

