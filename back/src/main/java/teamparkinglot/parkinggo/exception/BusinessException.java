package teamparkinglot.parkinggo.exception;

import lombok.Getter;

public class BusinessException extends RuntimeException {

    @Getter
    private ExceptionCode exceptionCode;


    public BusinessException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMsg());
        this.exceptionCode = exceptionCode;
    }
}
