package com.example.todoprojectdevelop.dto;

import com.example.todoprojectdevelop.entity.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MyCommnetsResponseDto {
    private final Long todoId;

    private final String todoTitle;

    private final Long commentId;

    private final String commentContents;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime modifiedAt;

    public MyCommnetsResponseDto(Comment comment) {
        this.todoId = comment.getTodo().getId();
        this.todoTitle = comment.getTodo().getTitle();
        this.commentId = comment.getId();
        this.commentContents = comment.getContents();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
