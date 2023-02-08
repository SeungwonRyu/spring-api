package com.example.hanebaapi.loginApp.domain.dto;

import com.example.hanebaapi.loginApp.domain.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDTO {
    private final Long id;
    private final String email;
    private final String name;

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
    }
}
