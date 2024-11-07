package com.ajou.roommate_demo.dto;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.dto
 * @CLASS_NAME: RegisterRequest
 * @USER: BTS&ARMY
 * @Date 2024/11/5 21:39
 * @Version 1.0
 */
public class RegisterRequest {
    private String studentId;  // 学号
    private String username;   // 用户名
    private String password;   // 密码
    private String fullName;   // 姓名
    private String gender;     // 性别

    // Getters and Setters
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
