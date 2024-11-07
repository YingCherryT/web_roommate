package com.ajou.roommate_demo.exception;

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

    public ErrorResponse(String errorCode, String message, String details) {
        this.errorCode = errorCode;
        this.message = message;
        this.details = details;
    }

    // Getters and Setters
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
}
