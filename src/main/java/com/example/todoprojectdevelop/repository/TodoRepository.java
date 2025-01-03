package com.example.todoprojectdevelop.repository;

import com.example.todoprojectdevelop.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    // 전체 일정 조회 (Param X)
    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);

    // 전체 일정 조회 (Param O)
    Page<Todo> findByModifiedAtBetweenOrUserId(Pageable pageable, LocalDateTime startOfDay, LocalDateTime endOfDay, Long userId);

    // 선택 일정 조회
    default Todo findBytodoIdOrElseThrow(Long todoId) {
        return findById(todoId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exitst id = " + todoId));
    }
}
