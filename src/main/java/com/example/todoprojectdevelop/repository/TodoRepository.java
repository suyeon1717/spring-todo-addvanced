package com.example.todoprojectdevelop.repository;

import com.example.todoprojectdevelop.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    // 전체 일정 조회 (Param X)
    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);

    // 전체 일정 조회 (Param O)
    Page<Todo> findByModifiedAtBetweenOrUserId(Pageable pageable, LocalDateTime startOfDay, LocalDateTime endOfDay, Long userId);

}
