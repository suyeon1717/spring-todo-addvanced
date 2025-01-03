package com.example.todoprojectdevelop.repository;

import com.example.todoprojectdevelop.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // 전체 댓글 조회
    List<Comment> findByTodoIdOrderByModifiedAtDesc(Long todoId);

    // 로그인한 유저가 작성한 댓글 조회
    List<Comment> findByUserIdOrderByModifiedAtDesc(Long userId);

    // 댓글 개수
    int countAllByTodoId(Long todoId);

    // 선택 댓글 조회
    default Comment findByCommentIdOrElseThrow(Long commentId) {
        return findById(commentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exitst id = " + commentId));
    }

}
