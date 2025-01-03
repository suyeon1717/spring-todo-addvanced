package com.example.todoprojectdevelop.dto;

import com.example.todoprojectdevelop.entity.User;
import lombok.Getter;

@Getter
public class SignUpResponseDto {

    private final Long userId;

    private final String email;

    private final String userName;

    public SignUpResponseDto(User user) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.userName = user.getUserName();
    }
}
