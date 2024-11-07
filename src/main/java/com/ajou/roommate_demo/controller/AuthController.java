package com.ajou.roommate_demo.controller;

import com.ajou.roommate_demo.dto.LoginRequest;
import com.ajou.roommate_demo.dto.RegisterRequest;
import com.ajou.roommate_demo.dto.UserDTO;
import com.ajou.roommate_demo.exception.ErrorResponse;
import com.ajou.roommate_demo.exception.RegistrationException;
import com.ajou.roommate_demo.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.controller
 * @CLASS_NAME: AuthController
 * @USER: BTS&ARMY
 * @Date 2024/11/5 16:41
 * @Version 1.0
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // 用户注册
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            UserDTO userDTO = authService.register(registerRequest);
            return ResponseEntity.ok(userDTO);
        } catch (RegistrationException e) {
            // 自定义异常处理
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getMessage(), "Details about the registration issue.");
            return ResponseEntity.badRequest().body(errorResponse);
        } catch (Exception e) {
            // 处理其他异常
            ErrorResponse errorResponse = new ErrorResponse("GENERAL_ERROR", "An unexpected error occurred", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
    // 用户登录
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = authService.login(loginRequest);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid credentials");  // 返回错误响应
        }
    }

}
