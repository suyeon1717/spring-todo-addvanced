package com.example.todoprojectdevelop.dto;

import com.example.todoprojectdevelop.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {

    private final Long userId;

    private final String email;

    private final String userName;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    public UserResponseDto(User user) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.userName = user.getUserName();
        this.createdAt = user.getCreatedAt();
        this.modifiedAt = user.getModifiedAt();
    }
}
