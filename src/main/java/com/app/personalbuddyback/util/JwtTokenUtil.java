package com.app.oauth.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
@Slf4j
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secretKey;

    // 토큰 생성 메소드 (이메일과 이름을 클레임으로 포함)
    public String generateToken(Map<String, Object> claims) {
        String email = (String) claims.get("email");  // 이메일 정보 추출
        String name = (String) claims.get("name");    // 이름 정보 추출

        // 24시간 (24 * 60 * 60 * 1000ms)
        long expirationTimeInMillis = 1000 * 60 * 60 * 24;  // 24시간
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTimeInMillis);

        return Jwts.builder()
                .claim("email", email)   // 이메일 클레임 추가
                .claim("name", name)     // 이름 클레임 추가
                .setExpiration(expirationDate)  // 만료시간 설정
                .signWith(SignatureAlgorithm.HS256, secretKey)  // SHA-256 알고리즘 사용
                .setHeaderParam("typ", "JWT")  // JWT 타입 설정
                .compact();  // JWT 토큰 생성
    }

    // 토큰 파싱 메소드
    public Claims parseToken(String token) {
        try {
            return Jwts.parser() // 새로운 파서 빌더 사용
                    .setSigningKey(secretKey)  // 서명 검증을 위한 비밀키 설정
                    .build()
                    .parseClaimsJws(token)  // JWT 파싱하여 Claims 객체 반환
                    .getBody();  // Claims 반환
        } catch (ExpiredJwtException e) {
            // 만료된 토큰 처리
            log.error("Expired token", e);
            throw new RuntimeException("Expired token");
        } catch (Exception e) {
            log.error("Invalid token", e);
            throw new RuntimeException("Invalid token");
        }
    }

    // JWT 토큰에서 이메일 추출
    public String getEmailFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.get("email", String.class);  // 이메일 클레임 가져오기
    }

    // JWT 토큰에서 이름 추출
    public String getNameFromToken(String token) {
        Claims claims = parseToken(token);
        return claims.get("name", String.class);  // 이름 클레임 가져오기
    }

    // 토큰이 유효한지 검증 (토큰의 서명 검증)
    public boolean isTokenValid(String token) {
        try {
            parseToken(token);  // 파싱을 통해 유효성 검증
            return true;  // 유효한 경우
        } catch (Exception e) {
            return false;  // 예외가 발생하면 토큰이 유효하지 않음
        }
    }
}
