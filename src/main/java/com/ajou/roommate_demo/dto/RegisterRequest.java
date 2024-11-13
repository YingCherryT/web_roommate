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

    // 默认构造函数（可以用于 Spring 等框架自动映射）
    public RegisterRequest() {}

    // 带参构造函数
    public RegisterRequest(String studentId, String username, String password, String fullName, String gender) {
        this.studentId = studentId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.gender = gender;
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
