package com.example.spring_boot.service;


import com.example.spring_boot.web.dto.JwtTokenRequest;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class MyJwtTokenServiceV2 {
    private final long JWT_EXPIRATION = 43200000L; // 1 day

    private void logToken(Map<String, Object> claims) {
        log.info(claims.get("username").toString());
        log.info(claims.get("role").toString());
        log.info(claims.get("issuer").toString());
        log.info(claims.get("audience").toString());
        log.info(claims.get("token_type").toString());
    }


    public Mono<String> generateTokenV2(JwtTokenRequest jwtTokenRequest) {

        // Create claims from JwtTokenRequest
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", jwtTokenRequest.getUsername());
        claims.put("role", jwtTokenRequest.getRole());
        claims.put("version", "2");
        claims.put("audience", "MyUser");
        claims.put("token_type", "Bearer");

        logToken(claims);

        // Generate JWT Token
        return Mono.just(Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .setHeaderParam("alg", "none")
                .compact());
    }

}