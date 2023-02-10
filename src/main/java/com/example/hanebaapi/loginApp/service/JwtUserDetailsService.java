package com.example.hanebaapi.loginApp.service;

import com.example.hanebaapi.loginApp.advice.exception.UserNotFoundException;
import com.example.hanebaapi.loginApp.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserJpaRepository userJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String userPk) throws UsernameNotFoundException {
        return userJpaRepository.findById(Long.parseLong(userPk))
                .orElseThrow(UserNotFoundException::new);
    }
}
