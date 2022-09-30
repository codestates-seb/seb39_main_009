package teamparkinglot.parkinggo.token.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import teamparkinglot.parkinggo.token.entity.RefreshToken;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByEmail(String email);

    @Query("select r from RefreshToken r where  r.token = :token")
    Optional<RefreshToken> findByToken(@Param("token") String token);
}
