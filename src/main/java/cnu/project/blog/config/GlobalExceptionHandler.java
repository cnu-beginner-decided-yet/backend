package cnu.project.blog.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice // 모든 @Controller에서 발생하는 예외를 한곳에서 처리하도록 지정
public class GlobalExceptionHandler {

    /**
     * 비즈니스 로직 상의 오류(예: 중복 이메일, 잘못된 비밀번호 등) 처리
     * UserService에서 던지는 모든 IllegalArgumentException을 잡습니다.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        
        // 1. 응답 바디에 포함할 JSON 형태의 Map 생성
        Map<String, String> responseBody = new HashMap<>();
        // ex.getMessage()를 통해 Service에서 설정한 에러 메시지를 가져옴
        responseBody.put("message", ex.getMessage()); 
        responseBody.put("status", "400");
        
        // 2. HTTP 상태 코드를 400 Bad Request로 설정하여 반환
        // 500 에러 대신 400 에러를 반환해야 "클라이언트 요청 오류"임을 명확히 알릴 수 있습니다.
        return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
    }
}