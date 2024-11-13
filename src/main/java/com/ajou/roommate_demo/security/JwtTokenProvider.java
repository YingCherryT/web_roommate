package com.ajou.roommate_demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * @PACKAGE_NAME: com.ajou.roommate_demo.security
 * @CLASS_NAME: JwtTokenProvider
 * @USER: BTS&ARMY
 * @Date 2024/11/5 21:01
 * @Version 1.0
 */
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")  // 从 application.properties 中读取密钥
    private String secretKey;

    @Value("${jwt.expiration}")  // 从配置文件中读取过期时间（单位：毫秒）
    private long expirationTime;

    // 生成 JWT Token，除了用户名外，还可以存储更多的信息（例如 userId）
    public String generateToken(Integer userId, String username) {
        // 使用 secretKey 创建一个 SecretKey 对象
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        // 创建 JWT Token
        JwtBuilder builder = Jwts.builder()
                .setSubject(username)  // 设置 JWT 的主体为用户名
                .setIssuedAt(new Date())  // 设置 token 创建时间
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))  // 设置过期时间
                .claim("userId", userId)  // 在 token 中携带 userId
                .claim("sub", username)  // 使用 claim 设置自定义字段（例如：subject）
                .signWith(key, SignatureAlgorithm.HS512);  // 使用密钥进行签名

        // 返回生成的 Token
        return builder.compact();
    }

    // 从 JWT 获取用户名
    public String getUsernameFromToken(String token) {
        // 使用 secretKey 创建 SecretKey 对象
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        // 解析 JWT Token 并获取 Claims
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();  // 返回用户标识（如用户名）
    }

    // 从 JWT 获取 userId
    public Integer getUserIdFromToken(String token) {
        // 使用 secretKey 创建 SecretKey 对象
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        // 解析 JWT Token 并获取 Claims
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("userId", Integer.class);  // 获取 userId
    }

    // 验证 JWT 是否有效
    public boolean validateToken(String token) {
        try {
            // 使用 secretKey 创建 SecretKey 对象
            SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

            // 解析并验证 token
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            // 检查 token 是否过期
            return !claimsJws.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            // 如果发生任何异常，表示 token 无效
            // 打印错误日志（可以自定义日志处理）
            System.err.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }
}
