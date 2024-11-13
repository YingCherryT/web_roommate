package com.ajou.roommate_demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.security
 * @CLASS_NAME: JwtAuthenticationFilter
 * @USER: BTS&ARMY
 * @Date 2024/11/7 20:39
 * @Version 1.0
 */
@Component
@Order(1)  // 设置过滤器顺序，数字越小越先执行
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    // 注入 JwtTokenProvider 以便进行验证操作
    @Autowired
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 从请求头获取 JWT 令牌
        String token = getTokenFromRequest(request);

        if (token != null && jwtTokenProvider.validateToken(token)) {
            // 如果 token 有效，获取用户名
            String username = jwtTokenProvider.getUsernameFromToken(token);

            // 创建一个 Authentication 对象，表示已认证的用户
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());

            // 将认证信息放到 Spring Security 的上下文中
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 继续过滤链
        filterChain.doFilter(request, response);
    }

    // 从请求头中提取 token
    private String getTokenFromRequest(HttpServletRequest request) {
        // 获取 Authorization 头部信息，通常是 "Bearer <token>"
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);  // 去掉 "Bearer " 前缀
        }
        return null;
    }
}
