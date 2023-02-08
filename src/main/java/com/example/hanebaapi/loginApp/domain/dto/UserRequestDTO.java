package com.example.hanebaapi.loginApp.domain.dto;

import com.example.hanebaapi.loginApp.domain.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDTO {
    private String email;
    private String name;

    @Builder
    public UserRequestDTO(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .name(name)
                .build();
    }


}
