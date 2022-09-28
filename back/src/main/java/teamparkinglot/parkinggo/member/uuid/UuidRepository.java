package teamparkinglot.parkinggo.member.uuid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UuidRepository extends JpaRepository<Uuid, Long> {

    @Query("select u from Uuid u where u.member.email = :email")
    Optional<Uuid> findByEmail(@Param("email") String email);

    @Query("select u from Uuid u join fetch u.member where u.uuid = :uuid")
    Optional<Uuid> findByUuid(@Param("uuid") String uuid);
}
