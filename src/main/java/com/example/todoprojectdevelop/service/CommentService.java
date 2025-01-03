package com.example.todoprojectdevelop.service;

import com.example.todoprojectdevelop.dto.CommentResponseDto;
import com.example.todoprojectdevelop.dto.MyCommnetsResponseDto;
import com.example.todoprojectdevelop.entity.Comment;
import com.example.todoprojectdevelop.entity.Todo;
import com.example.todoprojectdevelop.entity.User;
import com.example.todoprojectdevelop.repository.CommentRepository;
import com.example.todoprojectdevelop.repository.TodoRepository;
import com.example.todoprojectdevelop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    // 댓글 작성
    public CommentResponseDto save(Long userId, Long todoId, String contents) {
        User user = userRepository.findByUserIdOrElseThrow(userId);
        Todo todo = todoRepository.findBytodoIdOrElseThrow(todoId);

        Comment comment = new Comment(contents);
        comment.setTodo(todo);
        comment.setUser(user);

        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDto(savedComment);
    }
    // 전체 댓글 조회
    public List<CommentResponseDto> findAllComment(Long todoId) {
        List<Comment> commentList = commentRepository.findByTodoIdOrderByModifiedAtDesc(todoId);

        List<CommentResponseDto> commentResponseDtoList =
                commentList.stream()
                        .map(comment -> new CommentResponseDto(comment))
                        .collect(Collectors.toList());

        return commentResponseDtoList;
    }

    // 로그인한 유저가 작성한 댓글 조회
    public List<MyCommnetsResponseDto> findMyComments(Long userId) {
        List<Comment> commentList = commentRepository.findByUserIdOrderByModifiedAtDesc(userId);

        List<MyCommnetsResponseDto> myCommnetsResponseDtoList =
                commentList.stream()
                        .map(comment -> new MyCommnetsResponseDto(comment))
                        .collect(Collectors.toList());

        return myCommnetsResponseDtoList;
    }

    @Transactional
    // 댓글 수정
    public CommentResponseDto updateComment(Long userId, Long commentId, String contents) {
        Comment comment = commentRepository.findByCommentIdOrElseThrow(commentId);

        if(!comment.getUser().getId().equals(userId)) // 수정하려는 댓글의 작성자 != 로그인 유저
            throw new RuntimeException("댓글 수정 권한이 없습니다.");

        comment.updateContents(contents);
        commentRepository.flush();

        return new CommentResponseDto(comment);
    }

    // 댓글 삭제
    public void deleteComment(Long userId, Long commentId) {
        Comment comment = commentRepository.findByCommentIdOrElseThrow(commentId);

        if(!comment.getUser().getId().equals(userId)) // 삭제하려는 댓글의 작성자 != 로그인 유저
            throw new RuntimeException("댓글 삭제 권한이 없습니다.");

        commentRepository.delete(comment);
    }

}
