package com.example.todoprojectdevelop.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {

    private final String email;

    public LoginResponseDto(String email) {
        this.email = email;
    }
}
