package com.ajou.roommate_demo.exception;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.exception
 * @CLASS_NAME: RegistrationException
 * @USER: BTS&ARMY
 * @Date 2024/11/6 22:03
 * @Version 1.0
 */
public class RegistrationException extends RuntimeException {
    private String errorCode;

    public RegistrationException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
