package teamparkinglot.parkinggo.token.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import teamparkinglot.parkinggo.advice.exception.BusinessException;
import teamparkinglot.parkinggo.advice.ExceptionCode;
import teamparkinglot.parkinggo.config.secret.SecretCode;
import teamparkinglot.parkinggo.token.dto.TokenDto;
import teamparkinglot.parkinggo.token.entity.RefreshToken;
import teamparkinglot.parkinggo.token.repository.TokenRepository;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenController {

    private final SecretCode secretCode;
    private final TokenRepository tokenRepository;

    @PostMapping("/oauth/token")
    public ResponseEntity recreationAccessToken(HttpServletResponse response, @RequestBody @Valid TokenDto tokenDto) {

        String refreshToken = tokenDto.getRefreshtoken();

        refreshTokenNotExistsException(refreshToken);
        Date expiresAt = JWT.decode(refreshToken).getExpiresAt();
        expiredRefreshTokenException(expiresAt, new Date());

        log.info("리프레시 토큰 들어오는 값 = {}", refreshToken);
        RefreshToken findRefreshToken = tokenRepository.findByToken(refreshToken).orElseThrow(
                () -> new BusinessException(ExceptionCode.REFRESH_TOKEN_NOT_EXISTS)
        );

        String accessToken = recreationAccessToken(findRefreshToken.getEmail());
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

    private String recreationAccessToken(String email) {
        // 액세스 토큰 발급
        return JWT.create()
                .withSubject("AccessToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + secretCode.getAccessTokenExpireTime()))
                .withClaim("email", email)
                .sign(Algorithm.HMAC512(secretCode.getTokenSecurityKey()));
    }
}
