package com.example.todoprojectdevelop.controller;

import com.example.todoprojectdevelop.dto.TodoPageResponseDto;
import com.example.todoprojectdevelop.dto.TodoRequestDto;
import com.example.todoprojectdevelop.dto.TodoResponseDto;
import com.example.todoprojectdevelop.service.TodoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;
    private static final String USER_ID = "USER_ID";

    // 일정 생성
    @PostMapping
    public ResponseEntity<TodoResponseDto> save(
            @SessionAttribute(USER_ID) Long userId,
            @RequestBody @Valid TodoRequestDto requestDto
    ) {

        TodoResponseDto todoResponseDto = todoService.save( //service 요청
                requestDto.getTitle(),
                requestDto.getContents(),
                userId
        );
        return new ResponseEntity<>(todoResponseDto, HttpStatus.CREATED);
    }

    // 전체 일정 조회
    @GetMapping
    public ResponseEntity<List<TodoPageResponseDto>> findAllTodo(
            @RequestParam(required = false) String modifiedAt,
            @RequestParam(required = false) Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<TodoPageResponseDto> todoPageResponseDtos = todoService.findAllTodo(modifiedAt, userId, page, size);
        return new ResponseEntity<>(todoPageResponseDtos.getContent(), HttpStatus.OK);
    }

    // 특정 일정 조회
    @GetMapping("/{todoId}")
    public ResponseEntity<TodoPageResponseDto> findByTodoId(
            @PathVariable Long todoId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        TodoPageResponseDto todoPageResponseDtos = todoService.findByTodoId(todoId);

        return new ResponseEntity<>(todoPageResponseDtos, HttpStatus.OK);
    }

    // 특정 일정 수정
    @PatchMapping("/{todoId}")
    public ResponseEntity<TodoResponseDto> updateTodo(
            @PathVariable Long todoId,
            @RequestBody @Valid TodoRequestDto requestDto
    ) {
        TodoResponseDto todoResponseDto = todoService.updateTodo(todoId, requestDto.getTitle(), requestDto.getContents());
        return new ResponseEntity<>(todoResponseDto, HttpStatus.OK);
    }

    // 특정 일정 삭제
    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long todoId) {
        todoService.deleteTodo(todoId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
