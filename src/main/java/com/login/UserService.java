package com.login;

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

    public Long save(User.RequestDTO reqDTO) {
        reqDTO.setPassword(pwEncoder.encode(reqDTO.getPassword()));
        reqDTO.setDropYn("N");

        return userRep.save(reqDTO.toEntity()).getId();
    }

    public User findByEmail(String email) {
        return userRep.findByEmail(email).orElseThrow(() -> {
            return new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
        });
    }

    public int countByEmailAndDropYn(String email, String dropYn) {
        return userRep.countByEmailAndDropYn(email, dropYn);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRep.findByEmail(s).orElseThrow(() -> {
            return new UsernameNotFoundException("해당 유저를 찾을 수 없습니다.");
        });
    }
}
