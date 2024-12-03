package org.example.spring.exception;


public class MissionAlreadyChallengedHandler extends RuntimeException {
    public MissionAlreadyChallengedHandler(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
    }
}
