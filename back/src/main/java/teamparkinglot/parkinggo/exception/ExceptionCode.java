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
    INPUT_ERROR(400, "비밀번호를 다시 입력해주세요.");

    @Getter
    private int status;
    @Getter
    private String msg;

    ExceptionCode(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
