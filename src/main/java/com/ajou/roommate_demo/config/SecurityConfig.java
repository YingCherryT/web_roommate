package com.ajou.roommate_demo.config;

import com.ajou.roommate_demo.security.JwtTokenProvider;
import com.ajou.roommate_demo.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.config
 * @CLASS_NAME: SecurityConfig
 * @USER: BTS&ARMY
 * @Date 2024/11/5 21:49
 * @Version 1.0
 */
/**
 * Spring Security 配置类
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    // 配置 SecurityFilterChain，替代 WebSecurityConfigurerAdapter
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeRequests(authz -> authz
                        .requestMatchers("/css/**", "/js/**", "/img/**").permitAll()  // 允许访问静态资源
                        .requestMatchers("/for/login", "/WEB-INF/jsp/login.jsp").permitAll()  // 允许匿名访问 login 页面和 JSP
                        .requestMatchers("/auth/login", "/auth/register").permitAll() // 允许匿名访问登录和注册的处理请求
                        .requestMatchers("/for/index").authenticated()  // 首页需要认证
                        .anyRequest().authenticated()  // 其他路径都需要认证
                )
                .formLogin(form -> form
                        .loginPage("/for/login")  // 自定义登录页面
                        .loginProcessingUrl("/auth/login")  // 处理登录请求
                        .defaultSuccessUrl("/for/index", true)  // 登录成功后跳转到首页
                        .failureUrl("/for/login?error=true")  // 登录失败后跳转到登录页面
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/for/login?logout=true")
                        .permitAll()
                );

        return http.build();
    }

    // 配置密码编码器 Bean（用于对用户密码进行加密）
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // 使用 BCrypt 加密算法
    }
}
