package org.example.spring.exception;

public enum ErrorStatus {
    FOOD_CATEGORY_NOT_FOUND("Food category not found"),
    STORE_NOT_FOUND("Store not found"),
    MEMBER_NOT_FOUND("멤버를 찾을 수 없습니다."),
    MISSION_NOT_FOUND("Mission not found"),
    REVIEW_CREATION_ERROR("Failed to create review"),
    REVIEW_NOT_FOUND("Review not found"),
    MISSION_ALREADY_EXISTS("Mission already exists for the store"),
    MISSION_ALREADY_CHALLENGED("Mission is already being challenged"),
    PAGE_INDEX_INVALID("페이지 번호는 1 이상이어야 합니다.");


    private final String message;

    ErrorStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
