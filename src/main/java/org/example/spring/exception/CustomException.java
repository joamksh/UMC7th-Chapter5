package org.example.spring.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorStatus errorStatus;

    public CustomException(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
        this.errorStatus = errorStatus;
    }

    public CustomException(ErrorStatus errorStatus, String customMessage) {
        super(customMessage);
        this.errorStatus = errorStatus;
    }
}
