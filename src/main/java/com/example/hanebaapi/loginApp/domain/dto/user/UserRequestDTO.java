package com.example.hanebaapi.loginApp.domain.dto.user;

import com.example.hanebaapi.loginApp.domain.entity.User;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private String email;
    private String name;
    private String nickname;

    public User toEntity() {
        return User.builder()
                .email(email)
                .name(name)
                .nickname(nickname)
                .build();
    }
}
