package com.example.hanebaapi.loginApp.service;

import com.example.hanebaapi.loginApp.advice.exception.EmailLoginFailedException;
import com.example.hanebaapi.loginApp.advice.exception.EmailSignupFailedException;
import com.example.hanebaapi.loginApp.config.security.JwtProvider;
import com.example.hanebaapi.loginApp.domain.dto.jwt.TokenDTO;
import com.example.hanebaapi.loginApp.domain.dto.sign.UserLoginRequestDTO;
import com.example.hanebaapi.loginApp.domain.dto.sign.UserSignupRequestDTO;
import com.example.hanebaapi.loginApp.domain.entity.RefreshToken;
import com.example.hanebaapi.loginApp.domain.entity.User;
import com.example.hanebaapi.loginApp.repository.RefreshTokenJpaRepository;
import com.example.hanebaapi.loginApp.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignService {
    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RefreshTokenJpaRepository refreshTokenJpaRepository;

    @Transactional
    public TokenDTO login(UserLoginRequestDTO userLoginRequestDTO) {
        // 회원 여부 확인
        User user = userJpaRepository.findByEmail(userLoginRequestDTO.getEmail())
                .orElseThrow(EmailLoginFailedException::new);

        // 패스워드 일치 확인
        if (!passwordEncoder.matches(userLoginRequestDTO.getPassword(), user.getPassword()))
            throw new EmailLoginFailedException();

        // Access, Refresh Token 발급
        TokenDTO tokenDTO = jwtProvider.createTokenDTO(user.getId(), user.getRoles());

        // Save Refresh Token
        RefreshToken refreshToken = RefreshToken.builder()
                .key(user.getId())
                .token(tokenDTO.getRefreshToken())
                .build();

        refreshTokenJpaRepository.save(refreshToken);
        return tokenDTO;
    }

    @Transactional
    public Long signup(UserSignupRequestDTO userSignupRequestDTO) {
        if (userJpaRepository.findByEmail(userSignupRequestDTO.getEmail()).isPresent())
            throw new EmailSignupFailedException();
        return userJpaRepository.save(userSignupRequestDTO.toEntity(passwordEncoder)).getId();
    }
}
