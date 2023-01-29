package com.example.hanebaapi.login;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@Getter
@Entity(name = "user")
@Table(name = "tbl_users")
public class User extends BaseTimeEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private String id;
    @Column(name = "Password")
    private String password;
    @Column(name = "Name")
    private String name;
    @Column(name = "Age")
    private int age;
    @Column(name = "Gender")
    private String gender;

    @Builder
    private User(String id, String password, String name, int age, String gender) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Getter
    @Setter
    public static class RequestDTO {
        private String id;
        private String password;

        public User toEntity() {
            return User.builder()
                    .id(id)
                    .password(password)
                    .build();
        }
    }

    public static class ResponseDTO {
        private String id;
        private String name;
        private int age;
        private String gender;

        public ResponseDTO(User user) {
            this.id = user.id;
            this.name = user.name;
            this.age = user.age;
            this.gender = user.gender;
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
        return this.id;
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
