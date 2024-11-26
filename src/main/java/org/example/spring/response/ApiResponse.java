package org.example.spring.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApiResponse<T> {
    private final boolean success; // 요청 성공 여부
    private final T data;          // 성공 시 반환 데이터
    private final String error;    // 실패 시 오류 메시지

    // 생성자: 외부에서 직접 호출하지 않고 정적 메서드를 통해 생성
    private ApiResponse(boolean success, T data, String error) {
        this.success = success;
        this.data = data;
        this.error = error;
    }

    /**
     * 성공 응답을 생성하는 메서드
     * @param data 반환할 데이터
     * @param <T> 데이터 타입
     * @return 성공 ApiResponse
     */
    public static <T> ApiResponse<T> onSuccess(T data) {
        return new ApiResponse<>(true, data, null);
    }

    /**
     * 실패 응답을 생성하는 메서드
     * @param error 오류 메시지
     * @param <T> 데이터 타입
     * @return 실패 ApiResponse
     */
    public static <T> ApiResponse<T> onError(String error) {
        return new ApiResponse<>(false, null, error);
    }
}

