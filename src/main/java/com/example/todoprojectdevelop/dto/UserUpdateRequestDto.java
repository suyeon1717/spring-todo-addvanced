package com.example.todoprojectdevelop.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class UserUpdateRequestDto {

    @NotEmpty
    @Size(min = 2, max = 30, message = "이름의 사이즈는 2~30 입니다.")
    private final String userName;

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "올바르지 않은 이메일 형식입니다.")
    @NotBlank
    private final String email;

    public UserUpdateRequestDto(String userEmail, String userName) {
        this.email = userEmail;
        this.userName = userName;
    }
}
