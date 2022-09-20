package teamparkinglot.parkinggo.uuid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UUIDRepository extends JpaRepository<Uuid, Long> {

    @Query("select u from Uuid u where u.member.email = :email")
    Optional<Uuid> findByEmail(@Param("email") String email);

    @Query("select u from Uuid u join fetch u.member where u.uuid = :uuid")
    Optional<Uuid> findByUuid(String uuid);
}
