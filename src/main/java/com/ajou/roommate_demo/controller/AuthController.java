package com.ajou.roommate_demo.controller;

import com.ajou.roommate_demo.dto.LoginRequest;
import com.ajou.roommate_demo.dto.RegisterRequest;
import com.ajou.roommate_demo.dto.UserDTO;
import com.ajou.roommate_demo.exception.ErrorResponse;
import com.ajou.roommate_demo.exception.LoginException;
import com.ajou.roommate_demo.exception.RegistrationException;
import com.ajou.roommate_demo.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            // 假设注册请求没有用户名会抛出 RegistrationException
            if (registerRequest.getUsername() == null || registerRequest.getUsername().isEmpty()) {
                throw new RegistrationException("USERNAME_MISSING", "Username is required.");
            }

            UserDTO userDTO = authService.register(registerRequest);
            return ResponseEntity.ok(userDTO);
        } catch (RegistrationException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getMessage(), "Please check the registration details.");
            return ResponseEntity.badRequest().body(errorResponse);  // 返回 400 错误
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("GENERAL_ERROR", "An unexpected error occurred", e.getMessage());
            return ResponseEntity.status(500).body(errorResponse);  // 返回 500 错误
        }
    }


    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        try {
            // 登录认证
            String token = authService.login(loginRequest);

            // 登录成功，保存 token 或者用户信息到 Session
            request.getSession().setAttribute("token", token);  // 将 token 存入 session 中

            // 重定向到首页
            return "redirect:/index";  // 返回视图名，可以是 index.jsp
        } catch (LoginException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getErrorCode(), e.getMessage(), "Please check your credentials.");
            request.setAttribute("error", errorResponse);  // 将错误信息存入 request 中
            return "login";  // 返回登录页面
        } catch (Exception e) {
            ErrorResponse errorResponse = new ErrorResponse("GENERAL_ERROR", "An unexpected error occurred", e.getMessage());
            request.setAttribute("error", errorResponse);  // 将错误信息存入 request 中
            return "login";  // 返回登录页面
        }
    }
}