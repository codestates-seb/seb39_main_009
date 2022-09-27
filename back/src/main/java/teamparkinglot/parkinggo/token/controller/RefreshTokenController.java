package teamparkinglot.parkinggo.token.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamparkinglot.parkinggo.exception.BusinessException;
import teamparkinglot.parkinggo.exception.ExceptionCode;
import teamparkinglot.parkinggo.secret.SecretCode;
import teamparkinglot.parkinggo.token.dto.TokenDto;
import teamparkinglot.parkinggo.token.repository.TokenRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenController {

    private final SecretCode secretCode;
    private final TokenRepository tokenRepository;

    @PostMapping("/oauth/token")
    public ResponseEntity recreationAccessToken(HttpServletResponse response, @RequestBody TokenDto tokenDto) {

        String refreshToken = tokenDto.getRefreshtoken();

        refreshTokenNotExistsException(refreshToken);
        Date expiresAt = JWT.decode(refreshToken).getExpiresAt();
        expiredRefreshTokenException(expiresAt, new Date());

        tokenRepository.findByToken(refreshToken).orElseThrow(
                () -> new BusinessException(ExceptionCode.REFRESH_TOKEN_NOT_EXISTS)
        );

        String accessToken = recreationAccessToken();
        response.addHeader("Authorization", "Bearer " + accessToken);

        return new ResponseEntity(HttpStatus.OK);
    }

    private static void expiredRefreshTokenException(Date expiresAt, Date now) {
        if (expiresAt.before(now)) {
            throw new BusinessException(ExceptionCode.REFRESH_TOKEN_EXPIRED);
        }
    }

    private static void refreshTokenNotExistsException(String refreshToken) {
        if (refreshToken == null) {
            throw new BusinessException(ExceptionCode.REFRESH_TOKEN_NOT_EXISTS);
        }
    }

    private String recreationAccessToken() {
        // 액세스 토큰 발급
        return JWT.create()
                .withSubject("AccessToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + secretCode.getAccessTokenExpireTime()))
                .sign(Algorithm.HMAC512(secretCode.getTokenSecurityKey()));
    }
}
