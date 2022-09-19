package teamparkinglot.parkinggo.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamparkinglot.parkinggo.token.entity.RefreshToken;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByEmail(String email);

    Optional<RefreshToken> findByToken(String token);
}
