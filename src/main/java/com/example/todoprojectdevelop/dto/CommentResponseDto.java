package com.example.todoprojectdevelop.dto;

import com.example.todoprojectdevelop.entity.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {

    @NotBlank
    private final Long todoId;

    @NotBlank
    private final Long commentId;

    @NotBlank
    private final Long userId;

    @NotBlank
    @Size(min = 1, max = 100)
    private final String contents;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        this.todoId = comment.getTodo().getId();
        this.commentId = comment.getId();
        this.userId = comment.getUser().getId();
        this.contents = comment.getContents();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
