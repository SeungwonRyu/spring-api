package com.example.hanebaapi.loginApp.advice;

import com.example.hanebaapi.loginApp.advice.exception.EmailLoginFailedException;
import com.example.hanebaapi.loginApp.advice.exception.EmailSignupFailedException;
import com.example.hanebaapi.loginApp.advice.exception.UserNotFoundException;
import com.example.hanebaapi.loginApp.model.response.CommonResult;
import com.example.hanebaapi.loginApp.service.response.ResponseService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {
    private final ResponseService responseService;

    // default
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 500
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {
        //log.info(String.valueOf(e));
        return responseService.getFailResult();
    }

    // User not found
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected CommonResult userNotFoundException(HttpServletRequest request, UserNotFoundException e) {
        return responseService.getFailResult();
    }

    // 로그인 실패
    @ExceptionHandler(EmailLoginFailedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    protected CommonResult emailLoginFailedException(HttpServletRequest request, EmailLoginFailedException e) {
        return responseService.getFailResult();
    }

    // 회원가입 실패 (중복)
    @ExceptionHandler(EmailSignupFailedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected  CommonResult emailSignupFailedException(HttpServletRequest request, EmailSignupFailedException e) {
        return responseService.getFailResult();
    }

    private String getMessage(String code) {
        return getMessage(code);
    }
}
