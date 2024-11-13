package com.ajou.roommate_demo.dto;

import com.ajou.roommate_demo.model.User;

import java.time.LocalDateTime;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.dto
 * @CLASS_NAME: UserDTO
 * @USER: BTS&ARMY
 * @Date 2024/11/5 19:45
 * @Version 1.0
 */

public class UserDTO {
    private Integer userId;
    private String studentId;
    private String username;
    private String email;
    private String phone;
    private String fullName;
    private String gender;
    private String profilePicturePath;  // 存储图片文件的路径
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 通过 User 实体创建 UserDTO
    public UserDTO(User user) {
        this.userId = user.getUserId();
        this.studentId = user.getStudentId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.fullName = user.getFullName();
        this.gender = user.getGender().name(); // Gender 枚举转为 String
        this.profilePicturePath = user.getProfilePicturePath();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdatedAt();
    }
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

    public UserDTO(Integer userId, String studentId, String username, String email, String phone, String fullName, String gender, String profilePicturePath, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
        this.studentId = studentId;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.fullName = fullName;
        this.gender = gender;
        this.profilePicturePath = profilePicturePath;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UserDTO(Integer userId, String studentId, String username, String email, String phone, String fullName, String gender, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.userId = userId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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
        // 可选的：可以进行简单的验证确保gender是 "MALE" 或 "FEMALE"
        if (!gender.equalsIgnoreCase("MALE") && !gender.equalsIgnoreCase("FEMALE")) {
            throw new IllegalArgumentException("Gender must be 'MALE' or 'FEMALE'");
        }
        this.gender = gender;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
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
