package com.example.hanebaapi.loginApp.service.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonResponse {
    SUCCESS(0, "success"),
    FAIL(-1, "fail");

    private int code;
    private String msg;
}
