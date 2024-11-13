package com.ajou.roommate_demo.exception;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.exception
 * @CLASS_NAME: FileUploadException
 * @USER: BTS&ARMY
 * @Date 2024/11/12 22:07
 * @Version 1.0
 */
public class FileUploadException extends RuntimeException {

    public FileUploadException(String message) {
        super(message);
    }

    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }
}