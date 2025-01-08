package org.example.spring.exception;

public class MemberMissionHandler extends RuntimeException {
    public MemberMissionHandler(ErrorStatus errorStatus) {
        super(errorStatus.getMessage());
    }

    public MemberMissionHandler(String message) {
        super(message);
    }
}
