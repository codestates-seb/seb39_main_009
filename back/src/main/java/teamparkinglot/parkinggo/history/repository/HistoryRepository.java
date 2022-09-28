package teamparkinglot.parkinggo.history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import teamparkinglot.parkinggo.history.entity.History;

import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query("select h from History h join fetch h.parking where h.member.email = :email")
    List<History> findByMemberEmail(@Param("email") String email);

    @Query("select h from History h where h.member.email = :email and h.parking.id = :parkingId")
    Optional<History> findByMemberEmailAndParkingId(@Param("email") String email, @Param("parkingId") long parkingId);
}
