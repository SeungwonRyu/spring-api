package com.example.hanebaapi.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*
* 로그인 성공시 처리 핸들링
* 성공시 마지막 로그인 시간 리턴, 메인 페이지 이동
*/
@RequiredArgsConstructor
@Component
public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserRepository userRep;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest req,
            HttpServletResponse res,
            Authentication auth
    ) throws IOException, ServletException {
        userRep.updateUserLastLogin(auth.getName());
        setDefaultTargetUrl("/main");
        super.onAuthenticationSuccess(req, res, auth);
    }
}
