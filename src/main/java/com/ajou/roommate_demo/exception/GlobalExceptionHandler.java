package com.ajou.roommate_demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.exception
 * @CLASS_NAME: GlobalExceptionHandler
 * @USER: BTS&ARMY
 * @Date 2024/11/6 22:05
 * @Version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    // 处理自定义异常
    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<ErrorResponse> handleRegistrationException(RegistrationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode(), ex.getMessage(), "Additional details about registration failure");
        return ResponseEntity.badRequest().body(errorResponse);
    }

    // 处理所有其他异常
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse("GENERAL_ERROR", "An unexpected error occurred", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
