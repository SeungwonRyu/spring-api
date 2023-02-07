package com.example.hanebaapi.login;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
public class SecurityConfiguration {
    private final AuthSuccessHandler authSuccessHandler;
    private final AuthFailureHandler authFailureHandler;

    @Bean
    public BCryptPasswordEncoder pwEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // Http Permission
        http.authorizeHttpRequests()
                .requestMatchers("/", "/user/**", "/error/**", "/js/**", "/css/**", "/image/**")
                .permitAll().anyRequest().authenticated();

        // Login
        http.formLogin()
                .loginPage("/id/login").loginProcessingUrl("/login/action")
                .successHandler(authSuccessHandler).failureHandler(authFailureHandler);

        // Logout
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/id/logout"))
                .logoutSuccessUrl("/id/login").invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me");

        // Session
        http.sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredUrl("/login?error=true&exception=Have been attempted to login from a new place. or session expired");

        // 유지
        http.rememberMe()
                .key("0467EC591838570F48CC386CEE6ED9FBA53B4593A283BAFD5A94347AD3428408")
                .alwaysRemember(false).tokenValiditySeconds(43200)
                .rememberMeParameter("remember-me");

        return http.build();
    }

}
