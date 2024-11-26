package org.example.spring.exception;

public enum ErrorStatus {
    FOOD_CATEGORY_NOT_FOUND("Food category not found");

    private final String message;

    ErrorStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
