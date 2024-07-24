package org.backend.global.response;

import org.springframework.http.HttpStatus;

public enum ResponseCode {

    /*
    Basic
     */
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "권한이 없습니다."),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 메소드입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 오류가 발생하였습니다."),

    /*
    Member
     */
    MEMBER_CREATED(HttpStatus.CREATED, "회원가입 성공"),

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "회원 정보가 존재하지 않습니다."),

    MEMBER_READ_SUCCESS(HttpStatus.OK, "회원 정보 조회 성공"),
    MEMBER_LOGIN_SUCCESS(HttpStatus.OK, "로그인 성공"),
    MEMBER_LOGOUT_SUCCESS(HttpStatus.OK, "로그아웃 성공"),

    MEMBER_DELETE_SUCCESS(HttpStatus.NO_CONTENT, "회원 정보 삭제 성공"),

    /*
    TravelCourse
     */
    COURSE_CREATED(HttpStatus.CREATED, "여행코스 생성 성공"),

    COURSE_NOT_FOUND(HttpStatus.NOT_FOUND, "여행코스가 존재하지 않습니다."),

    COURSE_READ_SUCCESS(HttpStatus.OK, "여행코스 조회 성공"),
    COURSE_MEMBER_READ_SUCCESS(HttpStatus.OK, "회원의 여행코스 목록 조회 성공"),

    COURSE_DELETE_SUCCESS(HttpStatus.NO_CONTENT, "여행코스 삭제 성공"),

    /*
    Location
     */
    LOCATION_CREATED(HttpStatus.CREATED, "위치 생성 성공"),

    LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND, " 존재하지 않는 위치 입니다."),

    LOCATION_READ_SUCCESS(HttpStatus.OK, "위치 조회 성공"),
    LOCATION_DELETE_SUCCESS(HttpStatus.NO_CONTENT, "위치 삭제 성공");

    private HttpStatus status;
    private String message;

    ResponseCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
