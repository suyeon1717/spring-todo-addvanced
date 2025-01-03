package com.example.todoprojectdevelop.config.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice // @ControllerAdvice + @ResponseBody : 전역적으로 예외를 처리하고 JSON 형식으로 응답을 반환하는 데 사용
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, String>> handleCustomException(CustomException ex) {
        Map<String, String> response = new LinkedHashMap<>();
        response.put("errorCode", ex.getErrorCode().getCode());
        response.put("errorMessage", ex.getErrorCode().getMessage());

        log.info("에러 발생 >> 코드 : {}, 메시지 : {}", response.get("errorCode"), response.get("errorMessage"));
        return new ResponseEntity<>(response, ex.getErrorCode().getStatus());
    }
}
