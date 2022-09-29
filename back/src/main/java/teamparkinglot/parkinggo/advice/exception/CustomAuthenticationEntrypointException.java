package teamparkinglot.parkinggo.advice.exception;

public class CustomAuthenticationEntrypointException extends RuntimeException {

    public CustomAuthenticationEntrypointException() {
        super();
    }

    public CustomAuthenticationEntrypointException(String message) {
        super(message);
    }

    public CustomAuthenticationEntrypointException(String message, Throwable cause) {
        super(message, cause);
    }
}
