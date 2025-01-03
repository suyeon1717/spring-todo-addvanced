package com.example.todoprojectdevelop.service;

import com.example.todoprojectdevelop.dto.TodoPageResponseDto;
import com.example.todoprojectdevelop.dto.TodoResponseDto;
import com.example.todoprojectdevelop.entity.Todo;
import com.example.todoprojectdevelop.entity.User;
import com.example.todoprojectdevelop.repository.CommentRepository;
import com.example.todoprojectdevelop.repository.TodoRepository;
import com.example.todoprojectdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository; // Todo N : User 1 연관 관계
    private final CommentRepository commentRepository;

    // 일정 생성
    public TodoResponseDto save(String title, String contents, Long userId) {

        User findUser = userRepository.findByUserIdOrElseThrow(userId);

        Todo todo = new Todo(title, contents);
        todo.setUser(findUser);

        Todo savedTodo = todoRepository.save(todo); // Repository에 저장

        return new TodoResponseDto(savedTodo);
    }

    // 전체 일정 조회 : case 4개
    public Page<TodoPageResponseDto> findAllTodo(String modifiedAt, Long userId, int page, int size) {

        Pageable pageable = PageRequest.of(page, size); // pageable 객체 생성
        Page<Todo> todoPage;

        if(modifiedAt == null && userId == null){ //Param이 모두 비어있다면 모든 일정을 조회
            todoPage = todoRepository.findAllByOrderByModifiedAtDesc(pageable);
        }
        else{
            LocalDate date = null;
            if(modifiedAt != null)
                date = LocalDate.parse(modifiedAt, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            // LocalDate를 LocalDateTime으로 변환 (하루의 시작과 끝)
            LocalDateTime startOfDay = (date != null) ? date.atStartOfDay() : null;
            LocalDateTime endOfDay = (date != null) ? date.atTime(23, 59, 59, 999999) : null;

            todoPage = todoRepository.findByModifiedAtBetweenOrUserId(pageable, startOfDay, endOfDay, userId);
        }

        return todoPage.map(todo -> new TodoPageResponseDto(todo, commentRepository.countAllByTodoId(todo.getId()), todo.getUser().getUserName()));
    }

    // 선택 일정 조회 -> 일정은 하나 뿐이라 페이지 의미 없음, response만 page response 형태로
    public TodoPageResponseDto findByTodoId(Long todoId) {

        Todo todo = todoRepository.findBytodoIdOrElseThrow(todoId);

        return new TodoPageResponseDto(todo, commentRepository.countAllByTodoId(todoId), todo.getUser().getUserName());
    }

    // 선택 일정 수정
    @Transactional
    public TodoResponseDto updateTodo(Long todoId, String title, String contents) {
        Todo todo = todoRepository.findBytodoIdOrElseThrow(todoId);

        // 제목 수정
        if(title != null){
            todo.updateTitle(title);
        }
        // 내용 수정
        if(contents != null){
            todo.updateContents(contents);
        }
        todoRepository.flush(); //todoRepository.save(todo) XX 영속성 문제

        return new TodoResponseDto(todo);

    }

    // 선택 일정 삭제
    public void deleteTodo(Long todoId) {
        Todo todo = todoRepository.findBytodoIdOrElseThrow(todoId);

        todoRepository.delete(todo);
    }

}
