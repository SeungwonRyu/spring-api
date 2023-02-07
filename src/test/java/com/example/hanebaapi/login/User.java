package com.example.hanebaapi.login;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

/*
* Entity 정의 및 요청
* 요청, 응답 클래스 선언
* Spring Security (UserDetails 상속)
*/
@NoArgsConstructor
@Getter
@Entity(name = "user")
public class User extends BaseTimeEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String gender;
    private String dropYn;
    private LocalDateTime lastLoginTime;

    @Builder
    private User(Long id, String email, String password, String gender, String dropYn) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.dropYn = dropYn;
    }

    @Getter
    @Setter
    public static class RequestDTO {
        private String email;
        private String password;
        private String gender;
        private String dropYn;

        public User toEntity() {
            return User.builder()
                    .email(email)
                    .password(password)
                    .gender(gender)
                    .dropYn(dropYn)
                    .build();
        }
    }

    @Getter
    public static class ResponseDTO {
        private Long id;
        private String email;
        private String password;
        private String gender;
        private String dropYn;
        private String lastLoginTime;
        private String registerTime;
        private String modifyTime;

        public ResponseDTO(User user) {
            this.id = user.id;
            this.email = user.email;
            this.password = user.password;
            this.gender = user.gender;
            this.dropYn = user.dropYn;
            this.lastLoginTime = user.toStringDateTime(user.getLastLoginTime());
            this.registerTime = user.toStringDateTime(user.getCreatedDate());
            this.modifyTime = user.toStringDateTime(user.getModifiedDate());
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(() -> {
            return "계정별 등록 권한";
        });
        return collectors;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
