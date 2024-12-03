package org.example.spring.exception;

public class MissionHandler extends RuntimeException {
    public MissionHandler(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
    }
}