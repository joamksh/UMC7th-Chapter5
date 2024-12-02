package org.example.spring.exception;

public enum ErrorStatus {
    FOOD_CATEGORY_NOT_FOUND("Food category not found"),
    STORE_NOT_FOUND("Store not found"),
    MEMBER_NOT_FOUND("Member not found"),
    MISSION_NOT_FOUND("Mission not found"),
    REVIEW_CREATION_ERROR("Failed to create review"),
    REVIEW_NOT_FOUND("Review not found");


    private final String message;

    ErrorStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
