package teamparkinglot.parkinggo.token.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import teamparkinglot.parkinggo.exception.BusinessException;
import teamparkinglot.parkinggo.exception.ExceptionCode;
import teamparkinglot.parkinggo.token.entity.RefreshToken;
import teamparkinglot.parkinggo.token.repository.TokenRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    public void createRefreshToken(String token, String email) {

        findVerfiedToken(email);

        RefreshToken refreshToken = new RefreshToken(email, token);

        tokenRepository.save(refreshToken);
    }

    private void findVerfiedToken(String email) {
        tokenRepository.findByEmail(email).ifPresent(
                e -> new BusinessException(ExceptionCode.REFRESH_TOKEN_EXISTS)
        );
    }

    public void deleteIfTokenExsist(String email) {
        Optional<RefreshToken> refreshToken = tokenRepository.findByEmail(email);

        if (refreshToken.isPresent()) {
            tokenRepository.delete(refreshToken.get());
        }

    }
}
