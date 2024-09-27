package org.backend.global.response;


public class ApiResponse<T> {

    private ApiHeader header;
    private ApiBody <T> body;

    public ApiResponse(ApiHeader header, ApiBody<T> body) {
        this.header = header;
        this.body = body;
    }

    public ApiResponse(ApiHeader header) {
        this.header = header;
    }

    public ApiResponse(ApiBody<T> body) {
        this.body = body;
    }

    public ApiHeader getHeader() {
        return header;
    }

    public ApiBody<T> getBody() {
        return body;
    }

    public static <T> ApiResponse<T> success(ResponseCode responseCode, T data) {
        return new ApiResponse<T>(new ApiHeader(responseCode.getHttpStatus(), responseCode.getMessage()), new ApiBody<>(data));
    }

    public static <T> ApiResponse<T> success(ResponseCode responseCode) {
        return new ApiResponse<T>(new ApiHeader(responseCode.getHttpStatus(), responseCode.getMessage()));
    }
}
