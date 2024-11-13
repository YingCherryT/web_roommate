package com.ajou.roommate_demo.exception;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.exception
 * @CLASS_NAME: PreferenceNotFoundException
 * @USER: BTS&ARMY
 * @Date 2024/11/10 11:00
 * @Version 1.0
 */
public class PreferenceNotFoundException extends RuntimeException {

    public PreferenceNotFoundException(String message) {
        super(message);
    }

    public PreferenceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}