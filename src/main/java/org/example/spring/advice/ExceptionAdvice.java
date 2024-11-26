package org.example.spring.advice;

import lombok.extern.slf4j.Slf4j;
import org.example.spring.exception.CustomException;
import org.example.spring.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;
@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Object>> handleCustomException(CustomException ex, HttpServletRequest request) {
        log.error("CustomException occurred at {}: {}", request.getRequestURI(), ex.getMessage());
        ApiResponse<Object> response = ApiResponse.onError(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex, HttpServletRequest request) {
        log.error("Exception occurred at {}: {}", request.getRequestURI(), ex.getMessage());
        // Swagger 관련 요청 차단 방지
        if (request.getRequestURI().contains("/v3/api-docs")) {
            log.warn("Swagger request to /v3/api-docs bypassed.");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        ApiResponse<Object> response = ApiResponse.onError("An unexpected error occurred: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
