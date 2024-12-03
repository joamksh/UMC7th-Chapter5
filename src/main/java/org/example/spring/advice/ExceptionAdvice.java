package org.example.spring.advice;

import org.example.spring.exception.MissionAlreadyChallengedHandler;
import org.example.spring.exception.MissionHandler;
import org.example.spring.exception.ReviewHandler;
import org.example.spring.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse<String>> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity.badRequest().body(ApiResponse.onError(errorMessage));
    }


    @ExceptionHandler(MissionAlreadyChallengedHandler.class)
    @ResponseBody
    public ResponseEntity<ApiResponse<String>> handleMissionAlreadyChallengedException(MissionAlreadyChallengedHandler ex) {
        return ResponseEntity.badRequest().body(ApiResponse.onError(ex.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ResponseEntity<ApiResponse<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ApiResponse.onError(ex.getMessage()));
    }

    @ExceptionHandler(ReviewHandler.class)
    @ResponseBody
    public ResponseEntity<ApiResponse<String>> handleReviewHandlerException(ReviewHandler ex) {
        return ResponseEntity.badRequest().body(ApiResponse.onError(ex.getMessage()));
    }



}
