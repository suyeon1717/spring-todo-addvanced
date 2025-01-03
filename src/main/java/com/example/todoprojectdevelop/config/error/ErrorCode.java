package com.example.todoprojectdevelop.config.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "ERR001", "아이디 또는 비밀번호가 잘못되었습니다."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "ERR002", "올바르지 않은 입력값입니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "ERR003", "잘못된 HTTP 메서드입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "ERR004", "서버 에러가 발생했습니다."),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "ERR005", "존재하지 않는 유저입니다. "),
    NOT_FOUND_TODO(HttpStatus.NOT_FOUND, "ERR005", "존재하지 않는 일정입니다. "),
    NOT_FOUND_COMMENT(HttpStatus.NOT_FOUND, "ERR005", "존재하지 않는 댓글입니다. "),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "ERR006", "접근 권한이 없습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
}
