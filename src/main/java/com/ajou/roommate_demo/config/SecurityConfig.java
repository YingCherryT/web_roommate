package com.ajou.roommate_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.config
 * @CLASS_NAME: SecurityConfig
 * @USER: BTS&ARMY
 * @Date 2024/11/5 21:49
 * @Version 1.0
 */
@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // 使用 BCrypt 编码器
    }
}
