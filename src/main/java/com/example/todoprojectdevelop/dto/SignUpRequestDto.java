package com.example.todoprojectdevelop.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class SignUpRequestDto {

    @Email(message = "형식 : abc@naver.com")
    @NotBlank
    private final String email;

    @NotBlank
    // 비밀번호 형식 : 영문자, 숫자, 특수문자를 포함한 8~64자리
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,64}$")
    @Size(min = 8, max = 64)
    private final String password;

    @NotEmpty
    @Size(min = 1, max = 10)
    private final String userName;

    public SignUpRequestDto(  String email, String password, String userName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
    }
}
