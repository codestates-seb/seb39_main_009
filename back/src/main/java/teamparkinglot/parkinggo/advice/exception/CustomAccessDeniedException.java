package teamparkinglot.parkinggo.advice.exception;

import org.springframework.security.access.AccessDeniedException;

public class CustomAccessDeniedException extends AccessDeniedException {
    public CustomAccessDeniedException(String msg) {
        super(msg);
    }

    public CustomAccessDeniedException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
