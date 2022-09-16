package teamparkinglot.parkinggo.token.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import teamparkinglot.parkinggo.exception.BusinessException;
import teamparkinglot.parkinggo.exception.ExceptionCode;
import teamparkinglot.parkinggo.member.repository.MemberRepository;
import teamparkinglot.parkinggo.secret.SecretCode;
import teamparkinglot.parkinggo.token.repository.TokenRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TokenController {

    private final SecretCode secretCode;
    private final TokenRepository tokenRepository;

    @PostMapping("/oauth/token")
    public ResponseEntity recreationAccessToken(HttpServletRequest request, HttpServletResponse response) {

        String refreshToken = getRefreshToken(request);

        refreshTokenNotExistsException(refreshToken);

        Date expiresAt = JWT.decode(refreshToken).getExpiresAt();
        Date now = new Date();

        expiredRefreshTokenException(expiresAt, now);

        String email = JWT.require(Algorithm.HMAC512(secretCode.getTokenSecurityKey())).build().verify(refreshToken).getClaim("email").asString();

        tokenRepository.findByEmail(email).orElseThrow(
                () -> new BusinessException(ExceptionCode.REFRESH_TOKEN_NOT_EXISTS)
        );

        String accessToken = recreationAccessToken(email);

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


    private static String getRefreshToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie refresh = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("Refresh")) {
                refresh = cookie;
                break;
            }
        }

        String refreshToken = refresh.getValue();
        return refreshToken;
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
