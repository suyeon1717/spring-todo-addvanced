package com.example.todoprojectdevelop.dto;

import com.example.todoprojectdevelop.entity.Todo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TodoPageResponseDto {

    private final String title;

    private final String contents;

    private final int commentCnt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime modifiedAt;

    private final String userName;

    public TodoPageResponseDto(Todo todo, int commentCnt, String userName) {
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.commentCnt = commentCnt;
        this.createdAt = todo.getCreatedAt();
        this.modifiedAt = todo.getModifiedAt();
        this.userName = userName;
    }
}
