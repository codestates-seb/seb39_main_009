package teamparkinglot.parkinggo.advice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teamparkinglot.parkinggo.advice.exception.CustomAccessDeniedException;
import teamparkinglot.parkinggo.advice.exception.CustomAuthenticationEntrypointException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("/entrypoint")
    public ResponseEntity AuthenticationException() {
        throw new CustomAuthenticationEntrypointException("인증 문제 발생!");
    }

    @GetMapping("/accessDenied")
    public ResponseEntity accessDeniedException() {
        throw new CustomAccessDeniedException("권한이 없습니다.");
    }
}
