package org.example.spring.exception;

public class FoodCategoryHandler extends RuntimeException {
    public FoodCategoryHandler(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
    }
}