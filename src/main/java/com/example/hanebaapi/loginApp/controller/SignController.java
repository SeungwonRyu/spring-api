package com.example.hanebaapi.loginApp.controller;

import com.example.hanebaapi.loginApp.config.security.JwtProvider;
import com.example.hanebaapi.loginApp.domain.dto.jwt.TokenDTO;
import com.example.hanebaapi.loginApp.domain.dto.sign.UserLoginRequestDTO;
import com.example.hanebaapi.loginApp.domain.dto.sign.UserSignupRequestDTO;
import com.example.hanebaapi.loginApp.model.response.SingleResult;
import com.example.hanebaapi.loginApp.service.SignService;
import com.example.hanebaapi.loginApp.service.UserService;
import com.example.hanebaapi.loginApp.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
@CrossOrigin(originPatterns = "http://localhost:3000")
public class SignController {
    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final ResponseService responseService;
    private final SignService signService;

    // 로그인
    @PostMapping("/login")
    public SingleResult<TokenDTO> login(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {
        TokenDTO tokenDTO = signService.login(userLoginRequestDTO);
        return responseService.getSingleResult(tokenDTO);
    }

    // 회원가입
    @PostMapping("/signup")
    public SingleResult<Long> signup(@RequestBody UserSignupRequestDTO userSignupRequestDTO) {
        Long signupId = signService.signup(userSignupRequestDTO);
        return responseService.getSingleResult(signupId);
    }
}

