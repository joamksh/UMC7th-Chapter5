package org.example.spring.exception;

public class ReviewHandler extends RuntimeException {
    public ReviewHandler(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
    }

    public ReviewHandler(String message) {
        super(message);
    }
}
