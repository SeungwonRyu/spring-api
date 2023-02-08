package com.example.hanebaapi.loginApp.config;

import com.example.hanebaapi.loginApp.service.JwtUserDetailsService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.Base64;

@RequiredArgsConstructor
@Component
public class JwtProvider {
    @Value("spring.jwt.secret")
    private String secretKey;
    private Long tokenValidMillisecond = 60 * 60 * 1000L;
    private final JwtUserDetailsService jwtUserDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }


}

