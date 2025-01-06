package com.example.todoprojectdevelop.repository;

import com.example.todoprojectdevelop.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 전체 댓글 조회
    List<Comment> findByTodoIdOrderByModifiedAtDesc(Long todoId);

    // 로그인한 유저가 작성한 댓글 조회
    List<Comment> findByUserIdOrderByModifiedAtDesc(Long userId);

    // 댓글 개수
    int countAllByTodoId(Long todoId);

}
