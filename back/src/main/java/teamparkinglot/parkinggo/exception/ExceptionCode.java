package teamparkinglot.parkinggo.exception;

import lombok.Getter;

public enum ExceptionCode {

    MEMBER_EXISTS(409, "이미 존재하는 회원입니다."),
    MEMBER_NOT_EXISTS(404, "회원이 없습니다."),
    PASSWORD_NOT_MATCH(401, "비밀번호가 맞지 않아요"),
    REFRESH_TOKEN_EXPIRED(401, "로그인 다시 하세요."),
    REFRESH_TOKEN_NOT_EXISTS(404, "리프레시 토큰이 없습니다."),
    ACCESS_TOKEN_EXPIRED(401, "Access Token expired"),
    REFRESH_TOKEN_EXISTS(409, "토큰이 이미 있는디?"),
    RESERVATION_NOT_EXISTS(404, "예약이 존재하지 않습니다."),
    POINT_NOT_ENOUGH(404, "포인트가 부족합니다"),
    PARKING_NOT_EXISTS(404, "주차장이 없어요!"),
    NEED_LOGIN(403, "로그인 후에 가능한 기능입니다."),
    INPUT_ERROR(400, "비밀번호 확인과 일치하지 않습니다."),
    ALREADY_EXISTS_BOOKMARK(409, "이미 생성된 즐겨찾기 입니다."),
    BOOKMARK_NOT_EXISTS(404, "즐겨찾기가 없어요"),
    UUID_NOT_MATCH(404, "주소가 잘못된 접근입니다."), SEARCH_ERROR(500, "서버에서 검색 잘못 나갔음 ㅡㅡ"),

    REVIEW_EXISTS(409, "이미 리뷰를 작성했습니다."),
    REVIEW_NOT_EXISTS(404, "존재하지 않는 리뷰입니다."),
    NOT_BEFORE_ONE_HOURS(404, "예약시간 1시간 전까지만 예약취소 가능합니다");

    @Getter
    private int status;
    @Getter
    private String msg;

    ExceptionCode(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
