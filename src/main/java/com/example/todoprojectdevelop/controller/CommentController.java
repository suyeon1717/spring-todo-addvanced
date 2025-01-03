package com.example.todoprojectdevelop.controller;

import com.example.todoprojectdevelop.dto.CommentResponseDto;
import com.example.todoprojectdevelop.dto.MyCommnetsResponseDto;
import com.example.todoprojectdevelop.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private static final String USER_ID = "USER_ID";

    // 댓글 작성
    @PostMapping("/{todoId}/comment")
    public ResponseEntity<CommentResponseDto> save(
            @SessionAttribute(USER_ID) Long userId,
            @PathVariable Long todoId,
            @RequestBody String contents)
    {
        CommentResponseDto commentResponseDto = commentService.save(userId, todoId, contents);

        return new ResponseEntity<>(commentResponseDto, HttpStatus.CREATED);
    }

    // 전체 댓글 조회
    @GetMapping("/{todoId}/comment")
    public ResponseEntity<List<CommentResponseDto>> findAllComment(
            @PathVariable Long todoId
    ) {
        List<CommentResponseDto> commentResponseDtoList = commentService.findAllComment(todoId);
        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }

    // 내가 작성한 댓글 조회
    @GetMapping("/mycomments")
    public ResponseEntity<List<MyCommnetsResponseDto>> findMycomments(@SessionAttribute(USER_ID) Long userId){
        List<MyCommnetsResponseDto> myCommentResponseDtoList = commentService.findMyComments(userId);
        return new ResponseEntity<>(myCommentResponseDtoList, HttpStatus.OK);
    }

    // 댓글 수정
    @PatchMapping("/mycomments/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @SessionAttribute(USER_ID) Long userId,
            @PathVariable Long commentId,
            @RequestBody String contents
    ) {
        CommentResponseDto commentResponseDto = commentService.updateComment(userId, commentId, contents);

        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/mycomments/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @SessionAttribute(USER_ID) Long userId,
            @PathVariable Long commentId
    ) {
        commentService.deleteComment(userId, commentId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
