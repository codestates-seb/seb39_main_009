package teamparkinglot.parkinggo.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {

    @Query("select h from History h join fetch h.parking where h.member.email = :email")
    List<History> findByMemberEmail(@Param("email") String email);
}
