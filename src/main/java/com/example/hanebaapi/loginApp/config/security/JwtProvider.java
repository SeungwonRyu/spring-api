package com.example.hanebaapi.loginApp.config.security;

import com.example.hanebaapi.loginApp.domain.dto.jwt.TokenDTO;
import com.example.hanebaapi.loginApp.service.JwtUserDetailsService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.Base64UrlCodec;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secretKey;
    private String ROLES = "roles";
    private final Long accessTokenValidMillisecond = 60 * 60 * 1000L;  // 1H
    private final Long refreshToeknValidMilisecond = 14 * 24 * 60 * 60 * 1000L;  // 14D
    private final UserDetailsService userDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        //secretKey = Base64UrlCodec.BASE64URL.encode(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    // Jwt 생성
    public TokenDTO createTokenDTO(Long userPk, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(String.valueOf(userPk));
        claims.put(ROLES, roles);

        Date now = new Date();

        String accessToken = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + accessTokenValidMillisecond))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        String refreshToken = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setExpiration(new Date(now.getTime() + refreshToeknValidMilisecond))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return TokenDTO.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpireDate(accessTokenValidMillisecond)
                .build();
    }

    // 인증정보 조회
    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);

        //if(claims.get(ROLES) == null) throw new Exception

        UserDetails userDetails = userDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰 복호화
    private Claims parseClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    // HTTP Request Header, Token parsing "X-AUTH-TOKEN"
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    // jwt 유효성 및 만료일자 check
    public boolean validationToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}

