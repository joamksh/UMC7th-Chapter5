package org.example.study.domain;

public enum MissionStatus {
    IN_PROGRESS, // 진행 중
    COMPLETED;    // 완료
    @Override
    public String toString() {
        return this.name().toLowerCase(); // JSON 응답 시 소문자로 변환
    }
}
