package com.ajou.roommate_demo.dto;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.dto
 * @CLASS_NAME: LoginRequest
 * @USER: BTS&ARMY
 * @Date 2024/11/5 21:37
 * @Version 1.0
 */
public class LoginRequest {
    private String studentId;  // 学号
    private String username;   // 用户名
    private String password;   // 密码

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
}
