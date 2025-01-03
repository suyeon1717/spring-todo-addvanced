package com.example.todoprojectdevelop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class TodoRequestDto {

    @NotNull
    @Size(min = 1, max = 20, message = "제목의 길이는 1~20입니다.")
    private final String title;

    @Size(max = 300, message = "내용의 최대 길이는 300입니다.")
    private final String contents;

    public TodoRequestDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
