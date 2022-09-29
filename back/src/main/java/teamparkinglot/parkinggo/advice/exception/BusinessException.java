package teamparkinglot.parkinggo.advice.exception;

import lombok.Getter;
import teamparkinglot.parkinggo.advice.ExceptionCode;

public class BusinessException extends RuntimeException {

    @Getter
    private ExceptionCode exceptionCode;


    public BusinessException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMsg());
        this.exceptionCode = exceptionCode;
    }
}
