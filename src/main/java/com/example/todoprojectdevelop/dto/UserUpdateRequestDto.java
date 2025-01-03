package com.example.todoprojectdevelop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserUpdateRequestDto {

    @NotEmpty
    @Size(min = 2, max = 30)
    private final String userName;

    @Email(message = "형식 : abc@naver.com")
    @NotBlank
    private final String email;

    public UserUpdateRequestDto(String userEmail, String userName) {
        this.email = userEmail;
        this.userName = userName;
    }
}
