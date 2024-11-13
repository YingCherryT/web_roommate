package com.ajou.roommate_demo.exception;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.exception
 * @CLASS_NAME: LoginException
 * @USER: BTS&ARMY
 * @Date 2024/11/10 16:39
 * @Version 1.0
 */
public class LoginException extends RuntimeException {
    private String errorCode;

    public LoginException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
