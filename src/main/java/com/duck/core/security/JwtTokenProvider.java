package com.duck.core.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.DecodingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * Created by eunduck on 2022/10/31.
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private final String SECRET = "duck-stud-92888810231122627393-eyJ1c2VybmFtZSI6InN0cmluZzBAZ21haWw";

    /**
     * 토큰 생성
     */
    public String generateToken(UserDetails userDetails) {
        Claims claims = Jwts.claims();
        claims.put("username", userDetails.getUsername());
        return createToken(claims);
    }

    private String createToken(Claims claims) {
        byte[] keyBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key key = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 1시간
                .signWith(key)
                .compact();
    }

    /**
     * 토큰의 Claim 디코딩
     */
    private Claims getAllClaims(String token) {
        byte[] keyBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key key = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Claim 에서 username 가져오기
     */
    public String getUsernameFromToken(String token) {
        return String.valueOf(getAllClaims(token).get("username"));
    }

    public boolean validateToken(String token) {
        try {
            byte[] keyBytes = DatatypeConverter.parseBase64Binary(SECRET);
            Key key = new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException | DecodingException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;
    }
}
