package teamparkinglot.parkinggo.exception;

import lombok.Getter;

public enum ExceptionCode {

    MEMBER_EXISTS(409, "이미 존재하는 회원입니다."),
    MEMBER_NOT_EXISTS(404, "회원이 없습니다."),
    PASSWORD_NOT_MATCH(401, "비밀번호가 맞지 않아요"),
    REFRESH_TOKEN_EXPIRED(401, "로그인 다시 하세요."),
    REFRESH_TOKEN_NOT_EXISTS(404, "리프레시 토큰이 없습니다.");

    @Getter
    private int status;
    @Getter
    private String msg;

    ExceptionCode(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
