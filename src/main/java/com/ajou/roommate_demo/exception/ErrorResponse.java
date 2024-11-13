package com.ajou.roommate_demo.exception;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.exception
 * @CLASS_NAME: ErrorResponse
 * @USER: BTS&ARMY
 * @Date 2024/11/6 22:02
 * @Version 1.0
 */
public class ErrorResponse {

    private String errorCode;
    private String message;
    private String details;
    private String timestamp;  // 改为 String 类型以便返回格式化的日期字符串

    // Constructor
    public ErrorResponse(String errorCode, String message, String details) {
        this.errorCode = errorCode;
        this.message = message;
        this.details = details;
        this.timestamp = getCurrentTimestamp();
    }

    // 获取当前时间的格式化字符串
    private String getCurrentTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    // Getter and Setter methods
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    // 重写 toString 方法，方便调试和打印
    @Override
    public String toString() {
        return "ErrorResponse{" +
                "errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                ", details='" + details + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
