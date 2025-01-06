# [Spring 심화] 숙련 CRUD 기반의 기능 개선
## 📆 기간 
#### 2024.12.30 ~ 2025.01.06

## 📋 요구사항
#### ☑️ 유효성 검사 추가
#### ☑️ 예외 처리 강화

### 1️⃣ 유효성 검사 추가
#### - 주로 사용자 입력 데이터의 신뢰성을 확보하고, 서버 측에서 비즈니스 로직 수행 전에 데이터를 검증하는 데 사용
#### - 주로 애노테이션 기반 검사와 코드 기반 검사 방식으로 이루어진다.
#### - 아래와 같이 유효성 검사를 추가

- DTO에 @NotBlank 와 같은 어노테이션 사용
- 컨트롤러 메소드에 @Valid 어노테이션 사용
- 메소드 파라미터가 원시타입이거나 문자열일 때에는 @Validated 어노테이션 사용

### 2️⃣ 예외 처리 강화
#### - 적절한 예외 처리 로직을 추가하여 오류 발생 시 사용자에게 명확한 피드백 제공
#### - ENUM으로 에러코드 관리
```java
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
```
#### - GlobalExceptionHandler 클래스 생성
- 애플리케이션의 모든 컨트롤러에서 발생하는 예외를 중앙에서 처리하는 역할
- `@RestControllerAdvice` : `@ControllerAdvice + @ResponseBody`
    - 전역적으로 예외를 처리하고 JSON 형식으로 응답을 반환하는 데 사용
```java
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
```
-  특정 컨트롤러에만 적용하려면?
    -    `@RestControllerAdvice(basePackages = "com.example.api")`
