package com.example.hanebaapi.loginApp.domain.entity;

import com.example.hanebaapi.loginApp.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "tbl_refresh_token")
@Getter
@NoArgsConstructor
public class RefreshToken extends BaseTimeEntity {
    //private String id;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long key;
    @Column(nullable = false)
    private String token;

    public RefreshToken updateToken(String token) {
        this.token = token;
        return this;
    }

    @Builder
    public RefreshToken(Long key, String token) {
        this.key = key;
        this.token = token;
    }
}
