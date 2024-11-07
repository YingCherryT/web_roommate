package com.ajou.roommate_demo.dto;

import java.time.LocalDateTime;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.dto
 * @CLASS_NAME: UserDTO
 * @USER: BTS&ARMY
 * @Date 2024/11/5 19:45
 * @Version 1.0
 */

public class UserDTO {

    private String studentId;
    private String username;
    private String email;
    private String phone;
    private String fullName;
    private String gender;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 构造函数
    public UserDTO(String studentId, String username, String email, String phone, String fullName, String gender, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.studentId = studentId;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.fullName = fullName;
        this.gender = gender;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserDTO(String studentId, String username, String fullName, String gender) {
        this.studentId = studentId;
        this.username = username;
        this.fullName = fullName;
        this.gender = gender;

        System.out.println("UserDTO username: " + this.username);  // 打印 UserDTO 中的 username
        System.out.println("UserDTO fullName: " + this.fullName);  // 打印 UserDTO 中的 fullName
    }

    // 默认构造函数（可以用于 Spring 等框架自动映射）
    public UserDTO() {}

    // Getters 和 Setters
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
