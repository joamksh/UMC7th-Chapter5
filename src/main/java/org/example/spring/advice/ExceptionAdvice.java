//package org.example.spring.advice;
//
//import lombok.extern.slf4j.Slf4j;
//import org.example.spring.exception.CustomException;
//import org.example.spring.response.ApiResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//@Slf4j
//@RestControllerAdvice
//public class ExceptionAdvice {
//
//    @ExceptionHandler(CustomException.class)
//    public ResponseEntity<ApiResponse<Object>> handleCustomException(CustomException ex) {
//        log.error("CustomException: {}", ex.getMessage());
//        ApiResponse<Object> response = ApiResponse.onError(ex.getMessage());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex) {
//        log.error("Exception: {}", ex.getMessage());
//        ApiResponse<Object> response = ApiResponse.onError("Unexpected error occurred.");
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
//    }
//}
