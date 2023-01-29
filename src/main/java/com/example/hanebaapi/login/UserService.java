package com.example.hanebaapi.login;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRep;
    private final BCryptPasswordEncoder pwEncoder;

    public String save(User.RequestDTO reqDTO) {
        reqDTO.setPassword(pwEncoder.encode(reqDTO.getPassword()));
        return userRep.save(reqDTO.toEntity()).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
