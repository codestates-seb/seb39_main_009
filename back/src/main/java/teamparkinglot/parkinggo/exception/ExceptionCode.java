package teamparkinglot.parkinggo.exception;

import lombok.Getter;

public enum ExceptionCode {

    MEMBER_EXISTS(409, "이미 존재하는 회원입니다.");

    @Getter
    private int status;
    @Getter
    private String msg;

    ExceptionCode(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
