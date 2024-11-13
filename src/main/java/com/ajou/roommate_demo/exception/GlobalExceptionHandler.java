package com.ajou.roommate_demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常处理器，用于全局处理自定义异常和一般异常。
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 处理 RegistrationException 异常，返回 400 错误（Bad Request）
    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<ErrorResponse> handleRegistrationException(RegistrationException ex) {
        // 记录日志，确认是否进入该方法
        System.out.println("Handling RegistrationException: " + ex.getMessage());
        return buildErrorResponse(ex.getErrorCode(), ex.getMessage(), "注册失败的附加细节", HttpStatus.BAD_REQUEST);
    }


    // 处理 PreferenceNotFoundException 异常，返回 404 错误（Not Found）
    @ExceptionHandler(PreferenceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePreferenceNotFoundException(PreferenceNotFoundException ex) {
        logger.error("Preference not found: {}", ex.getMessage(), ex);
        return buildErrorResponse("PREFERENCE_NOT_FOUND", "Requested preference not found.", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // 处理 UserNotFoundException 异常，返回 404 错误（Not Found）
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        logger.error("User not found: {}", ex.getMessage(), ex);
        return buildErrorResponse("USER_NOT_FOUND", "User not found in the system.", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // 处理 PreferenceValueNotFoundException 异常，返回 404 错误（Not Found）
    @ExceptionHandler(PreferenceValueNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePreferenceValueNotFoundException(PreferenceValueNotFoundException ex) {
        logger.error("Preference value not found: {}", ex.getMessage(), ex);
        return buildErrorResponse("PREFERENCE_VALUE_NOT_FOUND", "Preference value not found.", ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // 处理所有其他异常，返回 500 错误（Internal Server Error）
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        logger.error("Unexpected error occurred: {}", ex.getMessage(), ex);
        return buildErrorResponse("GENERAL_ERROR", "An unexpected error occurred", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 辅助方法：构建标准化的错误响应。
     * @param errorCode 错误码
     * @param errorMessage 错误信息
     * @param details 错误的详细信息
     * @param status HTTP 状态码
     * @return 构建的错误响应
     */
    private ResponseEntity<ErrorResponse> buildErrorResponse(String errorCode, String errorMessage, String details, HttpStatus status) {
        // 创建错误响应对象
        ErrorResponse errorResponse = new ErrorResponse(errorCode, errorMessage, details);
        // 返回带有指定状态码的错误响应
        return ResponseEntity.status(status).body(errorResponse);
    }
}
