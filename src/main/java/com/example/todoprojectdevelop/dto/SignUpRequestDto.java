package com.example.todoprojectdevelop.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class SignUpRequestDto {

    @NotBlank
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "올바르지 않은 이메일 형식입니다.")
    private final String email;

    @NotBlank
    // 비밀번호 형식 : 영문자, 숫자, 특수문자를 포함한 8~64자리
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,64}$",
            message = "비밀번호 형식 : 영문자, 숫자, 특수문자를 포함한 8~64자리 입니다.")
    @Size(min = 8, max = 64)
    private final String password;

    @NotEmpty
    @Size(min = 1, max = 10, message = "이름의 길이는 1~10 입니다.")
    private final String userName;

    public SignUpRequestDto(  String email, String password, String userName) {
        this.email = email;
        this.password = password;
        this.userName = userName;
    }
}
