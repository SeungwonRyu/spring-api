package com.example.hanebaapi.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
/*
* 로그인 실패시 처리 핸들링
* 실패시 메시지 리턴, 실패 url 이동
*/
@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(
            HttpServletRequest req,
            HttpServletResponse res,
            AuthenticationException e
    ) throws IOException, ServletException {
        String msg = "이메일 혹은 비밀번호가 잘못되었습니다.";

        // 예외처리 msg
        if(e instanceof DisabledException) {
            msg = "DisabledException account";
        }

        setDefaultFailureUrl("/id/login?error=true&exception=" + msg);
        super.onAuthenticationFailure(req, res, e);
    }
}


